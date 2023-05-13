
package aiss.gitminer.controller;


import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;
import aiss.gitminer.service.GitMinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/issues")
public class IssuesController {
    @Autowired
    GitMinerService gitMinerService;

    @GetMapping()
    public List<Issue> getIssues(@RequestParam(required = false) String authorId,
                                 @RequestParam(required = false) String issueId,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "20") int size,
                                 @RequestParam(required = false) String order,
                                 @RequestParam(required = false) String title) {
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

    @GetMapping("/{id}")
    public Issue getIssue(@PathVariable String id) throws IssueNotFoundException {
        return gitMinerService.getIssue(id);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@PathVariable String id) throws IssueNotFoundException {
        return gitMinerService.getIssueComments(id);
    }

}
