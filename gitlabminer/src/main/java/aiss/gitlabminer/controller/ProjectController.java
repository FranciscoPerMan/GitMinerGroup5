package aiss.gitlabminer.controller;

import aiss.gitlabminer.model.gitlab.Project;
import aiss.gitlabminer.model.gitminer.GMProject;
import aiss.gitlabminer.service.GitLabService;
import aiss.gitlabminer.service.TransformerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/projects")
public class ProjectController {

    private final GitLabService gitLabService;
    private final TransformerService transformerService;
    public ProjectController(GitLabService gitLabService, TransformerService transformerService) {
        this.gitLabService = gitLabService;
        this.transformerService = transformerService;
    }
    @GetMapping("/{id}")
    public GMProject getProjects(@PathVariable String id) {
        return transformerService.getCompleteGMProject(id);
    }


}
