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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Project> getProjects(Pageable paging) {
        return projectRepository.findAll(paging);
    }

    public Project getProject(String id) throws ProjectNotFoundException {
        return projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
    }

    public Page<Commit> getCommits(String email, Pageable paging) {
        if (email == null) {
            return commitRepository.findAll(paging);
        }
        return commitRepository.findByAuthorEmail(email, paging);
    }

    public Commit getCommit(String id) throws CommitNotFoundException{
        Commit commit =  commitRepository.findById(id).orElseThrow(CommitNotFoundException::new);
        return commit;

    }

    public Page<Issue> getIssues(String authorId, String state, String title, Pageable paging) {
        if (authorId == null && state == null && title == null) {
            return issueRepository.findAll(paging);
        } else if (authorId == null && state == null) {
            return issueRepository.findByTitleContaining(paging, title);
        } else if (authorId == null && title == null) {
            return issueRepository.findByState(paging, state);
        } else if (state == null && title == null) {
            return issueRepository.findByAuthorId(paging, authorId);
        } else if (authorId == null) {
            return issueRepository.findByStateAndTitleContaining(paging, state, title);
        } else if (state == null) {
            return issueRepository.findByAuthorIdAndTitleContaining(paging, authorId, title);
        } else if (title == null) {
            return issueRepository.findByAuthorIdAndState(paging, authorId, state);
        } else {
            return issueRepository.findByAuthorIdAndStateAndTitleContaining(paging, authorId, state, title);
        }
    }

    public Issue getIssue(String id) throws IssueNotFoundException{
        return issueRepository.findById(id).orElseThrow(IssueNotFoundException::new);
    }

    public List<Comment> getIssueComments(String id) throws IssueNotFoundException {
        Issue issue =  issueRepository.findById(id).orElseThrow(IssueNotFoundException::new);
        return issue.getComments();
    }

    public Comment getComment(String id) throws CommentNotFoundException {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    public Page<Comment> getComments(Pageable paging) {
        return commentRepository.findAll(paging);
    }

    public Page<Comment> getCommentsByBody(String body, Pageable paging) {
        return commentRepository.findByBodyContaining(body, paging);
    }

    public Page<Commit> getCommitsByTitle(String email, Pageable paging, String title) {
        return commitRepository.findByAuthorEmailAndTitleContaining(email, paging, title);
    }

    public Page<Project> getProjectsByName(Pageable paging, String name) {
        return projectRepository.findByNameContaining(name, paging);
    }
}
