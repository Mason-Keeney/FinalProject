package com.skilldistillery.tooldragon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.Participant;
import com.skilldistillery.tooldragon.entities.ParticipantId;
import com.skilldistillery.tooldragon.entities.Project;
import com.skilldistillery.tooldragon.entities.User;
import com.skilldistillery.tooldragon.repositories.ParticapantRepository;
import com.skilldistillery.tooldragon.repositories.ProjectRepository;
import com.skilldistillery.tooldragon.repositories.UserRepository;

@Service
public class ParticipantServiceImpl implements ParticipantService {

	@Autowired
	private ParticapantRepository participantRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProjectRepository projectRepo;

	@Override
	public Participant getParticipantById(ParticipantId participantId, User userId, String username) {
		Optional<Participant> participantOpt = participantRepo.findById(participantId);
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
		if (userRepo.findByUsername(username) != null) {
			Optional<Participant> op = participantRepo.findById(participantId);
			if (op.isPresent()) {
				participant = op.get();
			}
		}
		return participant;
	}

	@Override
	public Participant create(String username, Participant participant, int pid) {

		User user = userRepo.findByUsername(username);
		if (user != null) {
			participant.setUser(user);
			Optional<Project> projectOpt = projectRepo.findById(pid);
			
			if(projectOpt.isPresent()) {
				Project p = projectOpt.get();
				participant.setProject(p);
				ParticipantId id = new ParticipantId(p.getId(), user.getId());
				participant.setId(id);
			}
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
		System.out.println(participantId);
		Participant toDelete = null;
		if (userRepo.findByUsername(username) != null) {
			System.out.println("reached after user test");
			Optional<Participant> op = participantRepo.findById(participantId);
			if (op.isPresent()) {
				System.out.println("reached op.isPresent() test");
				toDelete = op.get();
				if (toDelete != null) {
					System.out.println("found participant");
					participantRepo.delete(toDelete);
					deleted = true;
				}
			}
		}
		return deleted;
	}

}
