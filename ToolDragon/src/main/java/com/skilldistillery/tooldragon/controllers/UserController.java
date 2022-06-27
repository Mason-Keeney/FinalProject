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

import com.skilldistillery.tooldragon.entities.User;
import com.skilldistillery.tooldragon.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("users")
	public List<User> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		List<User> users = userService.index(principal.getName());
		if (users == null) {
			res.setStatus(404);
		}
		return users;
	}

	@GetMapping("users/{aid}")
	public User show(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid, Principal principal) {
		User user = userService.show(principal.getName(), aid);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}

	@PostMapping("users")
	public User create(HttpServletRequest req, HttpServletResponse res, @RequestBody User user, Principal principal) {
		user = userService.create(principal.getName(), user);
		try {
			if (user == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(user.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Invalid Todo JSON");
			res.setStatus(400);
			user = null;

		}
		return user;
	}

	@PutMapping("users/{aid}")
	public User update(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid, @RequestBody User user,
			Principal principal) {
		
		try {
			user = userService.update(principal.getName(), aid, user);
			if (user == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return user;
	}

	@DeleteMapping("users/{aid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid, Principal principal) {
		try {
			if (userService.destroy(principal.getName(), aid)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

	// SMOKE TEST ONLY, DELETE/COMMENT OUT LATER
//	@GetMapping("test/users/{userId}")
//	public User getUserForTest(@PathVariable Integer userId, HttpServletResponse res) {
//		User user = userService.getUserById(userId);
//		if (user == null) {
//			res.setStatus(404);
//		}
//		return user;
//	}

}
