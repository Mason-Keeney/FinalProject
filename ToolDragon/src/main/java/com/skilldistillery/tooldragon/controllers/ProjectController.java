package com.skilldistillery.tooldragon.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

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

import com.skilldistillery.tooldragon.entities.Project;
import com.skilldistillery.tooldragon.services.ProjectService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class ProjectController {

	@Autowired
	private ProjectService projServ;

	@GetMapping("projects/all")
	private List<Project> indexAll(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		List<Project> projects = projServ.indexAll();
		if (projects == null) {
			res.setStatus(404);
		}
		return projects;
	}

	@GetMapping("projects")
	private Set<Project> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		Set<Project> projects = projServ.index(principal.getName());
		if (projects == null) {
			res.setStatus(404);
		}
		return projects;
	}

	@GetMapping("projects/{id}")
	private Project show(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int id) {
		Project project = projServ.show(principal.getName(), id);
		if (project == null) {
			res.setStatus(404);
		}
		return project;
	}

	@PostMapping("projects")
	private Project create(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@RequestBody Project project) {
		try {
			project = projServ.create(project, principal.getName());
			if (project == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(project.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Todo JSON");
			res.setStatus(400);
			project = null;

		}
		return project;
	}

	@PutMapping("projects/{id}")
	private Project update(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@RequestBody Project project, @PathVariable int id) {
		project = projServ.update(project, id, principal.getName());
		if (project == null) {
			res.setStatus(404);
		}
		return project;
	}

	@DeleteMapping("projects/{id}")
	private void destory(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int id) {
		try {
			if (projServ.destroy(principal.getName(), id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

	@GetMapping("projects/search/{keyword}")
	private List<Project> searchByKeyword(@PathVariable String keyword, HttpServletResponse res) {
		List<Project> projectResults = null;
		try {
			projectResults = projServ.findByKeyword(keyword);
			res.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return projectResults;
	}

}
