package com.skilldistillery.tooldragon.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tool_comment")
public class ToolComment {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	@Column(name = "comment_body")
	private String commentBody;
	
	@Column(name = "created_date")
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	private Boolean active;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("toolComments")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "tool_id")
	@JsonIgnoreProperties("comments")
	private Tool tool;
	
	@OneToMany(mappedBy = "toolComment")
	private List<ToolCommentVote> votes;

	public ToolComment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public List<ToolCommentVote> getVotes() {
		return new ArrayList<>(votes);
	}

	public void setVotes(List<ToolCommentVote> votes) {
		this.votes = votes;
	}
	
	public void addVote(ToolCommentVote vote) {
		if(votes == null) {
			votes = new ArrayList<>();
		}
		if(votes != null && !votes.contains(vote)) {
			votes.add(vote);
		}
		if(vote.getToolComment() == null) {
			vote.setToolComment(this);
		}
	}
	
	public void removeVote(ToolCommentVote vote) {
		if (votes != null && votes.contains(vote)) {
			votes.remove(vote);
		}
		if (vote.getToolComment().equals(this)) {
			vote.setToolComment(null);
		}
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
		ToolComment other = (ToolComment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ToolComment [id=" + id + ", commentBody=" + commentBody + ", createdDate=" + createdDate + ", active="
				+ active + "]";
	}
	
	
}
