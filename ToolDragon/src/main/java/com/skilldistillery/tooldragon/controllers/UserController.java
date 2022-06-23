package com.skilldistillery.tooldragon.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Set<User> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
//		Set<Todo> todos = todoService.index(username);
		Set<User> todos = userService.index(principal.getName());
		if (todos == null) {
			res.setStatus(404);
		}
		return todos;
	}
	
	
	
	
	
	
	
	
	
	

	// SMOKE TEST ONLY, DELETE/COMMENT OUT LATER
	@GetMapping("test/users/{userId}")
	public User getUserForTest(@PathVariable Integer userId, HttpServletResponse res) {
		User user = userService.getUserById(userId);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}

}
