package com.skilldistillery.tooldragon.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tooldragon.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	Set<Address> findByAddress(String address);
	
	Address findByIdAndAddress(int addressId, Address address);

	Address findByAddress(Address address);

	Address findByIdAndUser_Username(int addressId, Address address);


}
