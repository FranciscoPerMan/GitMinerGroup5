package aiss.gitlabminer.service;

import aiss.gitlabminer.model.gitminer.GMProject;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GitMinerService {
    private String gitMinerURI = "http://localhost:8080/api/projects";

    private RestTemplate restTemplate = new RestTemplate();

    public GMProject postProject(GMProject gmProject) {
        HttpEntity<GMProject> req = new HttpEntity<>(gmProject);
        GMProject res = restTemplate.postForObject(gitMinerURI, req, GMProject.class);
        return res;
    }
}
