
package aiss.gitminer.controller;


import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Issues", description = "Issues API")
@RestController
@RequestMapping("/gitminer/issues")
public class IssuesController {
    @Autowired
    GitMinerService gitMinerService;


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Issue found", content = @Content(schema = @Schema(implementation = Issue.class), mediaType = "application/json"))
    })
    @Operation(summary = "Get all issues",
            description = "Get all issues from the database. You can filter them by authorId, issueId, title and order them by any field. You can also paginate them.",
            tags = {"Issues"})
    @GetMapping()
    public List<Issue> getIssues(@Parameter(description = "id of the author") @RequestParam(required = false) String authorId,
                                 @Parameter(description = "id of the issue") @RequestParam(required = false) String issueId,
                                 @Parameter(description = "page number") @RequestParam(defaultValue = "0") int page,
                                 @Parameter(description = "page size") @RequestParam(defaultValue = "20") int size,
                                 @Parameter(description = "order by field, can be negative") @RequestParam(required = false) String order,
                                 @Parameter(description = "title of the issue") @RequestParam(required = false) String title) {
        Page<Issue> pageIssues;
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
        pageIssues = gitMinerService.getIssues(authorId, issueId, title, paging);
        return pageIssues.getContent();
    }

    @Operation(summary = "Get an issue",
            description = "Get an issue from the database by its id.",
            tags = {"Issues"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Issue found", content = @Content(schema = @Schema(implementation = Issue.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Issue not found", content = @Content(schema = @Schema(), mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public Issue getIssue(@Parameter(description = "id of the issue") @PathVariable String id) throws IssueNotFoundException {
        return gitMinerService.getIssue(id);
    }

    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Issue + comments found", content = @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Issue not found", content = @Content(schema = @Schema(), mediaType = "application/json"))
    })
    @Operation(summary = "Get all comments from an issue",
            description = "Get all comments from an issue from the database by its id.",
            tags = {"Issues"})
    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@Parameter(description = "id of the issue") @PathVariable String id) throws IssueNotFoundException {
        return gitMinerService.getIssueComments(id);
    }

}
