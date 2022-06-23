package com.skilldistillery.tooldragon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.Address;
import com.skilldistillery.tooldragon.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;

	@Override
	public List<Address> index(String address) {
		return addressRepo.findAll();
	}

	@Override
	public Address show(int addressId) {
		Optional<Address> op = addressRepo.findById(addressId);
		Address address = null;
		if (op.isPresent()) {
			address = op.get();
		}
		return address;
	}

	@Override
	public Address create(Address address) {
		if (address != null) {
			addressRepo.saveAndFlush(address);
		}
		return address;
	}

	@Override
	public Address update(Address address, int addressId) {
		Optional<Address> op = addressRepo.findById(addressId);
		Address existing = null;
		if (op.isPresent()) {
			existing = op.get();
		}
		if (existing != null) {
			existing.setStreet1(address.getStreet1());
			existing.setStreet2(address.getStreet2());
			existing.setCity(address.getCity());
			existing.setState(address.getState());
			existing.setPostalCode(address.getPostalCode());
			addressRepo.saveAndFlush(existing);
			return existing;
		}
		return address;
	}

	@Override
	public boolean destroy(int addressId) {
		Optional<Address> op = addressRepo.findById(addressId);
		boolean deleted = false;
		Address toDelete = null;
		if (op.isPresent()) {
			toDelete = op.get();
		}
		if (toDelete != null) {
			toDelete.setActive(false);
			addressRepo.saveAndFlush(toDelete);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public Address getAddressById(int addressId) {
		Optional<Address> addressOpt = addressRepo.findById(addressId);
		Address address = null;
		if (addressOpt.isPresent()) {
			address = addressOpt.get();
		}
		return address;
	}

}
