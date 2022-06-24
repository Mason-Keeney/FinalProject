package com.skilldistillery.tooldragon.services;

import java.util.List;

import com.skilldistillery.tooldragon.entities.ProjectTool;
import com.skilldistillery.tooldragon.entities.ProjectToolId;

public interface ProjectToolService {
	
	List<ProjectTool> index(String Username);
	ProjectTool create(String Username, ProjectTool projTool);
	ProjectTool update(String Username, ProjectTool projTool, ProjectToolId projToolId);
	boolean destroy(String Username, ProjectToolId projToolId);

}
