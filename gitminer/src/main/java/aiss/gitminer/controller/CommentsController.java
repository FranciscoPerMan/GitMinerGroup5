package aiss.gitminer.controller;

import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.service.GitMinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/gitminer/comments")
public class CommentsController {
    @Autowired
    GitMinerService gitMinerService;

    @GetMapping()
    public List<Comment> getComments()
    {
        return gitMinerService.getComments();
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable String id) throws CommentNotFoundException {
        return gitMinerService.getComment(id);
    }
}
