package com.skilldistillery.tooldragon.services;

import java.util.List;

import com.skilldistillery.tooldragon.entities.Participant;
import com.skilldistillery.tooldragon.entities.ParticipantId;
import com.skilldistillery.tooldragon.entities.User;

public interface ParticipantService {

	public Participant getParticipantById(ParticipantId participantId, User userId, String username);

	List<Participant> index(String username);

	Participant show(String username, ParticipantId participantId);

	Participant create(String username, Participant participant);

	Participant update(String username, Participant participant, ParticipantId participantId);

	boolean destroy(String username, ParticipantId participantId);

}
