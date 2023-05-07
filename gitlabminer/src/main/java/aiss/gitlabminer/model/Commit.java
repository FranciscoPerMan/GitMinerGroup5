package aiss.gitlabminer.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "short_id",
        "title",
        "author_name",
        "author_email",
        "authored_date",
        "committer_name",
        "committer_email",
        "committed_date",
        "created_at",
        "message",
        "parent_ids",
        "web_url"
})
@Generated("jsonschema2pojo")
public class Commit {

    @JsonProperty("id")
    private String id;
    @JsonProperty("short_id")
    private String shortId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("author_name")
    private String authorName;
    @JsonProperty("author_email")
    private String authorEmail;
    @JsonProperty("authored_date")
    private String authoredDate;
    @JsonProperty("committer_name")
    private String committerName;
    @JsonProperty("committer_email")
    private String committerEmail;
    @JsonProperty("committed_date")
    private String committedDate;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("message")
    private String message;
    @JsonProperty("parent_ids")
    private List<String> parentIds;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("short_id")
    public String getShortId() {
        return shortId;
    }

    @JsonProperty("short_id")
    public void setShortId(String shortId) {
        this.shortId = shortId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("author_name")
    public String getAuthorName() {
        return authorName;
    }

    @JsonProperty("author_name")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @JsonProperty("author_email")
    public String getAuthorEmail() {
        return authorEmail;
    }

    @JsonProperty("author_email")
    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    @JsonProperty("authored_date")
    public String getAuthoredDate() {
        return authoredDate;
    }

    @JsonProperty("authored_date")
    public void setAuthoredDate(String authoredDate) {
        this.authoredDate = authoredDate;
    }

    @JsonProperty("committer_name")
    public String getCommitterName() {
        return committerName;
    }

    @JsonProperty("committer_name")
    public void setCommitterName(String committerName) {
        this.committerName = committerName;
    }

    @JsonProperty("committer_email")
    public String getCommitterEmail() {
        return committerEmail;
    }

    @JsonProperty("committer_email")
    public void setCommitterEmail(String committerEmail) {
        this.committerEmail = committerEmail;
    }

    @JsonProperty("committed_date")
    public String getCommittedDate() {
        return committedDate;
    }

    @JsonProperty("committed_date")
    public void setCommittedDate(String committedDate) {
        this.committedDate = committedDate;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("parent_ids")
    public List<String> getParentIds() {
        return parentIds;
    }

    @JsonProperty("parent_ids")
    public void setParentIds(List<String> parentIds) {
        this.parentIds = parentIds;
    }

    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
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
