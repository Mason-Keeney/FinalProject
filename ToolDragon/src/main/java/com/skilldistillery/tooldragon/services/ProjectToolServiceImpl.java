package com.skilldistillery.tooldragon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.ProjectTool;
import com.skilldistillery.tooldragon.entities.ProjectToolId;
import com.skilldistillery.tooldragon.repositories.ProjectToolRepository;
import com.skilldistillery.tooldragon.repositories.UserRepository;

@Service
public class ProjectToolServiceImpl implements ProjectToolService {

	@Autowired
	private ProjectToolRepository projToolRepo;

	private UserRepository userRepo;

	@Override
	public List<ProjectTool> index(String username) {
		List<ProjectTool> tools = null;
		if (userRepo.findByUsername(username) != null) {
			tools = projToolRepo.findAll();
		}
		return tools;
	}

	@Override
	public ProjectTool create(String Username, ProjectTool projTool) {
		if (projTool != null) {
			projTool = projToolRepo.saveAndFlush(projTool);
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
						managed.setProject(projTool.getProject());
						managed.setTool(projTool.getTool());
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
		if(userRepo.findByUsername(username) != null) {
			Optional<ProjectTool> op = projToolRepo.findById(projToolId);
			if(op.isPresent()) {
				toDelete = op.get();
				if(toDelete != null) {
					projToolRepo.delete(toDelete);
					deleted = true;
				}
			}
		}
		return deleted;
	}

}
