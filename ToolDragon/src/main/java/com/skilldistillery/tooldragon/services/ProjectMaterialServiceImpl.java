package com.skilldistillery.tooldragon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.ProjectMaterial;
import com.skilldistillery.tooldragon.repositories.ProjectMaterialRepository;
import com.skilldistillery.tooldragon.repositories.UserRepository;

@Service
public class ProjectMaterialServiceImpl implements ProjectMaterialService {
	
	@Autowired
	private ProjectMaterialRepository projMaterialRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<ProjectMaterial> index(String username) {
		List<ProjectMaterial> projMats = null;
		
		if(userRepo.findByUsername(username) != null) {
			projMats = projMaterialRepo.findAll();
		}
		return projMats;
	}

	@Override
	public ProjectMaterial show(String username, int id) {
		ProjectMaterial projMat = null;
			if(userRepo.findByUsername(username) != null) {
				Optional<ProjectMaterial> op = projMaterialRepo.findById(id);
				if(op.isPresent()) {
					projMat = op.get();
				}
			}
		return projMat;
	}

	@Override
	public ProjectMaterial create(String username, ProjectMaterial projectMaterial) {
		ProjectMaterial projMat = null;
		if(userRepo.findByUsername(username) != null) {
			projMat = projMaterialRepo.saveAndFlush(projectMaterial);
		}
		return projMat;
	}

	@Override
	public ProjectMaterial update(String username, ProjectMaterial projectMaterial, int id) {
		ProjectMaterial managed = null;
		if(userRepo.findByUsername(username) != null) {
			Optional<ProjectMaterial> op = projMaterialRepo.findById(id);
			if(op.isPresent()) {
				managed = op.get();
				managed.setQuantity(projectMaterial.getQuantity());
				managed.setStatus(projectMaterial.getStatus());
			}
		}
		return managed;
	}

	@Override
	public boolean delete(String username, int id) {
		boolean deleted = false;
		if(userRepo.findByUsername(username) != null) {
			Optional<ProjectMaterial> op = projMaterialRepo.findById(id);
			if(op.isPresent()) {
				ProjectMaterial toDelete = op.get();
				projMaterialRepo.delete(toDelete);
				deleted = true;
			}
		}
		return deleted;
	}

}
