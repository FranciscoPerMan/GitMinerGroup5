package aiss.gitlabminer.controller;

import aiss.gitlabminer.model.gitminer.GMProject;
import aiss.gitlabminer.service.GitLabService;
import aiss.gitlabminer.service.GitMinerService;
import aiss.gitlabminer.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController()
@RequestMapping("/gitlab")
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
    public GMProject getProjects(@PathVariable String id, @RequestParam(defaultValue = "2" ) Integer sinceCommits,
                                 @RequestParam(defaultValue="20") Integer sinceIssues, @RequestParam(defaultValue="2") Integer maxPages){
        return transformerService.getCompleteGMProject(id, sinceCommits, sinceIssues, maxPages);
    }

    @PostMapping("/{id}")
    public GMProject postProjects(@PathVariable String id, @RequestParam(defaultValue = "2" ) Integer sinceCommits,
                                  @RequestParam(defaultValue="20") Integer sinceIssues, @RequestParam(defaultValue="2") Integer maxPages){
        GMProject transformed =  transformerService.getCompleteGMProject(id, sinceCommits, sinceIssues, maxPages);
        return gitMinerService.postProject(transformed);
    }


}
