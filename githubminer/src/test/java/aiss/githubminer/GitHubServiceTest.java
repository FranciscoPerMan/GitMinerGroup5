package aiss.githubminer;

import aiss.githubminer.model.commits.Commit;
import aiss.githubminer.model.issues.Issue;
import aiss.githubminer.model.projects.Project;
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
        List<Commit> commits = service.findAllCommits("spring-projects", "spring-framework", 2,2); // F-Droid Client
        assertTrue(!commits.isEmpty(), "The list of commits is empty");
        System.out.println(commits);
    }
    @Test
    @DisplayName("Get issues")
    void getIssues() {
        List<Issue> issues = service.findAllIssues("spring-projects", "spring-framework", 2,2); // F-Droid Client
        assertTrue(!issues.isEmpty(), "The list of issues is empty");
        System.out.println(issues);
    }

    @Test
    @DisplayName("Get proyect")
    void getProject() {
        Project project = service.findProjectByOwnerAndRepository("spring-projects","Spring-framework");
        System.out.println(project.toString());
    }
}
