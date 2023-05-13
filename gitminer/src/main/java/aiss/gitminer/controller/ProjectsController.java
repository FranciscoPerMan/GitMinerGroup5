package aiss.gitminer.controller;

import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.*;
import aiss.gitminer.service.GitMinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/projects")
public class ProjectsController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommitRepository commitRepository;
    @Autowired
    IssueRepository issueRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    GitMinerService gitMinerService;

    // Add response from GitLabMiner or GitHubMiner to the database

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Project createProject(@RequestBody @Valid Project project) {
        return gitMinerService.makeNewProject(project);
    }

    @GetMapping()
    public List<Project> getProjects() {
        return gitMinerService.getProjects();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable String id) throws ProjectNotFoundException {
        Optional<Project> project = gitMinerService.getProject(id);
        if (!project.isPresent()) {
            throw new ProjectNotFoundException();
        }
        return project.get();
    }
}
