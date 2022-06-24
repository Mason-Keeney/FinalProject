package com.skilldistillery.tooldragon.services;

import java.util.List;

import com.skilldistillery.tooldragon.entities.Material;

public interface MaterialService {

	public Material getUserById(int materialId);

	public List<Material> index(String username);

	public Material show(String username, int materialId);

	public Material create(String username, Material material);

	public Material update(String username, int materialId, Material material);

	public boolean destroy(String username, int materialId);

}
