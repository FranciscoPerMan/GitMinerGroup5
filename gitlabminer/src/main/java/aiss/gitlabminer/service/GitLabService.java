package aiss.gitlabminer.service;

import aiss.gitlabminer.model.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GitLabService {
    @Autowired
    RestTemplate restTemplate;

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
            // If the list of commits is null, that means there is nothing at that page and the end has been reached.
            if (response.getBody() == null) break;
            pageCommits = Arrays.stream(response.getBody()).toList();
            commits.addAll(pageCommits);
            page++;
        }
        return commits;
    }
}