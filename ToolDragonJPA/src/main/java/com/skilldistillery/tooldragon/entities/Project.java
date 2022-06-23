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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Project {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	private String description;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@Column(name = "estimated_end_date")
	private LocalDateTime estimatedEndDate;
	
	private Boolean completed;
	
	private Boolean cancelled;
	
	private Boolean active;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	@JsonIgnoreProperties("ownedProjects")
	private User owner;
	
	@OneToMany(mappedBy = "project")
	private List<Participant> participants;
	
	public Project() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getEstimatedEndDate() {
		return estimatedEndDate;
	}

	public void setEstimatedEndDate(LocalDateTime estimatedEndDate) {
		this.estimatedEndDate = estimatedEndDate;
	}

	public Boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Participant> getParticipants() {
		return new ArrayList<>(participants);
	}

	public void setParticipants(List<Participant> participantUsers) {
		this.participants = participantUsers;
	}
	
	public void addParticipant(Participant participant) {
		if(participants == null) {
			participants = new ArrayList<>();
		}
		if(participants != null && !participants.contains(participant)) {
			participants.add(participant);
		}
		if(participant.getProject() == null) {
			participant.setProject(this);
		}
	}
	
	public void removeParticipant(Participant participant) {
		if (participants != null && participants.contains(participant)) {
			participants.remove(participant);
		}
		if(participant.getProject().equals(this)) {
			participant.setProject(null);
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
		Project other = (Project) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", description=" + description + ", startDate=" + startDate + ", updatedAt="
				+ updatedAt + ", estimatedEndDate=" + estimatedEndDate + ", completed=" + completed + ", cancelled="
				+ cancelled + ", active=" + active + ", imageUrl=" + imageUrl + ", createdAt=" + createdAt + "]";
	}
	
	
	
}
