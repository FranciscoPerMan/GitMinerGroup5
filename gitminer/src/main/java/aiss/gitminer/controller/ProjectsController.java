package aiss.gitminer.controller;

import aiss.gitminer.exception.ProjectAlreadyExistsException;
import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.*;
import aiss.gitminer.service.GitMinerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Tag(name = "Projects", description = "Projects API")
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

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Project created", content = @Content(schema = @Schema(implementation = Project.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Project already exists", content = @Content(schema = @Schema(), mediaType = "application/json"))
    })
    @Operation(summary = "Create a new project",
            description = "Create a new project in the database.",
            tags = {"Projects"})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Project createProject(@RequestBody @Valid Project project) throws ProjectAlreadyExistsException {
        return gitMinerService.makeNewProject(project);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project updated", content = @Content(schema = @Schema(implementation = Project.class), mediaType = "application/json")),
    })
    @Operation(summary = "Get all projects",
            description = "Get all projects from the database. You can filter them by name and order them by any field. You can also paginate them.",
            tags = {"Projects"})
    @GetMapping()
    public List<Project> getProjects( @Parameter(description = "Name of the project") @RequestParam(required = false) String name,
                                      @Parameter(description = "Page number") @RequestParam(required = false, defaultValue = "0") Integer page,
                                      @Parameter(description = "Page size") @RequestParam(required = false, defaultValue = "10") Integer size,
                                      @Parameter(description = "Order by field, can be negative") @RequestParam(required = false) String order)
            {
        Page<Project> pageProjects;
        Pageable paging;
        if (order != null) {
            if (order.startsWith("-")) {
                paging = PageRequest.of(page, size, Sort.by(order.substring(1)).descending());
            } else {
                paging = PageRequest.of(page, size, Sort.by(order).ascending());
            }
        } else {
            paging = PageRequest.of(page, size);
        }
        if (name != null) {
            pageProjects = gitMinerService.getProjectsByName(paging, name);
        } else {
            pageProjects = gitMinerService.getProjects(paging);
        }
        return pageProjects.getContent();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Everything went well", content = @Content(schema = @Schema(implementation = Project.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Project not found", content = @Content(schema = @Schema(), mediaType = "application/json"))
    })
    @Operation(summary = "Get a project",
            description = "Get a project from the database by its id.",
            tags = {"Projects"})
    @GetMapping("/{id}")
    public Project getProject(@Parameter(description = "id of the project") @PathVariable String id) throws ProjectNotFoundException {
        Project project = gitMinerService.getProject(id);
        return project;
    }
}
