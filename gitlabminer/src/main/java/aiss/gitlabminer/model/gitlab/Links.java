
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
    "self",
    "notes",
    "award_emoji",
    "project",
    "closed_as_duplicate_of"
})
@Generated("jsonschema2pojo")
public class Links {

    @JsonProperty("self")
    private String self;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("award_emoji")
    private String awardEmoji;
    @JsonProperty("project")
    private String project;
    @JsonProperty("closed_as_duplicate_of")
    private String closedAsDuplicateOf;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("self")
    public String getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(String self) {
        this.self = self;
    }

    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    @JsonProperty("notes")
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @JsonProperty("award_emoji")
    public String getAwardEmoji() {
        return awardEmoji;
    }

    @JsonProperty("award_emoji")
    public void setAwardEmoji(String awardEmoji) {
        this.awardEmoji = awardEmoji;
    }

    @JsonProperty("project")
    public String getProject() {
        return project;
    }

    @JsonProperty("project")
    public void setProject(String project) {
        this.project = project;
    }

    @JsonProperty("closed_as_duplicate_of")
    public String getClosedAsDuplicateOf() {
        return closedAsDuplicateOf;
    }

    @JsonProperty("closed_as_duplicate_of")
    public void setClosedAsDuplicateOf(String closedAsDuplicateOf) {
        this.closedAsDuplicateOf = closedAsDuplicateOf;
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
