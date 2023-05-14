package aiss.githubminer;

import aiss.githubminer.model.comments.Comment;
import aiss.githubminer.model.commits.Commit;
import aiss.githubminer.model.issues.Issue;
import aiss.githubminer.model.projects.Project;
import aiss.githubminer.model.users.User;
import aiss.githubminer.service.GitHubService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@SpringBootTest
public class GitHubServiceTest {
    @Autowired
    GitHubService service;

    @Test
    @DisplayName("Get commits")
    void getCommits() {
        List<Commit> commits = service.findAllCommits("spring-projects", "spring-framework", 2,2);
        assertTrue(!commits.isEmpty(), "The list of commits is empty");
        // Check that it is valid
        Commit first = commits.get(0);
        assertTrue(first.getAuthor() != null, "The author is null");
        assertTrue(first.getNodeId() != null, "The node_id is null");
        assertTrue(first.getCommit() != null, "The commit of the commit is null"); //github handles it like this

    }
    @Test
    @DisplayName("Get issues and get the first one's comments")
    void getIssues() {
        List<Issue> issues = service.findAllIssues("spring-projects", "spring-framework", 2,2);
        assertTrue(!issues.isEmpty(), "The list of issues is empty");
        // Check that it is valid
        Issue first = issues.get(0);
        assertTrue(first.getComments() != null, "The comments are null");
        assertTrue(first.getCommentsUrl() != null, "The comments url is null");
        assertTrue(first.getCreatedAt() != null, "The created at is null");
        assertTrue(first.getHtmlUrl() != null, "The html url is null");

    }

    @Test
    @DisplayName("Get project")
    void getProject() {
        Project project = service.findProjectByOwnerAndRepository("spring-projects","Spring-framework");
        assertTrue(project != null, "The project is null");
        assertTrue(project.getName().equals("spring-framework"), "The project name is not spring-framework");
        assertTrue(project.getFullName().equals("spring-projects/spring-framework"), "The project full name is not spring-projects/spring-framework");
    }

    @Test
    @DisplayName("Find issue comments")
    void findIssueComments() {
        List<Comment> comments = service.findIssueComments("spring-projects","spring-framework","1");
        assertTrue(!comments.isEmpty(), "The list of comments is empty");
        // Check that it is valid
        Comment first = comments.get(0);
        assertTrue(first.getUser() != null, "The user is null");
        assertTrue(first.getHtmlUrl() != null, "The html url is null");
        assertTrue(first.getCreatedAt() != null, "The created at is null");

    }
    @Test
    @DisplayName("Find full user info by login")
    void findFullUserInfoByLogin() {
        User user = service.findUserByLogin("octocat");
        assertTrue(user != null, "The user is null");
        assertTrue(user.getLogin().equals("octocat"), "The user login is not octocat");
        assertTrue(user.getName().equals("The Octocat"), "The user name is not The Octocat");
        assertTrue(user.getAvatarUrl().contains("avatars"), "The user avatar url is not valid");
        assertTrue(user.getCompany().equals("@github"), "The user company is not @github");
    }

}
