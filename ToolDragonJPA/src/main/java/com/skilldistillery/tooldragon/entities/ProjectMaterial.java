package com.skilldistillery.tooldragon.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "project_material")
public class ProjectMaterial {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	private String quantity;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	@JsonIgnoreProperties("projectMaterials")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "material_id")
	private Material material;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;

	public ProjectMaterial() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
		ProjectMaterial other = (ProjectMaterial) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ProjectMaterial [id=" + id + ", quantity=" + quantity + "]";
	}
	
	
	
}
