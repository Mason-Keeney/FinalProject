package com.skilldistillery.tooldragon.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectToolId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "tool_id")
	private int toolId;
	
	@Column(name = "project_id")
	private int projectId;
	
	public ProjectToolId() {
		super();
	}

	public int getToolId() {
		return toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectId, toolId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectToolId other = (ProjectToolId) obj;
		return projectId == other.projectId && toolId == other.toolId;
	}

	@Override
	public String toString() {
		return "ProjectToolId [toolId=" + toolId + ", projectId=" + projectId + "]";
	}
	
	
	
	
}
