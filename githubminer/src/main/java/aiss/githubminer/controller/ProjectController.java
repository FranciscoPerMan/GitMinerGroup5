package aiss.githubminer.controller;

import aiss.githubminer.model.gitminer.GMProject;
import aiss.githubminer.service.GitHubService;
import aiss.githubminer.service.GitMiner;
import aiss.githubminer.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/github")
public class ProjectController {


    @Autowired
    private final GitHubService gitHubService;

    @Autowired
    private final GitMiner gitMinerService;
    @Autowired
    private final TransformerService transformerService;
    public ProjectController(GitHubService gitHubService, GitMiner gitMinerService, TransformerService transformerService) {
        this.gitHubService = gitHubService;
        this.gitMinerService = gitMinerService;
        this.transformerService = transformerService;
    }
    @GetMapping("/{owner}/{repository}")
    public GMProject getProjects(@PathVariable String owner,@PathVariable String repository, @RequestParam(defaultValue = "2" ) Integer sinceCommits,
                                 @RequestParam(defaultValue="20") Integer sinceIssues, @RequestParam(defaultValue="2") Integer maxPages){
        System.out.println("Owner: " + owner+ " Repository: " + repository + " sinceCommits: " + sinceCommits + " sinceIssues: " + sinceIssues + " maxPages: " + maxPages);
        return transformerService.getCompleteGMProject(owner,repository, sinceCommits, sinceIssues, maxPages);
    }

    @PostMapping("/{owner}/{repository}")
    public GMProject postProjects(@PathVariable String owner,@PathVariable String repository, @RequestParam(defaultValue = "2" ) Integer sinceCommits,
                                  @RequestParam(defaultValue="20") Integer sinceIssues, @RequestParam(defaultValue="2") Integer maxPages){
        GMProject transformed =  transformerService.getCompleteGMProject(owner,repository, sinceCommits, sinceIssues, maxPages);
        return gitMinerService.postProject(transformed);
    }


}
