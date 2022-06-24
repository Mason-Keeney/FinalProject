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

import com.skilldistillery.tooldragon.entities.Tool;
import com.skilldistillery.tooldragon.services.ToolService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class ToolController {

	@Autowired
	private ToolService toolService;

	@GetMapping("tools")
	public List<Tool> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		List<Tool> tools = toolService.index(principal.getName());
		if (tools == null) {
			res.setStatus(404);
		}
		return tools;
	}

	@GetMapping("tools/{tid}")
	public Tool show(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid, Principal principal) {
		Tool tool = toolService.show(principal.getName(), tid);
		if (tool == null) {
			res.setStatus(404);
		}
		return tool;
	}

	@PostMapping("tools")
	public Tool create(HttpServletRequest req, HttpServletResponse res, @RequestBody Tool tool, Principal principal) {
		tool = toolService.create(principal.getName(), tool);
		try {
			if (tool == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(tool.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Todo JSON");
			res.setStatus(400);
			tool = null;

		}
		return tool;
	}

	@PutMapping("tools/{aid}")
	public Tool update(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid, @RequestBody Tool tool,
			Principal principal) {
		tool = toolService.update(principal.getName(), aid, tool);
		if (tool == null) {
			res.setStatus(404);
		}
		return tool;
	}

	@DeleteMapping("tools/{aid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid, Principal principal) {
		try {
			if (toolService.destroy(principal.getName(), aid)) {
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
