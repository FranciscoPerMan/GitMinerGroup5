package aiss.gitminer.controller;

import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.*;
import aiss.gitminer.service.GitMinerService;
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
    public List<Project> getProjects(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(required = false) String order,
                                     @RequestParam(required = false) String name) {
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

    @GetMapping("/{id}")
    public Project getProject(@PathVariable String id) throws ProjectNotFoundException {
        Project project = gitMinerService.getProject(id);
        return project;
    }
}
