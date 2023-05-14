package aiss.gitminer.controller;


import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Commit;
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
@RequestMapping("/gitminer/commits")
public class CommitsController {
    @Autowired
    GitMinerService gitMinerService;

    @GetMapping()
    public List<Commit> getCommits(@RequestParam(required = false) String email,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "20") int size,
                                   @RequestParam(required = false) String order,
                                   @RequestParam(required = false) String title) {
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

    @GetMapping("/{id}")
    public Commit getCommit(@PathVariable String id) throws CommitNotFoundException {
        Commit commit = gitMinerService.getCommit(id);
        return commit;
    }

}
