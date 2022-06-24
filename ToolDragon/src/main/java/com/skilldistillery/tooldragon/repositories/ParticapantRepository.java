package com.skilldistillery.tooldragon.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tooldragon.entities.Participant;
import com.skilldistillery.tooldragon.entities.ParticipantId;
import com.skilldistillery.tooldragon.entities.User;

public interface ParticapantRepository extends JpaRepository<Participant, ParticipantId> {

	Optional<Participant> findById(User userId);

	Object findByUsername(String username);

}
