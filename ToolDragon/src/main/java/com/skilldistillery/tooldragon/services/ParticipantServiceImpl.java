package com.skilldistillery.tooldragon.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.Participant;
import com.skilldistillery.tooldragon.entities.User;
import com.skilldistillery.tooldragon.repositories.ParticapantRepository;

@Service
public class ParticipantServiceImpl implements ParticipantService {

	@Autowired
	private ParticapantRepository participantRepo;

	@Override
	public Participant getUserById(int participantId, User userId, String username) {
		
		Optional<Participant> participantOpt = participantRepo.findById(userId);
		Participant participant = null;
		if (participantOpt.isPresent()) {
			participant = participantOpt.get();
		}
		return participant;
	}

	
	
	
	
}
