package aiss.gitminer.controller;


import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Project;
import aiss.gitminer.service.GitMinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/commits") // TODO: Change
public class CommitsController {
    @Autowired
    GitMinerService gitMinerService;

    @GetMapping()
    public List<Commit> getCommits(@RequestParam(required = false) String email) {
        return gitMinerService.getCommits(email);
    }

    @GetMapping("/{id}")
    public Commit getCommit(@PathVariable String id) throws CommitNotFoundException {
        Commit commit = gitMinerService.getCommit(id);
        return commit;
    }

}
