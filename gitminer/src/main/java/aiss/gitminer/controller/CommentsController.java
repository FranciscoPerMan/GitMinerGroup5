package aiss.gitminer.controller;

import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.service.GitMinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/gitminer/comments")
public class CommentsController {
    @Autowired
    GitMinerService gitMinerService;

    @GetMapping()
    public List<Comment> getComments(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(required = false) String order,
                                     @RequestParam(required = false) String body) {
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

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable String id) throws CommentNotFoundException {
        return gitMinerService.getComment(id);
    }
}
