package com.skilldistillery.tooldragon.services;

import java.util.List;

import com.skilldistillery.tooldragon.entities.ProjectMaterial;

public interface ProjectMaterialService {
	
	List<ProjectMaterial> index(String username);
	ProjectMaterial show(String username, int id);
	ProjectMaterial create(String username, ProjectMaterial projectMaterial);
	ProjectMaterial update(String username, ProjectMaterial projectMaterial, int id);
	boolean delete(String username, int id);
}
