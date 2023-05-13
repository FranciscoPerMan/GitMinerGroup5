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

    public GMProject fromGitlabToGitminerModel(Project gitHubProject) {
        GMProject gitminerProject = new GMProject();
        gitminerProject.setId(gitHubProject.getId().toString());
        gitminerProject.setName(gitHubProject.getName());
        gitminerProject.setWebUrl(gitHubProject.getUrl());
        return gitminerProject;
    }
    private GMUser transformUser(User gitHubUser){
        try{
            GMUser gitminerUser = new GMUser();
            gitminerUser.setId(gitHubUser.getId().toString());
            gitminerUser.setName(gitHubUser.getName().toString());
            gitminerUser.setUsername(gitHubUser.getLogin());
            gitminerUser.setWebUrl(gitHubUser.getUrl());
            gitminerUser.setAvatarUrl(gitHubUser.getAvatarUrl());
            return gitminerUser;
        }catch (Exception e){
            return null;
        }
    }
    private List<GMCommit> transformCommits(List<Commit> commits){
        List<GMCommit> GMcommits= new ArrayList<>();
            for (Commit commit:commits){
                GMCommit GMcommit = new GMCommit();
                GMcommit.setId(commit.getSha());
                GMcommit.setTitle(commit.getCommit().getMessage().substring(0,72));
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
            List<Comment> comments = service.findIssueComments(owner,repository,issue.getId());
            GMissue.setComments(tranformsIssueComments(comments));
            GMissue.setId(issue.getId().toString());
            GMissue.setRefId(issue.getNumber().toString());
            GMissue.setTitle(issue.getTitle());
            GMissue.setDescription(issue.getBody());
            GMissue.setState(issue.getState());
            GMissue.setCreatedAt(issue.getCreatedAt());
            GMissue.setUpdatedAt(issue.getUpdatedAt());
            GMissue.setClosedAt(issue.getClosedAt().toString());
            GMissue.setLabels(issue.getLabels().stream().map(x->x.getName()).collect(Collectors.toList()));
            GMissue.setUpvotes(issue.getReactions().getPositive());
            GMissue.setDownvotes(issue.getReactions().getNegative());
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
        List<GMIssue> gitminerIssues = transformIssues(gitHubIssues,repository,owner);
        gitminerProject.setIssues(gitminerIssues);

        return gitminerProject;
    }
}