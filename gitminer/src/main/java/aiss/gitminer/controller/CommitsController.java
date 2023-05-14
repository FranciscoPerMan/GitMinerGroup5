package aiss.gitminer.controller;


import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Project;
import aiss.gitminer.service.GitMinerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Commits", description = "Commits API")
@RestController
@RequestMapping("/gitminer/commits")
public class CommitsController {
    @Autowired
    GitMinerService gitMinerService;


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Commits found", content = @Content(schema = @Schema(implementation = Commit.class), mediaType = "application/json")),
    })
    @Operation(summary = "Get all commits",
            description = "Get all commits from the database. You can filter them by authorId, commitId, title and order them by any field. You can also paginate them.",
            tags = {"Commits"})
    @GetMapping()
    public List<Commit> getCommits(@Parameter(description = "id of the author") @RequestParam(required = false) String email,
                                   @Parameter(description = "id of the commit") @RequestParam(required = false) String commitId,
                                   @Parameter(description = "page number") @RequestParam(defaultValue = "0") int page,
                                   @Parameter(description = "page size") @RequestParam(defaultValue = "20") int size,
                                   @Parameter(description = "order by field, can be negative") @RequestParam(required = false) String order,
                                   @Parameter(description = "title of the commit") @RequestParam(required = false) String title) {
        Page<Commit> pageCommits;
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
        if (title != null) {
            pageCommits = gitMinerService.getCommitsByTitle(email, paging, title);
        } else {
            pageCommits = gitMinerService.getCommits(email, paging);
        }
        return pageCommits.getContent();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Commit found", content = @Content(schema = @Schema(implementation = Commit.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Commit not found", content = @Content(schema = @Schema()))
    })
    @Operation(summary = "Get a commit",
            description = "Get a commit from the database by its id.",
            tags = {"Commits"})
    @GetMapping("/{id}")
    public Commit getCommit(@Parameter(description="id of the commit")@PathVariable String id) throws CommitNotFoundException {
        Commit commit = gitMinerService.getCommit(id);
        return commit;
    }

}
