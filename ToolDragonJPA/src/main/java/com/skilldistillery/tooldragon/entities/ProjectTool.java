package com.skilldistillery.tooldragon.entities;


import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "project_tool")
public class ProjectTool {
	
	@EmbeddedId
	private ProjectToolId id;
	
	@ManyToOne
	@JoinColumn(name = "tool_id")
	@MapsId(value = "toolId")
	private Tool tool;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	@MapsId(value = "projectId")
	private Project project;
	
	@Column(name = "project_comment")
	private String projectComment;
	
	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime dateCreated;
	
	@Column(name = "date_approved")
	private LocalDateTime dateApproved;
	
	@Column(name = "project_owner_rating")
	private Integer projectOwnerRating;
	
	@Column(name = "tool_owner_rating")
	private Integer toolOwnerRating;
	
	@Column(name = "project_owner_rating_date")
	private LocalDateTime projectOwnerRatingDate;
	
	@Column(name = "tool_owner_rating_date")
	private LocalDateTime toolOwnerRatingDate;
	
	@Column(name = "project_owner_rating_comment")
	private String projectOwnerRatingComment;
	
	@Column(name = "tool_owner_rating_comment")
	private String toolOwnerRatingComment;

	public ProjectTool() {
		super();
	}

	public ProjectToolId getId() {
		return id;
	}

	public void setId(ProjectToolId id) {
		this.id = id;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getProjectComment() {
		return projectComment;
	}

	public void setProjectComment(String projectComment) {
		this.projectComment = projectComment;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateApproved() {
		return dateApproved;
	}

	public void setDateApproved(LocalDateTime dateApproved) {
		this.dateApproved = dateApproved;
	}

	public Integer getProjectOwnerRating() {
		return projectOwnerRating;
	}

	public void setProjectOwnerRating(Integer projectOwnerRating) {
		this.projectOwnerRating = projectOwnerRating;
	}

	public Integer getToolOwnerRating() {
		return toolOwnerRating;
	}

	public void setToolOwnerRating(Integer toolOwnerRating) {
		this.toolOwnerRating = toolOwnerRating;
	}

	public LocalDateTime getProjectOwnerRatingDate() {
		return projectOwnerRatingDate;
	}

	public void setProjectOwnerRatingDate(LocalDateTime projectOwnerRatingDate) {
		this.projectOwnerRatingDate = projectOwnerRatingDate;
	}

	public LocalDateTime getToolOwnerRatingDate() {
		return toolOwnerRatingDate;
	}

	public void setToolOwnerRatingDate(LocalDateTime toolOwnerRatingDate) {
		this.toolOwnerRatingDate = toolOwnerRatingDate;
	}

	public String getProjectOwnerRatingComment() {
		return projectOwnerRatingComment;
	}

	public void setProjectOwnerRatingComment(String projectOwnerRatingComment) {
		this.projectOwnerRatingComment = projectOwnerRatingComment;
	}

	public String getToolOwnerRatingComment() {
		return toolOwnerRatingComment;
	}

	public void setToolOwnerRatingComment(String toolOwnerRatingComment) {
		this.toolOwnerRatingComment = toolOwnerRatingComment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectTool other = (ProjectTool) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ProjectTool [id=" + id + ", tool=" + tool + ", project=" + project + ", dateCreated=" + dateCreated
				+ ", dateApproved=" + dateApproved + ", projectOwnerRating=" + projectOwnerRating + ", toolOwnerRating="
				+ toolOwnerRating + ", projectOwnerRatingDate=" + projectOwnerRatingDate + ", toolOwnerRatingDate="
				+ toolOwnerRatingDate + "]";
	}
	
	
}
