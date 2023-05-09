package aiss.gitlabminer.model.gitlab;

import java.util.LinkedHashMap;
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
        "body",
        "attachment",
        "author",
        "created_at",
        "updated_at",
        "system",
        "noteable_id",
        "noteable_type",
        "project_id",
        "noteable_iid",
        "resolvable",
        "confidential",
        "internal"
})
@Generated("jsonschema2pojo")
public class Comment {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("body")
    private String body;
    @JsonProperty("attachment")
    private Object attachment;
    @JsonProperty("author")
    private User author;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("system")
    private Boolean system;
    @JsonProperty("noteable_id")
    private Integer noteableId;
    @JsonProperty("noteable_type")
    private String noteableType;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("noteable_iid")
    private Integer noteableIid;
    @JsonProperty("resolvable")
    private Boolean resolvable;
    @JsonProperty("confidential")
    private Boolean confidential;
    @JsonProperty("internal")
    private Boolean internal;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("attachment")
    public Object getAttachment() {
        return attachment;
    }

    @JsonProperty("attachment")
    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }

    @JsonProperty("author")
    public User getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(User author) {
        this.author = author;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("system")
    public Boolean getSystem() {
        return system;
    }

    @JsonProperty("system")
    public void setSystem(Boolean system) {
        this.system = system;
    }

    @JsonProperty("noteable_id")
    public Integer getNoteableId() {
        return noteableId;
    }

    @JsonProperty("noteable_id")
    public void setNoteableId(Integer noteableId) {
        this.noteableId = noteableId;
    }

    @JsonProperty("noteable_type")
    public String getNoteableType() {
        return noteableType;
    }

    @JsonProperty("noteable_type")
    public void setNoteableType(String noteableType) {
        this.noteableType = noteableType;
    }

    @JsonProperty("project_id")
    public Integer getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("noteable_iid")
    public Integer getNoteableIid() {
        return noteableIid;
    }

    @JsonProperty("noteable_iid")
    public void setNoteableIid(Integer noteableIid) {
        this.noteableIid = noteableIid;
    }

    @JsonProperty("resolvable")
    public Boolean getResolvable() {
        return resolvable;
    }

    @JsonProperty("resolvable")
    public void setResolvable(Boolean resolvable) {
        this.resolvable = resolvable;
    }

    @JsonProperty("confidential")
    public Boolean getConfidential() {
        return confidential;
    }

    @JsonProperty("confidential")
    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
    }

    @JsonProperty("internal")
    public Boolean getInternal() {
        return internal;
    }

    @JsonProperty("internal")
    public void setInternal(Boolean internal) {
        this.internal = internal;
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
