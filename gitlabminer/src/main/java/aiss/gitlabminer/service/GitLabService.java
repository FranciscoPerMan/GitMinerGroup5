package aiss.gitlabminer.service;

import aiss.gitlabminer.model.gitlab.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GitLabService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${gitlab.access_token}")
    String gitlabToken;


    public List<Commit> findAllCommits(String projectId, int sinceDays, int maxPages)
            throws HttpClientErrorException {
        List<Commit> commits = new ArrayList<>();
        LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
        // First page
        String uri = "https://gitlab.com/api/v4/projects/" + projectId + "/repository/commits/?since=" +
                since.format(DateTimeFormatter.ISO_DATE_TIME);
        ResponseEntity<Commit[]> response = restTemplate.exchange(uri, HttpMethod.GET, null, Commit[].class);
        List<Commit> pageCommits = Arrays.stream(response.getBody()).toList(); // TODO: Add exception handling?
        commits.addAll(pageCommits);
        // 2..n pages
        int page = 2;
        while (page <= maxPages) {
            String nextPageURI = "https://gitlab.com/api/v4/projects/" + projectId + "/repository/commits/?since=" +
                    since.format(DateTimeFormatter.ISO_DATE_TIME) + "&page=" + page;
            response = restTemplate.exchange(nextPageURI, HttpMethod.GET, null, Commit[].class);
            // If the list of commits is empty, that means there is nothing at that page and the end has been reached.
            if (response.getBody() == null) break;
            pageCommits = Arrays.stream(response.getBody()).toList();
            commits.addAll(pageCommits);
            page++;
        }
        return commits;
    }

    public List<Issue> findAllIssues(String projectId, int sinceDays, int maxPages)
        throws HttpClientErrorException {
        List<Issue> issues = new ArrayList<>();
        LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
        // First page
        String uri = "https://gitlab.com/api/v4/projects/" + projectId + "/issues/?updated_after=" +
                since.format(DateTimeFormatter.ISO_DATE_TIME);
        ResponseEntity<Issue[]> response = restTemplate.exchange(uri, HttpMethod.GET, null, Issue[].class);
        List<Issue> pageIssues = Arrays.stream(response.getBody()).toList();
        issues.addAll(pageIssues);
        // 2..n pages
        int page = 2;
        while (page <= maxPages) {
            String nextPageURI = "https://gitlab.com/api/v4/projects/" + projectId + "/issues/?created_after=" +
                    since.format(DateTimeFormatter.ISO_DATE_TIME) + "&page=" + page;
            response = restTemplate.exchange(nextPageURI, HttpMethod.GET, null, Issue[].class);
            // If the list of issues is empty, that means there is nothing at that page and the end has been reached.
            if (response.getBody() == null) break;
            pageIssues = Arrays.stream(response.getBody()).toList();
            issues.addAll(pageIssues);
            page++;
        }
        return issues;
    }

    public List<Comment> findIssueComments(String projectId, String issueId) {
        // Endpoint requires authentication
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(gitlabToken);
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        String uri = "https://gitlab.com/api/v4/projects/" + projectId + "/issues/" + issueId + "/notes";
        ResponseEntity<Comment[]> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Comment[].class);

        return Arrays.stream(response.getBody()).toList();
    }

    public User findUserByUsername(String username) {
        String uri = "https://gitlab.com/api/v4/users?username=" + username;
        ResponseEntity<User[]> response = restTemplate.exchange(uri, HttpMethod.GET, null, User[].class);
        return Arrays.stream(response.getBody()).toList().get(0);
    }

    public Project findProjectById(String projectId) {
        // Note: without authentication, the GitLab API will return a less detailed description of a project,
        // although the extra information that would be obtained by authenticating is not needed by GitMiner.
        String uri = "https://gitlab.com/api/v4/projects/" + projectId;
        ResponseEntity<Project> response = restTemplate.exchange(uri, HttpMethod.GET, null, Project.class);
        return response.getBody();
    }
}