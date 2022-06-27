package com.skilldistillery.tooldragon.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tooldragon.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

	Set<Project> findByDescription(String projectDescription);

	Optional<Project> findById(int projectId);
	
	Set<Project> findByOwner_Username(String username);

	List<Project> findByDescriptionContains(String description);
}
