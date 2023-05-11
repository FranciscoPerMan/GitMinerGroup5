package aiss.gitminer.controller;

import aiss.gitminer.model.Project;
import aiss.gitminer.repository.*;
import aiss.gitminer.service.GitMinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api") // TODO: Change
public class GMController {
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
    @PostMapping("/projects")
    public Project createProject(@RequestBody @Valid Project project) {
        return gitMinerService.makeNewProject(project);
    }
}
