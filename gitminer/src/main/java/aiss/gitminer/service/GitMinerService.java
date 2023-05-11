package aiss.gitminer.service;

import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitMinerService {
    @Autowired
    ProjectRepository projectRepository;

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
}
