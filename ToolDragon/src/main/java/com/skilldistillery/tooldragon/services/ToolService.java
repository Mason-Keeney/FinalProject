package com.skilldistillery.tooldragon.services;

import java.util.List;

import com.skilldistillery.tooldragon.entities.Tool;

public interface ToolService {
	
	public Tool getUserById(int toolId);

	public List<Tool> index(String username);

	public Tool show(String username, int toolId);

	public Tool create(String username, Tool tool);

	public Tool update(String username, int toolId, Tool tool);

	public boolean destroy(String username, int toolId);
	
	public List<Tool> indexAll();

}
