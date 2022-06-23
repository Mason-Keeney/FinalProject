package com.skilldistillery.tooldragon.services;

import java.util.List;

import com.skilldistillery.tooldragon.entities.Address;

public interface AddressService {
	
	public Address getAddressById(int addressId);

	public List<Address> index(String address);

	public Address show(int addressId);

	public Address create(Address address);

	public Address update(Address address, int addressId);

	public boolean destroy(int addressId);

}
