package com.skilldistillery.tooldragon.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tooldragon.entities.Participant;
import com.skilldistillery.tooldragon.entities.User;

public interface ParticapantRepository extends JpaRepository<Participant, Integer> {

	Optional<Participant> findById(User userId);

}
