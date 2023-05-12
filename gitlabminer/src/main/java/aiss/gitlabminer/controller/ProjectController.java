package aiss.gitlabminer.controller;

import aiss.gitlabminer.model.gitminer.GMProject;
import aiss.gitlabminer.service.GitLabService;
import aiss.gitlabminer.service.GitMinerService;
import aiss.gitlabminer.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/projects")
public class ProjectController {


    @Autowired
    private final GitLabService gitLabService;

    @Autowired
    private final GitMinerService gitMinerService;
    @Autowired
    private final TransformerService transformerService;
    public ProjectController(GitLabService gitLabService, GitMinerService gitMinerService, TransformerService transformerService) {
        this.gitLabService = gitLabService;
        this.gitMinerService = gitMinerService;
        this.transformerService = transformerService;
    }
    @GetMapping("/{id}")
    public GMProject getProjects(@PathVariable String id) {
        return transformerService.getCompleteGMProject(id);
    }

    @PostMapping("/{id}")
    public GMProject postProjects(@PathVariable String id) {
        GMProject transformed =  transformerService.getCompleteGMProject(id);
        return gitMinerService.postProject(transformed);
    }


}
