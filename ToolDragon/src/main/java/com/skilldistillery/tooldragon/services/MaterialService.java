package com.skilldistillery.tooldragon.services;

import java.util.List;

import com.skilldistillery.tooldragon.entities.Material;

public interface MaterialService {

	public Material getUserById(int materialId);

	public List<Material> index(String name);

	public Material show(String name, int materialId);

	public Material create(String name, Material material);

	public Material update(String name, int materialId, Material material);

	public boolean destroy(String name, int materialId);

}
