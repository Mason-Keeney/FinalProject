package com.skilldistillery.tooldragon.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tooldragon.entities.Participant;
import com.skilldistillery.tooldragon.entities.ParticipantId;
import com.skilldistillery.tooldragon.entities.ProjectTool;
import com.skilldistillery.tooldragon.entities.ProjectToolId;
import com.skilldistillery.tooldragon.services.ParticipantService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class ParticipantController {
	
	@Autowired
	private ParticipantService participantServ;
	
	
	@GetMapping("participants")
	public List<Participant> index(Principal principal, HttpServletResponse res, HttpServletRequest req){
		List<Participant> tools = null;
		try {
			tools = participantServ.index(principal.getName());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
		}
		
		return tools;
	}
	
	@GetMapping("participants/{pid}/{uid}")
	public Participant show(Principal principal, HttpServletResponse res, HttpServletRequest req, @PathVariable int pid, @PathVariable int uid) {
		Participant participant = null;
		ParticipantId id = new ParticipantId();
		id.setProjectId(pid);
		id.setUserId(uid);
		try {
			participant = participantServ.show(principal.getName(), id);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
		}
		return participant;
	}
	
	
	
	
	
	

}
