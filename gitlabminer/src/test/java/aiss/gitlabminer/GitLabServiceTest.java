package aiss.gitlabminer;

import aiss.gitlabminer.model.gitlab.*;
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
        assertTrue(firstCommit.getTitle() != null, "The title is null");

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
        assertTrue(firstIssue.getTitle() != null, "The title is null");

    }

    @Test
    @DisplayName("Get comments of an issue")
    void getComments() {
        List<Comment> comments = service.findIssueComments("36189", "2592");
        assertTrue(!comments.isEmpty(), "The list of comments is empty");
        System.out.println(comments);
        Comment firstComment = comments.get(0); // By default, comments (notes on the GitLab API) are sorted by created_at.
        System.out.println("Latest comment: From " + firstComment.getAuthor().getName() +
                "\nCreated at " + firstComment.getCreatedAt() + "\nBody: " + firstComment.getBody());
        assertTrue(firstComment.getBody() != null, "The body is null");
    }

    @Test
    @DisplayName("Get user by their username")
    void getUser() {
        User user = service.findUserByUsername("jjchico");
        System.out.println("Found user " + user.getName() + " (" + user.getUsername() + ")\nWeb URL: " + user.getWebUrl());
        assertTrue(user.getName() != null, "The name is null");
    }

    @Test
    @DisplayName("Get project by its ID")
    void getProject() {
        Project project = service.findProjectById("36189");
        System.out.println("Found project " + project.getName() + " (ID " + project.getId() + ")\nWeb URL: " + project.getWebUrl());
        assertTrue(project.getName() != null, "The name is null");
    }
}
