package com.skilldistillery.tooldragon.services;

import java.util.List;

import com.skilldistillery.tooldragon.entities.ProjectTool;
import com.skilldistillery.tooldragon.entities.ProjectToolId;

public interface ProjectToolService {
	
	List<ProjectTool> index(String Username);
	ProjectTool show(String Username, ProjectToolId projToolId);
	ProjectTool create(String Username, ProjectTool projTool, int pid, int tid);
	ProjectTool update(String Username, ProjectTool projTool, ProjectToolId projToolId);
	boolean destroy(String Username, ProjectToolId projToolId);

}
