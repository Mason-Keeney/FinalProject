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
@Table(name = "participant")
public class Participant {
	
	@EmbeddedId
	private ParticipantId id;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	@MapsId(value = "projectId")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@MapsId(value = "userId")
	private User user;	
	
	@Column(name = "participant_comment")
	private String participantComment;
	
	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime dateCreated;
	
	@Column(name = "date_approved")
	private LocalDateTime dateApproved;
	
	private Integer rating;
	
	@Column(name = "rating_comment")
	private String ratingComment;
	
	@Column(name = "rating_date")
	private LocalDateTime ratingDate;
	

	public Participant() {
		super();
	}

	public ParticipantId getId() {
		return id;
	}

	public void setId(ParticipantId id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getParticipantComment() {
		return participantComment;
	}

	public void setParticipantComment(String participantComment) {
		this.participantComment = participantComment;
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getRatingComment() {
		return ratingComment;
	}

	public void setRatingComment(String ratingComment) {
		this.ratingComment = ratingComment;
	}

	public LocalDateTime getRatingDate() {
		return ratingDate;
	}

	public void setRatingDate(LocalDateTime ratingDate) {
		this.ratingDate = ratingDate;
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
		Participant other = (Participant) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Participant [id=" + id + ", project=" + project + ", user=" + user + ", participantComment="
				+ participantComment + ", dateCreated=" + dateCreated + ", dateApproved=" + dateApproved + ", rating="
				+ rating + ", ratingComment=" + ratingComment + ", ratingDate=" + ratingDate + "]";
	}

	
}
