package com.skilldistillery.tooldragon.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ToolCommentVoteId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "tool_comment_id")
	private int toolCommentId;
	
	@Column(name = "user_id")
	private int userId;

	public ToolCommentVoteId() {
		super();
	}

	public int getToolCommentId() {
		return toolCommentId;
	}

	public void setToolCommentId(int toolCommentId) {
		this.toolCommentId = toolCommentId;
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
		return Objects.hash(toolCommentId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToolCommentVoteId other = (ToolCommentVoteId) obj;
		return toolCommentId == other.toolCommentId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "ToolCommentVoteId [toolCommentId=" + toolCommentId + ", userId=" + userId + "]";
	}
	
	
}
