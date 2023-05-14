package aiss.githubminer.controller;

import aiss.githubminer.model.gitminer.GMProject;
import aiss.githubminer.model.projects.Project;
import aiss.githubminer.service.GitHubService;
import aiss.githubminer.service.GitMiner;
import aiss.githubminer.service.TransformerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Project", description = "GitMiner Project controller for GitHub")
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

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project created", content = @Content(schema = @Schema(implementation = Project.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Project was not found on GitHub", content = @Content(schema = @Schema(), mediaType = "application/json"))
    })
    @Operation(summary = "Get project",
            description = "Get a GitHub project in the format of a GitMiner project. You can limit the number of issues " +
                    "and commits you obtain from the repository with the sinceCommits, sinceIssues and maxPages parameters.",
            tags = {"Projects"})
    @GetMapping("/{owner}/{repository}")
    public GMProject getProjects(@PathVariable String owner,@PathVariable String repository, @RequestParam(defaultValue = "2" ) Integer sinceCommits,
                                 @RequestParam(defaultValue="20") Integer sinceIssues, @RequestParam(defaultValue="2") Integer maxPages){

        return transformerService.getCompleteGMProject(owner,repository, sinceCommits, sinceIssues, maxPages);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project created", content = @Content(schema = @Schema(implementation = Project.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Project was not found on GitHub", content = @Content(schema = @Schema(), mediaType = "application/json"))
    })
    @Operation(summary = "Create a new project in GitMiner",
            description = "Take the data of an existing GitHub project to create a new project in the GitMiner database. You can limit the number of issues " +
                    "and commits you obtain from the repository with the sinceCommits, sinceIssues and maxPages parameters.\"",
            tags = {"Projects"})
    @PostMapping("/{owner}/{repository}")
    public GMProject postProjects(@PathVariable String owner,@PathVariable String repository, @RequestParam(defaultValue = "2" ) Integer sinceCommits,
                                  @RequestParam(defaultValue="20") Integer sinceIssues, @RequestParam(defaultValue="2") Integer maxPages){
        GMProject transformed =  transformerService.getCompleteGMProject(owner,repository, sinceCommits, sinceIssues, maxPages);
        return gitMinerService.postProject(transformed);
    }


}
