
package aiss.githubminer.model.gitminer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;



public class GMProject {

    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("web_url")
    public String webUrl;
    @JsonProperty("commits")

    private List<GMCommit> GMCommits;

    @JsonProperty("issues")

    private List<GMIssue> GMIssues;

    public GMProject() {
        GMCommits = new ArrayList<>();
        GMIssues = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public List<GMCommit> getCommits() {
        return GMCommits;
    }

    public void setCommits(List<GMCommit> GMCommits) {
        this.GMCommits = GMCommits;
    }

    public List<GMIssue> getIssues() {
        return GMIssues;
    }

    public void setIssues(List<GMIssue> GMIssues) {
        this.GMIssues = GMIssues;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GMProject.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("commits");
        sb.append('=');
        sb.append(((this.GMCommits == null)?"<null>":this.GMCommits));
        sb.append(',');
        sb.append("issues");
        sb.append('=');
        sb.append(((this.GMIssues == null)?"<null>":this.GMIssues));
        sb.append(',');

        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
