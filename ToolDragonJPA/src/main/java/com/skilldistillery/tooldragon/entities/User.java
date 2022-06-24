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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String password;

	private Boolean enabled;

	private String role;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	private String description;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@Column(name = "last_login")
	private LocalDateTime lastLogin;
	
	@Column(name = "background_image_url")
	private String backgroundImageUrl;
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy = "owner")
	@JsonIgnoreProperties({"owner", "participants", "address", "projectTools"})
	private List<Project> ownedProjects;
	
	@OneToMany(mappedBy = "owner")
	@JsonIgnoreProperties({"owner", "projectsUsedIn"})
	private List<Tool> tools;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({"user", "project"})
	private List<ProjectComment> projectComments;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({"user", "tool"})
	private List<ToolComment> toolComments;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({"user", "project"})
	private List<Participant> participations;
	
	
	{
		ownedProjects = new ArrayList<>();
	}
	
	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getBackgroundImageUrl() {
		return backgroundImageUrl;
	}

	public void setBackgroundImageUrl(String backgroundImageUrl) {
		this.backgroundImageUrl = backgroundImageUrl;
	}
	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
//	SOLUTION FOR JSON NULL POINTER WHEN NO OWNEDPROJECTS
	
	public List<Project> getOwnedProjects() {
		List<Project> copy = new ArrayList<>();
		if (ownedProjects != null) {
			copy = new ArrayList<>(ownedProjects);
		}
		return copy;
	}
	
//	SOLUTION FOR JSON NULL POINTER WHEN NO OWNEDPROJECTS END

	public void setOwnedProjects(List<Project> projects) {
		this.ownedProjects = projects;
	}
	
	public void addProject(Project project) {
		if(ownedProjects == null) {
			ownedProjects = new ArrayList<>();
		}
		if(ownedProjects != null && ownedProjects.contains(project)) {
			ownedProjects.add(project);
		}
		if(project.getOwner() == null) {
			project.setOwner(this);
		}
	}
	
	public void removeProject(Project project) {
		if (ownedProjects != null && ownedProjects.contains(project)) {
			ownedProjects.remove(project);
		}
		if(project.getOwner().equals(this)) {
			project.setOwner(null);
		}
	}

	public List<Tool> getTools() {
		List<Tool> copy = new ArrayList<>();
		if(tools != null) {
			copy = new ArrayList<>(tools);
		}
		return copy;
	}

	public void setTools(List<Tool> tools) {
		this.tools = tools;
	}
	
	public void addTool(Tool tool) {
		if (tools == null) {
			tools = new ArrayList<>();
		}
		if(tools != null && !tools.contains(tool)) {
			tools.add(tool);
		}
		if(tool.getOwner() == null) {
			tool.setOwner(this);
		}
	}
	
	public void removeTool(Tool tool) {
		if(tools != null && tools.contains(tool)) {
			tools.remove(tool);
		}
		if(tool.getOwner().equals(this)) {
			tool.setOwner(null);
		}
	}

	public List<ProjectComment> getProjectComments() {
		List<ProjectComment> copy = new ArrayList<>();
		if(projectComments != null) {
			copy = new ArrayList<>(projectComments);
		}
		return copy;
	}

	public void setProjectComments(List<ProjectComment> projectComments) {
		this.projectComments = projectComments;
	}
	
	public void addProjectComment(ProjectComment projComment) {
		if(projectComments == null) {
			projectComments = new ArrayList<>();
		}
		if(projectComments != null && !projectComments.contains(projComment)) {
			projectComments.add(projComment);
		}
		if(projComment.getUser() == null) {
			projComment.setUser(this);
		}
	}
	
	public void removeProjectComment(ProjectComment projComment) {
		if(projectComments != null && projectComments.contains(projComment)) {
			projectComments.remove(projComment);
		}
		if(projComment.getUser().equals(this)) {
			projComment.setUser(null);
		}
	}

	public List<ToolComment> getToolComments() {
		List<ToolComment> copy = new ArrayList<>();
		if(toolComments != null) {
			copy = new ArrayList<>(toolComments);
		}
		return copy;
	}

	public void setToolComments(List<ToolComment> toolComments) {
		this.toolComments = toolComments;
	}
	
	public void addToolComment(ToolComment toolComment) {
		if(toolComments == null) {
			toolComments = new ArrayList<>();
		}
		
		if(toolComments != null && !toolComments.contains(toolComment)) {
			toolComments.add(toolComment);
		}
		
		if(toolComment.getUser() == null) {
			toolComment.setUser(this);
		}
	}
	
	public void removeToolComment(ToolComment toolComment) {
		if(toolComments != null && toolComments.contains(toolComment)) {
			toolComments.remove(toolComment);
		}
		if(toolComment.getUser().equals(this)){
			toolComment.setUser(null);
		}
	}

	public List<Participant> getParticipations() {
		List<Participant> copy = new ArrayList<>();
		if(participations != null) {
			copy = new ArrayList<>(participations);
		}
		return copy;
	}

	public void setParticipations(List<Participant> participations) {
		this.participations = participations;
	}
	
	public void addParticipation(Participant participation) {
		if(participations == null){
			participations = new ArrayList<>();
		}
		if(participations != null && !participations.contains(participation)) {
			participations.add(participation);
		}
		if(participation.getUser() == null) {
			participation.setUser(this);
		}
	}
	
	public void removeParticipation(Participant participation) {
		if(participations != null && participations.contains(participation)) {
			participations.add(participation);
		}
		if(participation.getUser().equals(this)) {
			participation.setUser(null);
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", active="
				+ enabled + "]";
	}

}
