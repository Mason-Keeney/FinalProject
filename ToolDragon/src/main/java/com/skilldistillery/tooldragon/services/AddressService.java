package com.skilldistillery.tooldragon.services;

import java.util.List;

import com.skilldistillery.tooldragon.entities.Address;

public interface AddressService {
	
	public Address getAddressById(String username, int addressId);

	public List<Address> index(String username);

	public Address show(String username, int addressId);

	public Address create(String username, Address address);

	public Address update(String username, Address address, int addressId);

	public boolean destroy(String username, int addressId);

}
