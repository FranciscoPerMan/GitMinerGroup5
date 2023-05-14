package aiss.gitlabminer.controller;

import aiss.gitlabminer.model.gitlab.Project;
import aiss.gitlabminer.model.gitminer.GMProject;
import aiss.gitlabminer.service.GitLabService;
import aiss.gitlabminer.service.GitMinerService;
import aiss.gitlabminer.service.TransformerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Tag(name = "Project", description = "GitMiner Project controller for GitLab")
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project created", content = @Content(schema = @Schema(implementation = Project.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Project was not found on GitLab", content = @Content(schema = @Schema(), mediaType = "application/json"))
    })
    @Operation(summary = "Get project",
            description = "Get a GitLab project in the format of a GitMiner project. You can limit the number of issues " +
                    "and commits you obtain from the repository with the sinceCommits, sinceIssues and maxPages parameters.",
            tags = {"Projects"})
    @GetMapping("/{id}")
    public GMProject getProjects(@PathVariable String id, @RequestParam(defaultValue = "2" ) Integer sinceCommits,
                                 @RequestParam(defaultValue="20") Integer sinceIssues, @RequestParam(defaultValue="2") Integer maxPages){
        return transformerService.getCompleteGMProject(id, sinceCommits, sinceIssues, maxPages);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project created", content = @Content(schema = @Schema(implementation = Project.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Project was not found on GitLab", content = @Content(schema = @Schema(), mediaType = "application/json"))
    })
    @Operation(summary = "Create a new project in GitMiner",
            description = "Take the data of an existing GitLab project to create a new project in the GitMiner database. You can limit the number of issues " +
                    "and commits you obtain from the repository with the sinceCommits, sinceIssues and maxPages parameters.",
            tags = {"Projects"})
    @PostMapping("/{id}")
    public GMProject postProjects(@PathVariable String id, @RequestParam(defaultValue = "2" ) Integer sinceCommits,
                                  @RequestParam(defaultValue="20") Integer sinceIssues, @RequestParam(defaultValue="2") Integer maxPages){
        GMProject transformed =  transformerService.getCompleteGMProject(id, sinceCommits, sinceIssues, maxPages);
        return gitMinerService.postProject(transformed);
    }


}
