package com.skilldistillery.tooldragon.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectCommentVoteId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "project_comment_id")
	private int projectCommentId;
	
	@Column(name = "user_id")
	private int userId;

	public ProjectCommentVoteId() {
		super();
	}

	public int getProjectCommentId() {
		return projectCommentId;
	}

	public void setProjectCommentId(int projectCommentId) {
		this.projectCommentId = projectCommentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectCommentId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectCommentVoteId other = (ProjectCommentVoteId) obj;
		return projectCommentId == other.projectCommentId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "ProjectCommentVoteId [projectCommentId=" + projectCommentId + ", userId=" + userId + "]";
	}
	
	
	
	

	
}
