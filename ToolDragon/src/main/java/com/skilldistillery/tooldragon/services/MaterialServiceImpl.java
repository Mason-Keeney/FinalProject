package com.skilldistillery.tooldragon.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.Material;
import com.skilldistillery.tooldragon.entities.User;
import com.skilldistillery.tooldragon.repositories.MaterialRepository;
import com.skilldistillery.tooldragon.repositories.UserRepository;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialRepository materialRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Material getUserById(int materialId) {
		Optional<Material> materialOpt = materialRepo.findById(materialId);
		Material material = null;
		if (materialOpt.isPresent()) {
			material = materialOpt.get();
		}
		return material;
	}

	@Override
	public List<Material> index(String username) {
		List<Material> materials = null;
		List<Material> filteredMaterial = null;
		if (userRepo.findByUsername(username) != null) {
			materials = materialRepo.findAll();
			filteredMaterial = new ArrayList<>();
			if (materials != null && !materials.isEmpty()) {
				for (Material material : materials) {
					if (material.getActive()) {
						filteredMaterial.add(material);
					}
				}
			}
		}
		return filteredMaterial;
	}

	@Override
	public Material show(String username, int materialId) {
		Material material = null;
		if (userRepo.findByUsername(username) != null) {
			Optional<Material> op = materialRepo.findById(materialId);
			if (op.isPresent()) {
				material = op.get();
				if (!material.getActive()) {
					material = null;
				}
			}
		}
		return material;
	}

	@Override
	public Material create(String username, Material material) {
		User owner = userRepo.findByUsername(username);
		if (material != null && owner != null) {
			material.setName(material.getName());
			material.setDescription(material.getDescription());
			material.setActive(true);
			materialRepo.saveAndFlush(material);
			return material;
		}
		return null;
	}

	@Override
	public Material update(String username, int materialId, Material material) {
		User sessionUser = userRepo.findByUsername(username);
		Material existing = null;
		if (sessionUser != null) {
			Optional<Material> op = materialRepo.findById(materialId);
			if (op.isPresent()) {
				existing = op.get();
				if (!existing.getActive())
					existing = null;
			}
			if (existing != null) {
				existing.setName(material.getName());
				existing.setDescription(material.getDescription());
				existing.setActive(true);
				materialRepo.saveAndFlush(existing);
				return existing;
			}
		}
		return material;
	}

	@Override
	public boolean destroy(String username, int materialId) {
		boolean deleted = false;
		User sessionUser = userRepo.findByUsername(username);
		Material toDelete = null;
		if (sessionUser != null) {
			Optional<Material> op = materialRepo.findById(materialId);
			if (op.isPresent()) {
				toDelete = op.get();
			}
			if (toDelete != null) {
				toDelete.setActive(false);
				materialRepo.saveAndFlush(toDelete);
				deleted = true;
			}

		}
		return deleted;
	}

}
