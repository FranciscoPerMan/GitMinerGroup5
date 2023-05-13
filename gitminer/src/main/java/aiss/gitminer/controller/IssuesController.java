
package aiss.gitminer.controller;


import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;
import aiss.gitminer.service.GitMinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/issues")
public class IssuesController {
    @Autowired
    GitMinerService gitMinerService;

    @GetMapping()
    public List<Issue> getIssues(@RequestParam(required = false) String authorId
            ,@RequestParam(required = false) String issueId)
    {
        return gitMinerService.getIssues(authorId, issueId);
    }

    @GetMapping("/{id}")
    public Issue getIssue(@PathVariable String id) throws IssueNotFoundException {
        return gitMinerService.getIssue(id);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@PathVariable String id) throws IssueNotFoundException {
        return gitMinerService.getIssueComments(id);
    }

}
