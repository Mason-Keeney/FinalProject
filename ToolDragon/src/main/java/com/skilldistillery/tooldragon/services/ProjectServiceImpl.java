package com.skilldistillery.tooldragon.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.tooldragon.entities.Project;
import com.skilldistillery.tooldragon.entities.User;
import com.skilldistillery.tooldragon.repositories.ProjectRepository;

public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepo;

	@Override
	public Project getProjectById(int projectId) {
		Optional<Project> projectOpt = projectRepo.findById(projectId);
		Project project = null;
		if (projectOpt.isPresent()) {
			project = projectOpt.get();
		}
		return project;
	}

	@Override
	public Set<Project> index(Project project) {
		return projectRepo.findByProjectDescription(project.getDescription());
	}

	@Override
	public Project show(Project project, int projectId) {
		return projectRepo.findByIdAndProjectDescription(projectId, project.getDescription());
	}


	@Override
	public Project update(Project project, int projectId, User user) {
//		Project existing = projectRepo.findByIdAndProjectName_name(projectId, project);
		Project existing = projectRepo.findByIdAndProjectDescription(projectId, null);
		if (existing != null) {
			existing.setDescription(project.getDescription());;
			existing.setStartDate(project.getStartDate());
			existing.setUpdatedAt(project.getUpdatedAt());
			existing.setEstimatedEndDate(project.getEstimatedEndDate());
			existing.setCompleted(false);
			existing.setCancelled(false);
			existing.setActive(false);
			existing.setImageUrl(project.getImageUrl());
			existing.setCreatedAt(project.getCreatedAt());
			projectRepo.saveAndFlush(existing);
			return existing;
		}
		return project;
	}

	@Override
	public boolean destroy(Project project, int projectId) {
		boolean deleted = false;
//		Project toDelete = projectRepo.findByIdAndProjectName_name(projectId, project);
		Project toDelete = projectRepo.findByIdAndProjectDescription(projectId, null);
		if (toDelete != null) {
			projectRepo.delete(toDelete);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public Project create(Project project, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
