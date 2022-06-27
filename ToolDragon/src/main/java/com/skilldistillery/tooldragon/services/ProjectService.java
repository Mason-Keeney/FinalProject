package com.skilldistillery.tooldragon.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.tooldragon.entities.Project;

public interface ProjectService {

	public Project getProjectById(String username, int projectId);

	public Set<Project> index(String username);

	public Project show(String username, int projectId);

	public Project create(Project project, String username);

	public Project update(Project project, int projectId, String username);

	public boolean destroy(String username, int projectId);
	
	public List<Project> indexAll();
	
	public List<Project> findByKeyword(String keyword);
	
}
