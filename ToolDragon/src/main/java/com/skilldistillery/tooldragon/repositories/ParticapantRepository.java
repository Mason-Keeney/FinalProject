package com.skilldistillery.tooldragon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tooldragon.entities.Participant;
import com.skilldistillery.tooldragon.entities.ParticipantId;

public interface ParticapantRepository extends JpaRepository<Participant, ParticipantId> {

}
