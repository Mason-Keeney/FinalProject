package com.skilldistillery.tooldragon.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ParticipantId implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@Column(name = "project_id")
	private int projectId;
	
	@Column(name = "user_id")
	private int userId;

	public ParticipantId() {
		super();
	}
	
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Override
	public int hashCode() {
		return Objects.hash(projectId, userId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipantId other = (ParticipantId) obj;
		return projectId == other.projectId && userId == other.userId;
	}


	@Override
	public String toString() {
		return "ParticipantId [projectId=" + projectId + ", userId=" + userId + "]";
	}
	
	
	
}
