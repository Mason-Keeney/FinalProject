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
@Table(name = "project_comment")
public class ProjectComment {
	
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
	@JsonIgnoreProperties("projectComments")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	@JsonIgnoreProperties("comments")
	private Project project;
	
	@OneToMany(mappedBy = "projectComment")
	@JsonIgnoreProperties({"projectComment", "user"})
	private List<ProjectCommentVote> votes;

	public ProjectComment() {
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<ProjectCommentVote> getVotes() {
		return votes;
	}

	public void setVotes(List<ProjectCommentVote> votes) {
		this.votes = votes;
	}
	
	public void addVote(ProjectCommentVote vote) {
		if(votes == null) {
			votes = new ArrayList<>();
		}
		if(votes != null && !votes.contains(vote)) {
			votes.add(vote);
		}
		if(vote.getProjectComment() == null) {
			vote.setProjectComment(this);
		}
	}
	
	public void removeVote(ProjectCommentVote vote) {
		if(votes != null && votes.contains(vote)) {
			votes.remove(vote);
		}
		if(vote.getProjectComment().equals(this)) {
			vote.setProjectComment(null);
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
		ProjectComment other = (ProjectComment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ProjectComment [id=" + id + ", commentBody=" + commentBody + ", createdDate=" + createdDate
				+ ", active=" + active + "]";
	}
	
	

}
