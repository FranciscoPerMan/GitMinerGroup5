
package aiss.gitlabminer.model.gitlab;

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
    "state",
    "description",
    "author",
    "milestone",
    "project_id",
    "assignees",
    "assignee",
    "type",
    "updated_at",
    "closed_at",
    "closed_by",
    "id",
    "title",
    "created_at",
    "moved_to_id",
    "iid",
    "labels",
    "upvotes",
    "downvotes",
    "merge_requests_count",
    "user_notes_count",
    "due_date",
    "web_url",
    "references",
    "time_stats",
    "has_tasks",
    "task_status",
    "confidential",
    "discussion_locked",
    "issue_type",
    "severity",
    "_links",
    "task_completion_status"
})
@Generated("jsonschema2pojo")
public class Issue {

    @JsonProperty("state")
    private String state;
    @JsonProperty("description")
    private String description;
    @JsonProperty("author")
    private User author;
    @JsonProperty("milestone")
    private Milestone milestone;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("assignees")
    private List<User> users;
    @JsonProperty("assignee")
    private User assignee;
    @JsonProperty("type")
    private String type;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("closed_at")
    private Object closedAt;
    @JsonProperty("closed_by")
    private Object closedBy;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("moved_to_id")
    private Object movedToId;
    @JsonProperty("iid")
    private Integer iid;
    @JsonProperty("labels")
    private List<String> labels;
    @JsonProperty("upvotes")
    private Integer upvotes;
    @JsonProperty("downvotes")
    private Integer downvotes;
    @JsonProperty("merge_requests_count")
    private Integer mergeRequestsCount;
    @JsonProperty("user_notes_count")
    private Integer userNotesCount;
    @JsonProperty("due_date")
    private String dueDate;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("references")
    private References references;
    @JsonProperty("time_stats")
    private TimeStats timeStats;
    @JsonProperty("has_tasks")
    private Boolean hasTasks;
    @JsonProperty("task_status")
    private String taskStatus;
    @JsonProperty("confidential")
    private Boolean confidential;
    @JsonProperty("discussion_locked")
    private Boolean discussionLocked;
    @JsonProperty("issue_type")
    private String issueType;
    @JsonProperty("severity")
    private String severity;
    @JsonProperty("_links")
    private Links links;
    @JsonProperty("task_completion_status")
    private TaskCompletionStatus taskCompletionStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("author")
    public User getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(User author) {
        this.author = author;
    }

    @JsonProperty("milestone")
    public Milestone getMilestone() {
        return milestone;
    }

    @JsonProperty("milestone")
    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    @JsonProperty("project_id")
    public Integer getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("assignees")
    public List<User> getAssignees() {
        return users;
    }

    @JsonProperty("assignees")
    public void setAssignees(List<User> users) {
        this.users = users;
    }

    @JsonProperty("assignee")
    public User getAssignee() {
        return assignee;
    }

    @JsonProperty("assignee")
    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("closed_at")
    public Object getClosedAt() {
        return closedAt;
    }

    @JsonProperty("closed_at")
    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }

    @JsonProperty("closed_by")
    public Object getClosedBy() {
        return closedBy;
    }

    @JsonProperty("closed_by")
    public void setClosedBy(Object closedBy) {
        this.closedBy = closedBy;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("moved_to_id")
    public Object getMovedToId() {
        return movedToId;
    }

    @JsonProperty("moved_to_id")
    public void setMovedToId(Object movedToId) {
        this.movedToId = movedToId;
    }

    @JsonProperty("iid")
    public Integer getIid() {
        return iid;
    }

    @JsonProperty("iid")
    public void setIid(Integer iid) {
        this.iid = iid;
    }

    @JsonProperty("labels")
    public List<String> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @JsonProperty("upvotes")
    public Integer getUpvotes() {
        return upvotes;
    }

    @JsonProperty("upvotes")
    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    @JsonProperty("downvotes")
    public Integer getDownvotes() {
        return downvotes;
    }

    @JsonProperty("downvotes")
    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    @JsonProperty("merge_requests_count")
    public Integer getMergeRequestsCount() {
        return mergeRequestsCount;
    }

    @JsonProperty("merge_requests_count")
    public void setMergeRequestsCount(Integer mergeRequestsCount) {
        this.mergeRequestsCount = mergeRequestsCount;
    }

    @JsonProperty("user_notes_count")
    public Integer getUserNotesCount() {
        return userNotesCount;
    }

    @JsonProperty("user_notes_count")
    public void setUserNotesCount(Integer userNotesCount) {
        this.userNotesCount = userNotesCount;
    }

    @JsonProperty("due_date")
    public String getDueDate() {
        return dueDate;
    }

    @JsonProperty("due_date")
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonProperty("references")
    public References getReferences() {
        return references;
    }

    @JsonProperty("references")
    public void setReferences(References references) {
        this.references = references;
    }

    @JsonProperty("time_stats")
    public TimeStats getTimeStats() {
        return timeStats;
    }

    @JsonProperty("time_stats")
    public void setTimeStats(TimeStats timeStats) {
        this.timeStats = timeStats;
    }

    @JsonProperty("has_tasks")
    public Boolean getHasTasks() {
        return hasTasks;
    }

    @JsonProperty("has_tasks")
    public void setHasTasks(Boolean hasTasks) {
        this.hasTasks = hasTasks;
    }

    @JsonProperty("task_status")
    public String getTaskStatus() {
        return taskStatus;
    }

    @JsonProperty("task_status")
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @JsonProperty("confidential")
    public Boolean getConfidential() {
        return confidential;
    }

    @JsonProperty("confidential")
    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
    }

    @JsonProperty("discussion_locked")
    public Boolean getDiscussionLocked() {
        return discussionLocked;
    }

    @JsonProperty("discussion_locked")
    public void setDiscussionLocked(Boolean discussionLocked) {
        this.discussionLocked = discussionLocked;
    }

    @JsonProperty("issue_type")
    public String getIssueType() {
        return issueType;
    }

    @JsonProperty("issue_type")
    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    @JsonProperty("severity")
    public String getSeverity() {
        return severity;
    }

    @JsonProperty("severity")
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @JsonProperty("_links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonProperty("task_completion_status")
    public TaskCompletionStatus getTaskCompletionStatus() {
        return taskCompletionStatus;
    }

    @JsonProperty("task_completion_status")
    public void setTaskCompletionStatus(TaskCompletionStatus taskCompletionStatus) {
        this.taskCompletionStatus = taskCompletionStatus;
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
