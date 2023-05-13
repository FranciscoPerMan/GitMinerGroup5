package aiss.gitminer.service;

import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.CommitRepository;
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

    public Optional<Project> getProject(String id) {
        return projectRepository.findById(id);
    }

    public List<Commit> getCommits(String email) {
        if (email == null) {
            return commitRepository.findAll();
        }
        return commitRepository.findByAuthorEmail(email);
    }

    public Optional<Commit> getCommit(String id) {
        return commitRepository.findById(id);
    }
}
