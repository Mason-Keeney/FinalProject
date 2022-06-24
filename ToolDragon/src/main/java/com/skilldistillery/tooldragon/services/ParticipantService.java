package com.skilldistillery.tooldragon.services;

import com.skilldistillery.tooldragon.entities.Participant;
import com.skilldistillery.tooldragon.entities.User;

public interface ParticipantService {
	
	public Participant getUserById(int participantId, User userId, String username);

}
