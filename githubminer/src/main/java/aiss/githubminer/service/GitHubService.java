package aiss.githubminer.service;

import aiss.githubminer.model.issues.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import aiss.githubminer.model.commits.Commit;

@Service
public class GitHubService {
    @Autowired
    RestTemplate restTemplate;


    public List<Commit> findAllCommits(String owner, String repository, Integer sinceDays, Integer maxPages){
        List<Commit> commits = new ArrayList<>();
        LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
        String url ="https://api.github.com/repos/" + owner+ "/" + repository + "/commits?since=" +
        since.format(DateTimeFormatter.ISO_DATE_TIME);;
        ResponseEntity<Commit[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Commit[].class);
        List<Commit> pageCommits = Arrays.stream(response.getBody()).toList(); // TODO: Add exception handling?
        commits.addAll(pageCommits);
        int page = 2;
        while (page <= maxPages) {
            String nextPageURI = "https://api.github.com/repos/" + owner+ "/" + repository + "/commits?since=" +
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
    public List<Issue> findAllIssues(String owner, String repository, Integer sinceDays, Integer maxPages){
        List<Issue> issues = new ArrayList<>();
        LocalDateTime since = LocalDateTime.now().minusDays(sinceDays);
        String url ="https://api.github.com/repos/" + owner+ "/" + repository + "/commits?since=" +
                since.format(DateTimeFormatter.ISO_DATE_TIME);;
        ResponseEntity<Issue[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Issue[].class);
        List<Issue> pageIssues = Arrays.stream(response.getBody()).toList(); // TODO: Add exception handling?
        issues.addAll(pageIssues);
        int page = 2;
        while (page <= maxPages) {
            String nextPageURI = "https://api.github.com/repos/" + owner+ "/" + repository + "/commits?since=" +
                    since.format(DateTimeFormatter.ISO_DATE_TIME) + "&page=" + page;
            response = restTemplate.exchange(nextPageURI, HttpMethod.GET, null, Issue[].class);
            // If the list of commits is empty, that means there is nothing at that page and the end has been reached.
            if (response.getBody() == null) break;
            pageIssues = Arrays.stream(response.getBody()).toList();
            issues.addAll(pageIssues);
            page++;
        }
        return issues;
    }

}
