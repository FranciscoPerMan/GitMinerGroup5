package aiss.gitminer.service;

import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.CommentRepository;
import aiss.gitminer.repository.CommitRepository;
import aiss.gitminer.repository.IssueRepository;
import aiss.gitminer.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GitMinerService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    CommitRepository commitRepository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    CommentRepository commentRepository;

    public Project makeNewProject(Project project)  {
        Project savedProject = new Project();
        savedProject.setId(project.getId());
        savedProject.setName(project.getName());
        savedProject.setWebUrl(project.getWebUrl());
        savedProject.setIssues(project.getIssues());
        savedProject.setCommits(project.getCommits());
        projectRepository.save(savedProject);
        return savedProject;
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project getProject(String id) throws ProjectNotFoundException {
        return projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
    }

    public List<Commit> getCommits(String email) {
        if (email == null) {
            return commitRepository.findAll();
        }
        return commitRepository.findByAuthorEmail(email);
    }

    public Commit getCommit(String id) throws CommitNotFoundException{
        Commit commit =  commitRepository.findById(id).orElseThrow(CommitNotFoundException::new);
        return commit;

    }

    public List<Issue> getIssues(String authorId, String state) {
        if (authorId == null && state == null) {
            return issueRepository.findAll();
        }
        if (authorId == null) {
            return issueRepository.findByState(state);
        }
        if (state == null) {
            return issueRepository.findByAuthorId(authorId);
        }
        return issueRepository.findByAuthorIdAndState(authorId, state);
    }

    public Issue getIssue(String id) throws IssueNotFoundException{
        return issueRepository.findById(id).orElseThrow(IssueNotFoundException::new);
    }

    public List<Comment> getIssueComments(String id) throws IssueNotFoundException {
        Issue issue =  issueRepository.findById(id).orElseThrow(IssueNotFoundException::new);
        return issue.getComments();
    }
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Comment getComment(String id) throws CommentNotFoundException {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }
}
