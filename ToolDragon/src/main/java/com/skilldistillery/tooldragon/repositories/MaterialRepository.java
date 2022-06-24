package com.skilldistillery.tooldragon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tooldragon.entities.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
	
	Material findByName(String name);

}
