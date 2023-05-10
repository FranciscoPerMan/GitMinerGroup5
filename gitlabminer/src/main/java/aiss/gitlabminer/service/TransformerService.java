package aiss.gitlabminer.service;

import aiss.gitlabminer.model.gitlab.Project;
import aiss.gitlabminer.model.gitminer.GMProject;

public class TransformerService {
    public GMProject fromGitlabToGitminerModel(Project gitlabProject) {
        GMProject gitminerProject = new GMProject();
        gitminerProject.setName(gitlabProject.getName());
        gitminerProject.setWebUrl(gitlabProject.getWebUrl());
        return gitminerProject;
    }
}
