package aiss.githubminer.service;

import aiss.githubminer.model.gitminer.GMProject;
import aiss.githubminer.model.projects.Project;

public class TranformerService {
    public GMProject fromGitlabToGitminerModel(Project githubProject) {
        GMProject gitminerProject = new GMProject();
        gitminerProject.setName(githubProject.getName());
        gitminerProject.setWebUrl(githubProject.getUrl());
        return gitminerProject;
    }
}
