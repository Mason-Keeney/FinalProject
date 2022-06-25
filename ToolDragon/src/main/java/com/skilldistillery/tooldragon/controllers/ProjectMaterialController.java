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

import com.skilldistillery.tooldragon.entities.ProjectMaterial;
import com.skilldistillery.tooldragon.services.ProjectMaterialService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class ProjectMaterialController {
	
	@Autowired
	private ProjectMaterialService projMaterialServ;
	
	@GetMapping("projectmaterials")
	public List<ProjectMaterial> index(Principal principal, HttpServletResponse res){
		List<ProjectMaterial> materials = null;
		try {
			materials = projMaterialServ.index(principal.getName());
			if(materials == null) {
				res.setStatus(404);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(500);
		}
		return materials;
	}
	
	@GetMapping("projectmaterials/{pid}")
	public ProjectMaterial show(Principal principal, HttpServletResponse res, @PathVariable int pid) {
		ProjectMaterial projMat = null;
		try {
			projMat = projMaterialServ.show(principal.getName(), pid);
			if(projMat == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return projMat;
	}
	
	@PostMapping("projectmaterials")
	public ProjectMaterial create(Principal principal, HttpServletResponse res, HttpServletRequest req, @RequestBody ProjectMaterial projectMaterial) {
		try {
			projectMaterial = projMaterialServ.create(principal.getName(), projectMaterial);
			if(projectMaterial != null) {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(projectMaterial.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
		
		return projectMaterial;
	}
	
	
	@PutMapping("projectmaterials/{pid}")
	public ProjectMaterial update(Principal principal, HttpServletResponse res, HttpServletRequest req, @RequestBody ProjectMaterial projectMaterial, @PathVariable int pid) {
		try {
			projectMaterial = projMaterialServ.update(principal.getName(), projectMaterial, pid);
			if(projectMaterial == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
		return projectMaterial;
	}
	
	@DeleteMapping("projectmaterials/{pid}")
	public void delete(Principal principal, HttpServletResponse res, HttpServletRequest req, @PathVariable int pid) {
		try {
			boolean deleted = projMaterialServ.delete(principal.getName(), pid);
			if(deleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}	
		} catch (Exception e) {
			res.setStatus(400);
		}
	}

}
