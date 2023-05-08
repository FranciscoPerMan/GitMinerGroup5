
package aiss.githubminer.model.commits;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Parent {

    @JsonProperty("sha")
    private String sha;
    @JsonProperty("url")
    private String url;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sha")
    public String getSha() {
        return sha;
    }

    @JsonProperty("sha")
    public void setSha(String sha) {
        this.sha = sha;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }

    @JsonProperty("html_url")
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
