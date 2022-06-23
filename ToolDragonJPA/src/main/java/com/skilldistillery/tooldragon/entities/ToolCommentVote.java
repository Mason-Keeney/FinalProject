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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tool_comment_vote")
public class ToolCommentVote {
	
	@EmbeddedId
	private ToolCommentVoteId id;
	
	@Column(name = "vote_date")
	@CreationTimestamp
	private LocalDateTime voteDate;
	
	@Column(name = "reported_for")
	private String reportedFor;
	
	@ManyToOne
	@JoinColumn(name = "tool_comment_id")
	@MapsId(value = "toolCommentId")
	@JsonIgnoreProperties("votes")
	private ToolComment toolComment;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@MapsId(value = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "vote_id")
	private Vote vote;
	

	public ToolCommentVote() {
		super();
	}

	public ToolCommentVoteId getId() {
		return id;
	}

	public void setId(ToolCommentVoteId id) {
		this.id = id;
	}

	public LocalDateTime getVoteDate() {
		return voteDate;
	}

	public void setVoteDate(LocalDateTime voteDate) {
		this.voteDate = voteDate;
	}

	public String getReportedFor() {
		return reportedFor;
	}

	public void setReportedFor(String reportedFor) {
		this.reportedFor = reportedFor;
	}

	public ToolComment getToolComment() {
		return toolComment;
	}

	public void setToolComment(ToolComment toolComment) {
		this.toolComment = toolComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
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
		ToolCommentVote other = (ToolCommentVote) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ToolCommentVote [id=" + id + ", voteDate=" + voteDate + ", reportedFor=" + reportedFor + "]";
	}
	
	
}
