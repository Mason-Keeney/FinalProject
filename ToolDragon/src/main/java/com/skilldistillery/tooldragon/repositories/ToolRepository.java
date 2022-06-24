package com.skilldistillery.tooldragon.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tooldragon.entities.Tool;

public interface ToolRepository extends JpaRepository<Tool, Integer> {

	public Optional<Tool> findById(int toolId);

	public Tool findByName(String username);

	public List<Tool> findAll();

}
