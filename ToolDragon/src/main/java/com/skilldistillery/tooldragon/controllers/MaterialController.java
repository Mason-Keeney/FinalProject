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

import com.skilldistillery.tooldragon.entities.Material;
import com.skilldistillery.tooldragon.services.MaterialService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class MaterialController {

	@Autowired
	private MaterialService materialServ;

	@GetMapping("materials")
	public List<Material> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		List<Material> materials = materialServ.index(principal.getName());
		if (materials == null) {
			res.setStatus(404);
		}
		return materials;
	}

	@GetMapping("materials/{aid}")
	public Material show(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid, Principal principal) {
		Material material = materialServ.show(principal.getName(), aid);
		if (material == null) {
			res.setStatus(404);
		}
		return material;
	}

	@PostMapping("materials")
	public Material create(HttpServletRequest req, HttpServletResponse res, @RequestBody Material material,
			Principal principal) {
		material = materialServ.create(principal.getName(), material);
		try {
			if (material == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(material.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Todo JSON");
			res.setStatus(400);
			material = null;

		}
		return material;
	}

	@PutMapping("materials/{aid}")
	public Material update(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid,
			@RequestBody Material material, Principal principal) {
		material = materialServ.update(principal.getName(), aid, material);
		if (material == null) {
			res.setStatus(404);
		}
		return material;
	}

	@DeleteMapping("materials/{aid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid, Principal principal) {
		try {
			if (materialServ.destroy(principal.getName(), aid)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

}
