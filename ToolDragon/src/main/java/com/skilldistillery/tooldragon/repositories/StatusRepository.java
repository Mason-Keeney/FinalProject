package com.skilldistillery.tooldragon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tooldragon.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}
