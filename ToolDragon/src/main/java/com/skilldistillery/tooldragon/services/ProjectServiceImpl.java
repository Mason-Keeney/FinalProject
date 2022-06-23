package com.skilldistillery.tooldragon.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.Project;
import com.skilldistillery.tooldragon.entities.User;
import com.skilldistillery.tooldragon.repositories.ProjectRepository;
import com.skilldistillery.tooldragon.repositories.UserRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Project getProjectById(String username, int projectId) {
		Optional<Project> projectOpt = projectRepo.findById(projectId);
		Project project = null;
		if (userRepo.findByUsername(username) != null && projectOpt.isPresent()) {
			project = projectOpt.get();
			if(!project.getActive()) {
				project = null;
			}
		}
		return project;
	}

	@Override
	public Set<Project> index(String username) {
		Set<Project> projects = null;
		Set<Project> activeProjects = null;
		if(userRepo.findByUsername(username) != null) {
			projects = projectRepo.findByOwner_Username(username);
			if(projects != null && !projects.isEmpty()) {
				activeProjects = new HashSet<>();
				for (Project project : projects) {
					if(project.getActive()) {
						activeProjects.add(project);
					}
				}
			}
		}
		return activeProjects;
	}

	@Override
	public List<Project> indexAll() {
		List<Project> projects = projectRepo.findAll();
		List<Project> activeProjects = null;
		if(projects != null && !projects.isEmpty()) {
			activeProjects = new ArrayList<>();
			for (Project project : projects) {
				if (project.getActive()) {
					activeProjects.add(project);
				}
			}
		}
		return activeProjects;
	}

	@Override
	public Project show(String username, int projectId) {
		Project project = null;
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Optional<Project> op = projectRepo.findById(projectId);
			if (op.isPresent()) {
				project = op.get();
				if(!project.getActive()) {
					project = null;
				}
			}
		}
		return project;
	}

	@Override
	public Project update(Project project, int projectId, String username) {
		User sessionUser = userRepo.findByUsername(username);
		Project existing = null;
		if (sessionUser != null) {
			Optional<Project> op = projectRepo.findById(projectId);
			if (op.isPresent()) {
				existing = op.get();
				if(!existing.getActive())
					existing = null;
			}
			if (existing != null && (existing.getOwner().getUsername().equals(username) || sessionUser.getRole().equals("role_admin"))) {
				existing.setDescription(project.getDescription());
				existing.setStartDate(project.getStartDate());
				existing.setUpdatedAt(project.getUpdatedAt());
				existing.setEstimatedEndDate(project.getEstimatedEndDate());
				existing.setCompleted(project.isCompleted());
				existing.setCancelled(project.isCancelled());
				existing.setImageUrl(project.getImageUrl());
				existing.setCreatedAt(project.getCreatedAt());
				projectRepo.saveAndFlush(existing);
			}
		}
		return existing;
	}

	@Override
	public boolean destroy(String username, int projectId) {
		boolean deleted = false;
		User sessionUser = userRepo.findByUsername(username);
		Project toDelete = null;
		if (sessionUser != null) {
			Optional<Project> op = projectRepo.findById(projectId);
			if (op.isPresent()) {
				toDelete = op.get();
			}
			if (toDelete != null && toDelete.getOwner().getUsername().equals(username) || sessionUser.getRole().equals("role_admin")) {
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
			project.setActive(true);
			projectRepo.saveAndFlush(project);
		} else {
			project = null;
		}
		return project;
	}

}
