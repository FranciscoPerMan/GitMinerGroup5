package aiss.gitlabminer;

import aiss.gitlabminer.model.gitlab.Issue;
import aiss.gitlabminer.model.gitminer.GMCommit;
import aiss.gitlabminer.model.gitminer.GMIssue;
import aiss.gitlabminer.model.gitminer.GMProject;
import aiss.gitlabminer.service.TransformerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@SpringBootTest
public class TransformerServiceTest {

    @Autowired
    TransformerService transformerService;
    @Test
    @DisplayName("Get complete GM project")
    void testGetCompleteGMProject(){
        GMProject gmProject = transformerService.getCompleteGMProject("36189");
        // Test issues
        List<GMIssue> issues = gmProject.getIssues();
        assertTrue(!issues.isEmpty(), "The list of issues is empty");
        // Test commits
        List<GMCommit> commits = gmProject.getCommits();
        assertTrue(!commits.isEmpty(), "The list of commits is empty");
        //Test name
        String name = gmProject.getName();
        assertTrue(!name.isEmpty(), "The name is empty");
        // Test id
        String id = gmProject.getId();
        assertTrue(id.equals("36189"), "The id is empty");
    }
}
