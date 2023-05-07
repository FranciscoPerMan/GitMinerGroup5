package aiss.gitlabminer;

import aiss.gitlabminer.model.Commit;
import aiss.gitlabminer.model.Issue;
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

    @Test
    @DisplayName("Get issues")
    void getIssues() {
        List<Issue> issues = service.findAllIssues("36189", 30, 2); // F-Droid Client
        assertTrue(!issues.isEmpty(), "The list of issues is empty");
        System.out.println(issues);
        Issue firstIssue = issues.get(0);
        System.out.println("Latest issue:\nFrom " + firstIssue.getAuthor().getName() +
                "\nOpened at " + firstIssue.getCreatedAt() + "\nTitle: " + firstIssue.getTitle() +
                "\nWeb URL: " + firstIssue.getWebUrl() + "\nUpvotes: " + firstIssue.getUpvotes());
    }
}
