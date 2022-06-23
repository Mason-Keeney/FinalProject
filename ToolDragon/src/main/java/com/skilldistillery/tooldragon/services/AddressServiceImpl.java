package com.skilldistillery.tooldragon.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.Address;
import com.skilldistillery.tooldragon.repositories.AddressRepository;
import com.skilldistillery.tooldragon.repositories.UserRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Address> index(String username) {
		List<Address> addresses = null;
		List<Address> filteredAddresses = null;
		if (userRepo.findByUsername(username) != null) {
			addresses = addressRepo.findAll();
			if (addresses != null && !addresses.isEmpty()) {
				filteredAddresses = new ArrayList<>();
				for (Address addr : addresses) {
					if (addr.getActive()) {
						filteredAddresses.add(addr);
					}
				}
			}
		}
		return filteredAddresses;
	}

	@Override
	public Address show(String username, int addressId) {
		Address address = null;
		if (userRepo.findByUsername(username) != null) {
			Optional<Address> op = addressRepo.findById(addressId);
			if (op.isPresent()) {
				address = op.get();
				if (!address.getActive()) {
					address = null;
				}
			}
		}
		return address;
	}

	@Override
	public Address create(String username, Address address) {
		if (userRepo.findByUsername(username) != null && address != null) {
			address.setActive(true);
			addressRepo.saveAndFlush(address);
		}
		return address;
	}

	@Override
	public Address update(String username, Address address, int addressId) {
		Address existing = null;
		if (userRepo.findByUsername(username) != null) {

			Optional<Address> op = addressRepo.findById(addressId);
			if (op.isPresent()) {
				existing = op.get();
				if (!existing.getActive()) {
					existing = null;
				}
			}
			if (existing != null) {
				existing.setStreet1(address.getStreet1());
				existing.setStreet2(address.getStreet2());
				existing.setCity(address.getCity());
				existing.setState(address.getState());
				existing.setPostalCode(address.getPostalCode());
				addressRepo.saveAndFlush(existing);
			}
		}

		return existing;
	}

	@Override
	public boolean destroy(String username, int addressId) {
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
	public Address getAddressById(String username, int addressId) {
		Address address = null;
		if (userRepo.findByUsername(username) != null) {

			Optional<Address> addressOpt = addressRepo.findById(addressId);
			if (addressOpt.isPresent()) {
				address = addressOpt.get();
			}
		}
		return address;
	}

}
