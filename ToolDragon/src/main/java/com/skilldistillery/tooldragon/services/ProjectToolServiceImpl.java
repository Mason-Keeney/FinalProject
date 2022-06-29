package com.skilldistillery.tooldragon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.Project;
import com.skilldistillery.tooldragon.entities.ProjectTool;
import com.skilldistillery.tooldragon.entities.ProjectToolId;
import com.skilldistillery.tooldragon.entities.Tool;
import com.skilldistillery.tooldragon.entities.User;
import com.skilldistillery.tooldragon.repositories.ProjectRepository;
import com.skilldistillery.tooldragon.repositories.ProjectToolRepository;
import com.skilldistillery.tooldragon.repositories.ToolRepository;
import com.skilldistillery.tooldragon.repositories.UserRepository;

@Service
public class ProjectToolServiceImpl implements ProjectToolService {

	@Autowired
	private ProjectToolRepository projToolRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private ToolRepository toolRepo;

	@Override
	public List<ProjectTool> index(String username) {
		List<ProjectTool> tools = null;
		if (userRepo.findByUsername(username) != null) {
			tools = projToolRepo.findAll();
		}
		return tools;
	}

	@Override
	public ProjectTool show(String username, ProjectToolId projToolId) {
		ProjectTool tool = null;
		if(userRepo.findByUsername(username) != null) {
			Optional<ProjectTool> op = projToolRepo.findById(projToolId);
			if(op.isPresent()) {
				tool = op.get();
			}
		}
		return tool;
	}

	@Override
	public ProjectTool create(String username, ProjectTool projTool, int pid, int tid) {
		User user = userRepo.findByUsername(username);
		Project project = null;
		Tool tool = null;
		Optional<Project> projectOpt = projectRepo.findById(pid);
		if(projectOpt.isPresent()) {
			project = projectOpt.get();
		}
		
		Optional<Tool> toolOpt = toolRepo.findById(tid);
		if(toolOpt.isPresent()) {
			tool = toolOpt.get();
		}
		ProjectToolId id = new ProjectToolId();
		id.setProjectId(pid);
		id.setToolId(tid);
		
		if (user != null) {
			if (projTool != null) {
				projTool.setProject(project);
				projTool.setTool(tool);
				projTool.setId(id);
				projTool = projToolRepo.saveAndFlush(projTool);
			}
		}
		return projTool;
	}

	@Override
	public ProjectTool update(String username, ProjectTool projTool, ProjectToolId projToolId) {
		ProjectTool managed = null;
		if (userRepo.findByUsername(username) != null) {

			if (projTool != null) {
				Optional<ProjectTool> op = projToolRepo.findById(projToolId);
				if (op.isPresent()) {
					managed = op.get();
					if (managed != null) {
						managed.setDateApproved(projTool.getDateApproved());
						managed.setProjectComment(projTool.getProjectComment());
						managed.setProjectOwnerRating(projTool.getProjectOwnerRating());
						managed.setProjectOwnerRatingComment(projTool.getProjectOwnerRatingComment());
						managed.setProjectOwnerRatingDate(projTool.getProjectOwnerRatingDate());
						managed.setToolOwnerRating(projTool.getToolOwnerRating());
						managed.setToolOwnerRatingComment(projTool.getToolOwnerRatingComment());
						managed.setToolOwnerRatingDate(projTool.getToolOwnerRatingDate());
						projToolRepo.saveAndFlush(managed);
					}
				}

			}

		}
		return managed;
	}

	@Override
	public boolean destroy(String username, ProjectToolId projToolId) {
		boolean deleted = false;
		ProjectTool toDelete = null;
		if (userRepo.findByUsername(username) != null) {
			Optional<ProjectTool> op = projToolRepo.findById(projToolId);
			if (op.isPresent()) {
				toDelete = op.get();
				if (toDelete != null) {
					projToolRepo.delete(toDelete);
					deleted = true;
				}
			}
		}
		return deleted;
	}

}
