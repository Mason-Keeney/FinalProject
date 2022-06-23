package com.skilldistillery.tooldragon.services;

import java.util.Set;

import com.skilldistillery.tooldragon.entities.Project;
import com.skilldistillery.tooldragon.entities.User;

public interface ProjectService {
	
	public Project getProjectById(int projectId);

	public Set<Project> index(Project project);

    public Project show(Project project, int projectId);

    public Project create(Project project, User user);

    public Project update(Project project, int projectId, User user);

    public boolean destroy(Project project, int projectId);
}
