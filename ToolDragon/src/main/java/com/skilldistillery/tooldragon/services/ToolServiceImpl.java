package com.skilldistillery.tooldragon.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.Tool;
import com.skilldistillery.tooldragon.entities.User;
import com.skilldistillery.tooldragon.repositories.ToolRepository;
import com.skilldistillery.tooldragon.repositories.UserRepository;

@Service
public class ToolServiceImpl implements ToolService {

	@Autowired
	private ToolRepository toolRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Tool getUserById(int toolId) {
		Optional<Tool> toolOpt = toolRepo.findById(toolId);
		Tool tool = null;
		if (toolOpt.isPresent()) {
			tool = toolOpt.get();
		}
		return tool;
	}

	@Override
	public List<Tool> index(String username) {
		List<Tool> tools = null;
		List<Tool> filteredTools = null;
		if (userRepo.findByUsername(username) != null) {
			tools = toolRepo.findAll();
			filteredTools = new ArrayList<>();
			if (tools != null && !tools.isEmpty()) {
				for (Tool tool : tools) {
					if (tool.getActive()) {
						filteredTools.add(tool);
					}
				}
			}
		}
		return filteredTools;
	}

	@Override
	public Tool show(String username, int toolId) {
		Tool tool = null;
		if (toolRepo.findByName(username) != null) {
			Optional<Tool> op = toolRepo.findById(toolId);
			if (op.isPresent()) {
				tool = op.get();
				if (!tool.getActive()) {
					tool = null;
				}
			}
		}
		return tool;
	}

	@Override
	public Tool create(String username, Tool tool) {
		User owner = userRepo.findByUsername(username);
		if (tool != null) {
			tool.setOwner(owner);
			toolRepo.saveAndFlush(tool);
			return tool;
		}
		return null;
	}

	@Override
	public Tool update(String username, int toolId, Tool tool) {
		Tool existing = toolRepo.findByName(username);
		if (existing != null) {
			existing.setName(tool.getName());
			existing.setDescription(tool.getDescription());
			existing.setAvailability(tool.getAvailability());
			existing.setTrainingRequired(tool.getTrainingRequired());
			existing.setOperators(tool.getOperators());
			existing.setImageUrl(tool.getImageUrl());
			existing.setCreatedAt(tool.getCreatedAt());
			existing.setUpdatedAt(tool.getUpdatedAt());
			existing.setAvailable(tool.getAvailable());
			toolRepo.saveAndFlush(existing);
			return existing;
		}
		return tool;
	}

	@Override
	public boolean destroy(String username, int toolId) {
		boolean deleted = false;
		Tool toDelete = toolRepo.findByName(username);
		if (toDelete != null) {
			toDelete.setActive(false);
			toolRepo.saveAndFlush(toDelete);
			deleted = true;
		}
		return deleted;
	}

}
