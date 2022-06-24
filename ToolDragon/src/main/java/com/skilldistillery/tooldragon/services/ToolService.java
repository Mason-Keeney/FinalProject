package com.skilldistillery.tooldragon.services;

import java.util.List;

import com.skilldistillery.tooldragon.entities.Tool;

public interface ToolService {
	
	public Tool getUserById(int toolId);

	public List<Tool> index(String name);

	public Tool show(String name, int toolId);

	public Tool create(String name, Tool tool);

	public Tool update(String name, int toolId, Tool tool);

	public boolean destroy(String name, int toolId);

}
