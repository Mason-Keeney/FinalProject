package com.skilldistillery.tooldragon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tooldragon.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	
	
}
