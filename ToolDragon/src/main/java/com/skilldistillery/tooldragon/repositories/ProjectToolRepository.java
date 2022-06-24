package com.skilldistillery.tooldragon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tooldragon.entities.ProjectTool;
import com.skilldistillery.tooldragon.entities.ProjectToolId;

public interface ProjectToolRepository extends JpaRepository<ProjectTool, ProjectToolId>{

}
