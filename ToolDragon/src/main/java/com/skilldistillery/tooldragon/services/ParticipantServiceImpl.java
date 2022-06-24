package com.skilldistillery.tooldragon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.Participant;
import com.skilldistillery.tooldragon.entities.ParticipantId;
import com.skilldistillery.tooldragon.entities.User;
import com.skilldistillery.tooldragon.repositories.ParticapantRepository;
import com.skilldistillery.tooldragon.repositories.UserRepository;

@Service
public class ParticipantServiceImpl implements ParticipantService {

	@Autowired
	private ParticapantRepository participantRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Participant getUserById(int participantId, User userId, String username) {

		Optional<Participant> participantOpt = participantRepo.findById(userId);
		Participant participant = null;
		if (participantOpt.isPresent()) {
			participant = participantOpt.get();
		}
		return participant;
	}

	@Override
	public List<Participant> index(String username) {
		List<Participant> participants = null;
		if (userRepo.findByUsername(username) != null) {
			participants = participantRepo.findAll();
		}
		return participants;
	}

	@Override
	public Participant show(String username, ParticipantId participantId) {
		Participant participant = null;
		if (participantRepo.findByUsername(username) != null) {
			Optional<Participant> op = participantRepo.findById(participantId);
			if (op.isPresent()) {
				participant = op.get();
			}
		}
		return participant;
	}

	@Override
	public Participant create(String username, Participant participant) {
		if (userRepo.findByUsername(username) != null) {
			if (participant != null) {
				participant = participantRepo.saveAndFlush(participant);
			}
		}
		return participant;
	}

	@Override
	public Participant update(String username, Participant participant, ParticipantId participantId) {
		Participant managed = null;
		if (userRepo.findByUsername(username) != null) {

			if (participant != null) {
				Optional<Participant> op = participantRepo.findById(participantId);
				if (op.isPresent()) {
					managed = op.get();
					if (managed != null) {
						managed.setParticipantComment(participant.getParticipantComment());
						managed.setDateCreated(participant.getDateCreated());
						managed.setDateApproved(participant.getDateApproved());
						managed.setRating(participant.getRating());
						managed.setRatingComment(participant.getRatingComment());
						managed.setRatingDate(participant.getRatingDate());
						participantRepo.saveAndFlush(managed);
					}
				}

			}

		}
		return managed;
	}

	@Override
	public boolean destroy(String username, ParticipantId participantId) {
		boolean deleted = false;
		Participant toDelete = null;
		if (userRepo.findByUsername(username) != null) {
			Optional<Participant> op = participantRepo.findById(participantId);
			if (op.isPresent()) {
				toDelete = op.get();
				if (toDelete != null) {
					participantRepo.delete(toDelete);
					deleted = true;
				}
			}
		}
		return deleted;
	}

}
