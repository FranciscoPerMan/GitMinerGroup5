package aiss.gitlabminer.service;

import aiss.gitlabminer.model.commits.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GitHubService {
    @Autowired
    RestTemplate restTemplate;


    public List<Commit> findAllCommits(String owner, String repository){
        List<Commit> commits = new ArrayList<>();
        String url ="https://api.github.com/repos/" + owner+ "/" + repository + "/commits";
        Commit[] commitSearch= restTemplate.getForObject(url,Commit[].class);
        commits = Arrays.stream(commitSearch).toList();
        return commits;
    }

    public List<Commit>
}
