package com.skilldistillery.tooldragon.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tooldragon.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

	Set<Project> findByProjectDescription(String projectDescription);

	Project findByIdAndProjectDescription(int projectId, String projectDescription);

	Optional<Project> findById(int projectId);

}
