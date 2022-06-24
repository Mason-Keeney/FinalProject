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
public class Tool {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	private String name;
	
	private String description;
	
	private String availability;
	
	private Boolean available;
	
	@Column(name = "training_required")
	private Boolean trainingRequired;
	
	private Integer operators;
	
	private Boolean active;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	@JsonIgnoreProperties({"tools", "projects", "address"})
	private User owner;
	
	@OneToMany(mappedBy = "tool")
	@JsonIgnoreProperties({"tool", "user"})
	private List<ToolComment> comments;
	
	@ManyToOne
	@JoinColumn(name = "tool_condition_id")
	private ToolCondition condition;
	
	@ManyToMany
	@JoinTable(name = "category_tools", joinColumns = @JoinColumn(name="tool_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;
	
	@OneToMany(mappedBy = "tool")
	@JsonIgnoreProperties({"tool", "owner"})
	private List<ProjectTool> projectsUsedIn;

	public Tool() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Boolean getTrainingRequired() {
		return trainingRequired;
	}

	public void setTrainingRequired(Boolean trainingRequired) {
		this.trainingRequired = trainingRequired;
	}

	public Integer getOperators() {
		return operators;
	}

	public void setOperators(Integer operators) {
		this.operators = operators;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<ToolComment> getComments() {
		List<ToolComment> copy = new ArrayList<>();
		if(comments != null) {
			copy = new ArrayList<>(comments);
		}
		return copy;
	}

	public void setComments(List<ToolComment> comments) {
		this.comments = comments;
	}
	
	public void addComment(ToolComment comment) {
		if(comments == null) {
			comments = new ArrayList<>();
		}
		if(comments != null && !comments.contains(comment)) {
			comments.add(comment);
		}
		if (comment.getTool() == null) {
			comment.setTool(this);
		}
	}
	
	public void removeComment(ToolComment comment) {
		if(comments != null && comments.contains(comment)) {
			comments.remove(comment);
		}
		if(comment.getTool().equals(this)) {
			comment.setTool(null);
		}
	}

	public ToolCondition getCondition() {
		return condition;
	}

	public void setCondition(ToolCondition condition) {
		this.condition = condition;
	}

	public List<Category> getCategories() {
		List<Category> copy = new ArrayList<>();
		if(categories != null) {
			copy = new ArrayList<>(categories);
		}
		return copy;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public void addCategory(Category category) {
		if (categories == null) {
			categories = new ArrayList<>();
		}
		if(categories != null && !categories.contains(category)) {
			categories.add(category);
		}
	}
	
	public void removeCategory(Category category) {
		if(categories != null && categories.contains(category)) {
			categories.remove(category);
		}
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ProjectTool> getProjectsUsedIn() {
		List<ProjectTool> copy = new ArrayList<>();
		if(projectsUsedIn != null) {
			copy = new ArrayList<>(projectsUsedIn);
		}
		return copy;
	}

	public void setProjectsUsedIn(List<ProjectTool> projectsUsedIn) {
		this.projectsUsedIn = projectsUsedIn;
	}
	
	public void addProjectUsedIn(ProjectTool projectUsedIn) {
		if(projectsUsedIn == null) {
			projectsUsedIn = new ArrayList<>();
		}
		if(projectsUsedIn != null && !projectsUsedIn.contains(projectUsedIn)) {
			projectsUsedIn.add(projectUsedIn);
		}
		if(projectUsedIn.getTool() == null) {
			projectUsedIn.setTool(this);
		}
	}
	
	public void removeProjectUsedIn(ProjectTool projectUsedIn) {
		if(projectsUsedIn != null && projectsUsedIn.contains(projectUsedIn)) {
			projectsUsedIn.remove(projectUsedIn);
		}
		if(projectUsedIn.getTool().equals(this)) {
			projectUsedIn.setTool(null);
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
		Tool other = (Tool) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Tool [id=" + id + ", name=" + name + ", description=" + description + ", availability=" + availability
				+ ", available=" + available + ", trainingRequired=" + trainingRequired + ", operators=" + operators
				+ ", active=" + active + ", imageUrl=" + imageUrl + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}
	
	
	
}
