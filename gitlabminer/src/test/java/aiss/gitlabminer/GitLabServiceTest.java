package aiss.gitlabminer;

import aiss.gitlabminer.model.Commit;
import aiss.gitlabminer.service.GitLabService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@SpringBootTest
class GitLabServiceTest {
    @Autowired
    GitLabService service;

    @Test
    @DisplayName("Get commits")
    void getCommits() {
        List<Commit> commits = service.findAllCommits("36189", 30, 2); // F-Droid Client
        assertTrue(!commits.isEmpty(), "The list of commits is empty");
        System.out.println(commits);
        Commit firstCommit = commits.get(0);
        System.out.println("Latest commit:\nFrom " + firstCommit.getCommitterName() + "\nAuthored at " +
                firstCommit.getCommittedDate() + "\nTitle: " + firstCommit.getTitle() + "\nWeb URL: " +
                firstCommit.getWebUrl());
    }

}
