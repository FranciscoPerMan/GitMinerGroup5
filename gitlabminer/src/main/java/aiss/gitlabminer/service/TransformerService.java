package aiss.gitlabminer.service;

import aiss.gitlabminer.model.gitlab.*;
import aiss.gitlabminer.model.gitminer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TransformerService {

    @Autowired
    private GitLabService gitLabService;

    private GMProject transformProject(Project gitlabProject) {
        GMProject gitminerProject = new GMProject();
        gitminerProject.setName(gitlabProject.getName());
        gitminerProject.setWebUrl(gitlabProject.getWebUrl());
        gitminerProject.setId(gitlabProject.getId().toString());
        return gitminerProject;
    }

    private List<GMCommit> transformCommits(List<Commit> gitlabCommits) {
        List<GMCommit> gitminerCommits = new LinkedList<>();
        for (Commit gitlabCommit : gitlabCommits) {
            GMCommit gitminerCommit = new GMCommit();
            gitminerCommit.setId(gitlabCommit.getId());
            gitminerCommit.setAuthorName(gitlabCommit.getAuthorName());
            gitminerCommit.setAuthorEmail(gitlabCommit.getAuthorEmail());
            gitminerCommit.setCommittedDate(gitlabCommit.getCommittedDate());
            gitminerCommit.setMessage(gitlabCommit.getMessage());
            gitminerCommit.setAuthoredDate(gitlabCommit.getAuthoredDate());
            gitminerCommit.setCommitterName(gitlabCommit.getCommitterName());
            gitminerCommit.setCommitterEmail(gitlabCommit.getCommitterEmail());
            gitminerCommit.setWebUrl(gitlabCommit.getWebUrl());
            gitminerCommit.setTitle(gitlabCommit.getTitle());
            gitminerCommits.add(gitminerCommit);
        }
        return gitminerCommits;

    }

    private GMUser transformUser(User gitlabUser){
        try{
            GMUser gitminerUser = new GMUser();
            gitminerUser.setId(gitlabUser.getId().toString());
            gitminerUser.setName(gitlabUser.getName());
            gitminerUser.setUsername(gitlabUser.getUsername());
            gitminerUser.setWebUrl(gitlabUser.getWebUrl());
            gitminerUser.setAvatarUrl(gitlabUser.getAvatarUrl());
        return gitminerUser;
        }catch (Exception e){
            return null;
        }
    }

    private List<GMComment> transformComments(List<Comment> gitlabComments) {
        List<GMComment> gitminerComments = new LinkedList<>();
        for (Comment gitlabComment : gitlabComments) {
            GMComment gitminerComment = new GMComment();
            gitminerComment.setId(gitlabComment.getId().toString());
            gitminerComment.setBody(gitlabComment.getBody());
            gitminerComment.setCreatedAt(gitlabComment.getCreatedAt());
            gitminerComment.setUpdatedAt(gitlabComment.getUpdatedAt());
            gitminerComment.setAuthor(transformUser(gitlabComment.getAuthor()));
            gitminerComments.add(gitminerComment);
        }
        return gitminerComments;
    }


    private List<GMIssue> transformIssues(List<Issue> gitlabIssues) {
        System.out.println(gitlabIssues.toString());
        List<GMIssue> gitminerIssues = new LinkedList<>();
        for (Issue gitlabIssue : gitlabIssues) {
            List<Comment> gitlabComments = gitLabService.findIssueComments(gitlabIssue.getProjectId().toString(), gitlabIssue.getIid().toString());
            GMUser gitminerAuthor = transformUser(gitlabIssue.getAuthor());
            GMIssue gitminerIssue = new GMIssue();
            List<User> gitlabAssignees = gitlabIssue.getAssignees();

            gitminerIssue.setId(gitlabIssue.getId().toString());
            gitminerIssue.setRefId(gitlabIssue.getIid().toString());
            gitminerIssue.setTitle(gitlabIssue.getTitle());
            gitminerIssue.setDescription(gitlabIssue.getDescription());
            gitminerIssue.setState(gitlabIssue.getState());
            gitminerIssue.setCreatedAt(gitlabIssue.getCreatedAt());
            gitminerIssue.setUpdatedAt(gitlabIssue.getUpdatedAt());

            gitminerIssue.setLabels(gitlabIssue.getLabels());
            gitminerIssue.setAuthor(gitminerAuthor);
            gitminerIssue.setUpvotes(gitlabIssue.getUpvotes());
            gitminerIssue.setDownvotes(gitlabIssue.getDownvotes());
            gitminerIssue.setWebUrl(gitlabIssue.getWebUrl());
            gitminerIssue.setClosedAt(gitlabIssue.getClosedAt());
            if (gitlabAssignees != null && !gitlabAssignees.isEmpty()) {
                // GitMiner model has only 1 assignee, gitlab api returns a list that can be of length 0
                GMUser gitminerAssignee = transformUser(gitlabAssignees.get(0));
                gitminerIssue.setAssignee(gitminerAssignee);
            }

            List<GMComment> gitminerComments = transformComments(gitlabComments);
            gitminerIssue.setComments(gitminerComments);
            gitminerIssues.add(gitminerIssue);
        }
        return gitminerIssues;

    }


    public GMProject getCompleteGMProject(String id, Integer sinceCommits, Integer sinceIssues, Integer maxPages) {
        Project gitlabProject = gitLabService.findProjectById(id);
        List<Commit> gitlabCommits = gitLabService.findAllCommits(id, sinceCommits, maxPages);
        List<Issue> gitlabIssues = gitLabService.findAllIssues(id, sinceIssues, maxPages);

        GMProject gitminerProject = transformProject(gitlabProject);
        List<GMCommit> gitminerCommits = transformCommits(gitlabCommits);
        gitminerProject.setCommits(gitminerCommits);
        List<GMIssue> gitminerIssues = transformIssues(gitlabIssues);
        gitminerProject.setIssues(gitminerIssues);


        return gitminerProject;
    }
}
