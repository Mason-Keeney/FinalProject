package com.skilldistillery.tooldragon.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.tooldragon.entities.Project;
import com.skilldistillery.tooldragon.entities.User;
import com.skilldistillery.tooldragon.repositories.ProjectRepository;
import com.skilldistillery.tooldragon.repositories.UserRepository;

public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Project getProjectById(String username, int projectId) {
		User owner = userRepo.findByUsername(username);
		Optional<Project> projectOpt = projectRepo.findById(projectId);
		Project project = null;
		if (owner != null && projectOpt.isPresent()) {
			project = projectOpt.get();
		}
		return project;
	}

	@Override
	public Set<Project> index(String username) {
		return projectRepo.findByOwner_Username(username);
	}

	@Override
	public List<Project> indexAll() {
		return projectRepo.findAll();
	}

	@Override
	public Project show(String username, int projectId) {
		Project project = null;
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Optional<Project> op = projectRepo.findById(projectId);
			if (op.isPresent()) {
				project = op.get();
			}
		}
		return project;
	}

	@Override
	public Project update(Project project, int projectId, String username) {
		User user = userRepo.findByUsername(username);
		Project existing = null;
		if (user != null) {
			Optional<Project> op = projectRepo.findById(projectId);
			if (op.isPresent()) {
				existing = op.get();
			}
			if (existing != null) {
				existing.setDescription(project.getDescription());
				existing.setStartDate(project.getStartDate());
				existing.setUpdatedAt(project.getUpdatedAt());
				existing.setEstimatedEndDate(project.getEstimatedEndDate());
				existing.setCompleted(project.isCompleted());
				existing.setCancelled(project.isCancelled());
				existing.setImageUrl(project.getImageUrl());
				existing.setCreatedAt(project.getCreatedAt());
				projectRepo.saveAndFlush(existing);
				return existing;
			}
		}
		return project;
	}

	@Override
	public boolean destroy(String username, int projectId) {
		boolean deleted = false;
		User user = userRepo.findByUsername(username);
		Project toDelete = null;
		if (user != null) {
			Optional<Project> op = projectRepo.findById(projectId);
			if (op.isPresent()) {
				toDelete = op.get();
			}
			if (toDelete != null) {
				toDelete.setActive(false);
				projectRepo.saveAndFlush(toDelete);
				deleted = true;
			}
		}
		return deleted;
	}

	@Override
	public Project create(Project project, String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			projectRepo.saveAndFlush(project);
		} else {
			project = null;
		}
		return project;
	}

}
