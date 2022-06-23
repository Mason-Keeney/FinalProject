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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@JsonIgnoreProperties("project")
	private List<Participant> participants;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(mappedBy = "project")
	@JsonIgnoreProperties("project")
	private List<ProjectTool> projectTools;

	@ManyToMany
	@JoinTable(name = "project_category", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;

	@OneToMany(mappedBy = "project")
	@JsonIgnoreProperties("project")
	private List<ProjectComment> comments;

	@OneToMany(mappedBy = "project")
	@JsonIgnoreProperties("project")
	private List<ProjectMaterial> projectMaterials;

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

	public Boolean isCancelled() {
		return cancelled;
	}

	public Boolean isActive() {
		return active;
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
		if (participants == null) {
			participants = new ArrayList<>();
		}
		if (participants != null && !participants.contains(participant)) {
			participants.add(participant);
		}
		if (participant.getProject() == null) {
			participant.setProject(this);
		}
	}

	public void removeParticipant(Participant participant) {
		if (participants != null && participants.contains(participant)) {
			participants.remove(participant);
		}
		if (participant.getProject().equals(this)) {
			participant.setProject(null);
		}
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<ProjectTool> getProjectTools() {
		return new ArrayList<>(projectTools);
	}

	public void setProjectTools(List<ProjectTool> projectTools) {
		this.projectTools = projectTools;
	}

	public void addProjectTool(ProjectTool projectTool) {
		if (projectTools == null) {
			projectTools = new ArrayList<>();
		}
		if (projectTools != null && !projectTools.contains(projectTool)) {
			projectTools.add(projectTool);
		}
		if (projectTool.getProject() == null) {
			projectTool.setProject(this);
		}
	}

	public void removeProjectTool(ProjectTool projectTool) {
		if (projectTools != null && projectTools.contains(projectTool)) {
			projectTools.remove(projectTool);
		}
		if (projectTool.getProject().equals(this)) {
			projectTool.setProject(null);
		}
	}

	public List<Category> getCategories() {
		return new ArrayList<>(categories);
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void addCategory(Category category) {
		if (categories == null) {
			categories = new ArrayList<>();
		}
		if (categories != null && !categories.contains(category)) {
			categories.add(category);
		}
	}

	public void removeCategory(Category category) {
		if (categories != null && categories.contains(category)) {
			categories.remove(category);
		}
	}

	public List<ProjectComment> getComments() {
		return new ArrayList<>(comments);
	}

	public void setComments(List<ProjectComment> comments) {
		this.comments = comments;
	}

	public void addComment(ProjectComment comment) {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		if (comments != null && !comments.contains(comment)) {
			comments.add(comment);
		}
		if (comment.getProject() == null) {
			comment.setProject(this);
		}
	}

	public void removeComment(ProjectComment comment) {
		if (comments != null && comments.contains(comment)) {
			comments.remove(comment);
		}
		if (comment.getProject().equals(this)) {
			comment.setProject(null);
		}
	}

	public List<ProjectMaterial> getProjectMaterials() {
		return new ArrayList<>(projectMaterials);
	}

	public void setProjectMaterials(List<ProjectMaterial> projectMaterials) {
		this.projectMaterials = projectMaterials;
	}

	public void addProjectMaterial(ProjectMaterial projectMaterial) {
		if (projectMaterials == null) {
			projectMaterials = new ArrayList<>();
		}
		if (projectMaterials != null && !projectMaterials.contains(projectMaterial)) {
			projectMaterials.add(projectMaterial);
		}
		if (projectMaterial.getProject() == null) {
			projectMaterial.setProject(this);
		}
	}

	public void removeProjectMaterial(ProjectMaterial projectMaterial) {
		if (projectMaterials != null && projectMaterials.contains(projectMaterial)) {
			projectMaterials.remove(projectMaterial);
		}
		if (projectMaterial.getProject().equals(this)) {
			projectMaterial.setProject(null);
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
