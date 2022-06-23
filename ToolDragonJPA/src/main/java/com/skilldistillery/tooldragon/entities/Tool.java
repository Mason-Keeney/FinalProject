package com.skilldistillery.tooldragon.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
