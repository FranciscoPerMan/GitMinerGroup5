package aiss.githubminer.service;

import aiss.githubminer.model.comments.Comment;
import aiss.githubminer.model.commits.Commit;
import aiss.githubminer.model.gitminer.*;
import aiss.githubminer.model.issues.Issue;
import aiss.githubminer.model.projects.Project;
import aiss.githubminer.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransformerService {
    @Autowired
    GitHubService service;

    // Cache for full user information.
    // Since there will be repeated requests for the same user, we cache the full user information.
    private List<User> fullUsersInfoCache = new ArrayList<>();

    public GMProject fromGitlabToGitminerModel(Project gitHubProject) {
        GMProject gitminerProject = new GMProject();
        gitminerProject.setId(gitHubProject.getId().toString());
        gitminerProject.setName(gitHubProject.getName());
        gitminerProject.setWebUrl(gitHubProject.getUrl());
        return gitminerProject;
    }
    private GMUser transformUser(User gitHubUser){
            // Some users may be null, so we return null in that case.
            if (gitHubUser == null) return null;

            // Sometimes github will return a user object with limited information.
            // If we have already retrieved the full user information, use that.
            // Otherwise, retrieve the full user information.

            // java complains about it not being 'final' :(
            User finalGitHubUser = gitHubUser;
            if (fullUsersInfoCache.stream().anyMatch(u -> u.getId().equals(finalGitHubUser.getId()))) {
                // if the user is in the cache, retrieve it from there
                gitHubUser = fullUsersInfoCache.stream().filter(u -> u.getId().equals(finalGitHubUser.getId())).findFirst().get();
            } else {
                // if the user is not in the cache, retrieve the full user information
                gitHubUser = service.findUserByLogin(gitHubUser.getLogin());
                fullUsersInfoCache.add(gitHubUser);
            }

            // here, the current gitHubUser object will always have the full user information
            GMUser gitminerUser = new GMUser();
            gitminerUser.setId(gitHubUser.getId().toString());
            // Some users may not have a name
            if (gitHubUser.getName() != null) {
                gitminerUser.setName(gitHubUser.getName().toString());
            }
            gitminerUser.setUsername(gitHubUser.getLogin());
            gitminerUser.setWebUrl(gitHubUser.getUrl());
            gitminerUser.setAvatarUrl(gitHubUser.getAvatarUrl());
            return gitminerUser;

    }
    private List<GMCommit> transformCommits(List<Commit> commits){
        List<GMCommit> GMcommits= new ArrayList<>();
            for (Commit commit:commits){
                GMCommit GMcommit = new GMCommit();
                GMcommit.setId(commit.getSha());
                // Title is the first 72 characters of the message.
                // Use min to avoid exception if message is shorter than 72 characters.
                GMcommit.setTitle(commit.getCommit().getMessage().substring(0,Math.min(commit.getCommit().getMessage().length(), 72)));
                GMcommit.setMessage(commit.getCommit().getMessage());
                GMcommit.setAuthorName(commit.getCommit().getAuthor().getName());
                GMcommit.setAuthorEmail(commit.getCommit().getAuthor().getEmail());
                GMcommit.setAuthoredDate(commit.getCommit().getAuthor().getDate());
                GMcommit.setCommitterName(commit.getCommit().getCommitter().getName());
                GMcommit.setCommitterEmail(commit.getCommit().getCommitter().getEmail());
                GMcommit.setCommittedDate(commit.getCommit().getCommitter().getDate());
                GMcommit.setWebUrl(commit.getUrl());
                GMcommits.add(GMcommit);
            }
            return GMcommits;
    }
    private List<GMIssue> transformIssues(List<Issue> issues,String owner,String repository){
        List<GMIssue> GMissues= new ArrayList<>();
        for (Issue issue:issues){
            GMIssue GMissue =  new GMIssue();
            List<Comment> comments = service.findIssueComments(owner,repository,issue.getNumber());
            GMissue.setComments(tranformsIssueComments(comments));
            GMissue.setId(issue.getId().toString());
            GMissue.setRefId(issue.getNumber().toString());
            GMissue.setTitle(issue.getTitle());
            GMissue.setDescription(issue.getBody());
            GMissue.setState(issue.getState());
            GMissue.setCreatedAt(issue.getCreatedAt());
            GMissue.setUpdatedAt(issue.getUpdatedAt());
            if (issue.getClosedAt()!=null){
                GMissue.setClosedAt(issue.getClosedAt().toString());
            }
            GMissue.setLabels(issue.getLabels().stream().map(x->x.getName()).collect(Collectors.toList()));
            GMissue.setUpvotes(issue.getReactions().getPositive());
            GMissue.setDownvotes(issue.getReactions().getNegative());
            GMissue.setAuthor(transformUser(issue.getUser()));
            GMissue.setAssignee(transformUser(issue.getAssignee()));
            GMissue.setWebUrl(issue.getUrl());
            GMissues.add(GMissue);
        }
        return GMissues;
    }
    private List<GMComment> tranformsIssueComments(List<Comment> comments){
        List<GMComment> GMcomments = new ArrayList<>();
        for (Comment c: comments){
            GMComment GMcomment = new GMComment();
            GMcomment.setId(c.getId().toString());
            GMcomment.setBody(c.getBody());
            GMcomment.setCreatedAt(c.getCreatedAt());
            GMcomment.setUpdatedAt(c.getUpdatedAt());
            GMcomment.setAuthor(transformUser(c.getUser()));
            GMcomments.add(GMcomment);
        }
        return GMcomments;
    }
    public GMProject getCompleteGMProject(String owner,String repository, Integer sinceCommits, Integer sinceIssues, Integer maxPages) {
        Project gitHubProject = service.findProjectByOwnerAndRepository(owner,repository);
        List<Commit> gitHubCommits = service.findAllCommits(owner,repository, sinceCommits, maxPages);
        List<Issue> gitHubIssues = service.findAllIssues(owner,repository, sinceIssues, maxPages);
        GMProject gitminerProject = fromGitlabToGitminerModel(gitHubProject);
        List<GMCommit> gitminerCommits = transformCommits(gitHubCommits);
        gitminerProject.setCommits(gitminerCommits);
        List<GMIssue> gitminerIssues = transformIssues(gitHubIssues,owner,repository);
        gitminerProject.setIssues(gitminerIssues);

        return gitminerProject;
    }
}
