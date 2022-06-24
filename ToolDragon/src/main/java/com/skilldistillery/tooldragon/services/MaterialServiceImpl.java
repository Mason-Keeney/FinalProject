package com.skilldistillery.tooldragon.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.Material;
import com.skilldistillery.tooldragon.entities.Tool;
import com.skilldistillery.tooldragon.entities.User;
import com.skilldistillery.tooldragon.repositories.MaterialRepository;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialRepository materialRepo;

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
	public List<Material> index(String name) {
		List<Material> materials = null;
		List<Material> filteredMaterial = null;
		if (materialRepo.findByName(name) != null) {
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
	public Material show(String name, int materialId) {
		Material material = null;
		if (materialRepo.findByName(name) != null) {
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
	public Material create(String name, Material material) {
		Material materialName = materialRepo.findByName(name);
		if (material != null) {
			material.setName(name);
			materialRepo.saveAndFlush(material);
			return material;
		}
		return null;
	}

	@Override
	public Material update(String name, int materialId, Material material) {
		Material existing = materialRepo.findByName(name);
		if (existing != null) {
			existing.setName(material.getName());
			existing.setDescription(material.getDescription());
			materialRepo.saveAndFlush(existing);
			return existing;
		}
		return material;
	}

	@Override
	public boolean destroy(String name, int materialId) {
		boolean deleted = false;
		Material toDelete = materialRepo.findByName(name);
		if (toDelete != null) {
			toDelete.setActive(false);
			materialRepo.saveAndFlush(toDelete);
			deleted = true;
		}
		return deleted;
	}

}
