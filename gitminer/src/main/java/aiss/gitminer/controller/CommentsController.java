package aiss.gitminer.controller;

import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.model.Comment;
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


@Tag(name = "Comments", description = "Comments API")
@RestController
@RequestMapping("/gitminer/comments")
public class CommentsController {
    @Autowired
    GitMinerService gitMinerService;

    @Operation(summary = "Get all comments",
            description = "Get all comments from the database. You can filter them by body and order them by any field. You can also paginate them.",
            tags = {"Comments"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comments found", content = @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")),
    })

    @GetMapping()
    public List<Comment> getComments(@Parameter(description = "page number") @RequestParam(defaultValue = "0") int page,
                                     @Parameter(description = "page size") @RequestParam(defaultValue = "20") int size,
                                     @Parameter(description = "order by field, can be negative") @RequestParam(required = false) String order,
                                     @Parameter(description = "body of the comment") @RequestParam(required = false) String body) {
        Page<Comment> pageComments;
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
        if (body != null) {
            pageComments = gitMinerService.getCommentsByBody(body, paging);
        } else {
            pageComments = gitMinerService.getComments(paging);
        }
        return pageComments.getContent();
    }

    @Operation(summary = "Get a comment",
            description = "Get a comment from the database by its id.",
            tags = {"Comments"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comment found", content = @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content(schema = @Schema(), mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public Comment getComment(@Parameter(description="id of @PathVariable String id) throws CommentNotFoundException") @PathVariable String id) throws CommentNotFoundException {
        return gitMinerService.getComment(id);
    }
}
