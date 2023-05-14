package aiss.githubminer;


import aiss.githubminer.model.gitminer.GMCommit;
import aiss.githubminer.model.gitminer.GMIssue;
import aiss.githubminer.model.gitminer.GMProject;
import aiss.githubminer.service.TransformerService;
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
    @DisplayName("Get complete GM project") // We only test the public method getCompleteGMProject() because it calls the other private methods.
    void testGetCompleteGMProjectFullParams(){
        GMProject gmProject = transformerService.getCompleteGMProject("spring-projects", "spring-framework", 100, 10, 10);
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
        assertTrue(!id.isEmpty(), "The id is empty");
        // Test webUrl
        String webUrl = gmProject.getWebUrl();
        assertTrue(!webUrl.isEmpty(), "The web url is empty");
    }


}
