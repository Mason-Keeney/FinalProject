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

import com.skilldistillery.tooldragon.entities.Status;
import com.skilldistillery.tooldragon.services.StatusService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class StatusController {

	@Autowired
	private StatusService statusServ;

	@GetMapping("status")
	public List<Status> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		List<Status> statuses = statusServ.index(principal.getName());
		if (statuses == null) {
			res.setStatus(404);
		}
		return statuses;
	}

	@GetMapping("status/{sid}")
	public Status show(HttpServletRequest req, HttpServletResponse res, @PathVariable int sid, Principal principal) {
		Status status = statusServ.show(principal.getName(), sid);
		if (status == null) {
			res.setStatus(404);
		}
		return status;
	}

	@PostMapping("status")
	public Status create(HttpServletRequest req, HttpServletResponse res, @RequestBody Status status,
			Principal principal) {
		status = statusServ.create(principal.getName(), status);
		try {
			if (status == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(status.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Todo JSON");
			res.setStatus(400);
			status = null;

		}
		return status;
	}

	@PutMapping("status/{sid}")
	public Status update(HttpServletRequest req, HttpServletResponse res, @PathVariable int sid,
			@RequestBody Status status, Principal principal) {
		status = statusServ.update(principal.getName(), sid, status);
		if (status == null) {
			res.setStatus(404);
		}
		return status;
	}

	@DeleteMapping("status/{sid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int sid, Principal principal) {
		try {
			if (statusServ.destroy(principal.getName(), sid)) {
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
