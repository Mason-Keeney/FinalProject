package com.skilldistillery.tooldragon.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tooldragon.entities.ProjectTool;
import com.skilldistillery.tooldragon.entities.ProjectToolId;
import com.skilldistillery.tooldragon.services.ProjectToolService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class ProjectToolController {
	
	@Autowired
	private ProjectToolService projToolService;
	
	@GetMapping("projecttools")
	public List<ProjectTool> index(Principal principal, HttpServletResponse res, HttpServletRequest req){
		List<ProjectTool> tools = null;
		try {
			tools = projToolService.index(principal.getName());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
		}
		
		return tools;
	}
	
	@GetMapping("projecttools/{pid}/{tid}")
	public ProjectTool show(Principal principal, HttpServletResponse res, HttpServletRequest req, @PathVariable int pid, @PathVariable int tid) {
		ProjectTool tool = null;
		ProjectToolId id = new ProjectToolId();
		id.setProjectId(pid);
		id.setToolId(tid);
		try {
			tool = projToolService.show(principal.getName(), id);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
		}
		
		return tool;
		
	}
	
	@PostMapping("projecttools")
	public ProjectTool create(Principal principal, HttpServletResponse res, HttpServletRequest req, @RequestBody ProjectTool projTool) {
		ProjectTool tool = null;
		ProjectToolId id = new ProjectToolId();
		id.setProjectId(projTool.getProject().getId());
		id.setToolId(projTool.getTool().getId());
		projTool.setId(id);
		
		try {
			tool = projToolService.create(principal.getName(), projTool);
			if(tool != null) {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(tool.getId().getProjectId()).append("/").append(tool.getId().getToolId());
				res.setHeader("Location", url.toString());
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
		return tool;
	}
	
	@PutMapping("projecttools/{pid}/{tid}")
	public ProjectTool update(Principal principal, HttpServletResponse res, @PathVariable int pid, @PathVariable int tid, @RequestBody ProjectTool projTool) {
		ProjectToolId id = new ProjectToolId();
		id.setProjectId(pid);
		id.setToolId(tid);
		try {
			if(projTool == null) {
				res.setStatus(404);
			}
			projTool = projToolService.update(principal.getName(), projTool, id);
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
		return projTool;
	}
	
	@DeleteMapping("projecttools/{pid}/{tid}")
	public void delete(Principal principal, HttpServletResponse res, @PathVariable int pid, @PathVariable int tid) {
		ProjectToolId id = new ProjectToolId();
		id.setProjectId(pid);
		id.setToolId(tid);
		try {
			boolean deleted = projToolService.destroy(principal.getName(), id);
			if(deleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(500);
		}
	}
}
