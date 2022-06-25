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

import com.skilldistillery.tooldragon.entities.Participant;
import com.skilldistillery.tooldragon.entities.ParticipantId;
import com.skilldistillery.tooldragon.services.ParticipantService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class ParticipantController {

	@Autowired
	private ParticipantService participantServ;

	@GetMapping("participants")
	public List<Participant> index(Principal principal, HttpServletResponse res, HttpServletRequest req) {
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
	public Participant show(Principal principal, HttpServletResponse res, HttpServletRequest req, @PathVariable int pid,
			@PathVariable int uid) {
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

	@PostMapping("participants")
	public Participant create(Principal principal, HttpServletResponse res, HttpServletRequest req,
			@RequestBody Participant participantId) {
		Participant participant = null;
		ParticipantId id = new ParticipantId();
		id.setProjectId(participantId.getProject().getId());
		id.setUserId(participantId.getUser().getId());
		participantId.setId(id);
		try {
			participant = participantServ.create(principal.getName(), participantId);
			if (participant != null) {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(participant.getId().getProjectId()).append("/")
						.append(participant.getId().getUserId());
				res.setHeader("Location", url.toString());
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return participant;
	}

	@PutMapping("participants/{pid}/{uid}")
	public Participant update(Principal principal, HttpServletResponse res, @PathVariable int pid,
			@PathVariable int uid, @RequestBody Participant participantId) {
		ParticipantId id = new ParticipantId();
		id.setProjectId(pid);
		id.setUserId(uid);
		try {
			if (participantId == null) {
				res.setStatus(404);
			}
			participantId = participantServ.update(principal.getName(), participantId, id);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return participantId;
	}

	@DeleteMapping("participants/{pid}/{uid}")
	public void delete(Principal principal, HttpServletResponse res, @PathVariable int pid, @PathVariable int uid) {
		ParticipantId id = new ParticipantId();
		id.setProjectId(pid);
		id.setUserId(uid);
		try {
			boolean deleted = participantServ.destroy(principal.getName(), id);
			if (deleted) {
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
