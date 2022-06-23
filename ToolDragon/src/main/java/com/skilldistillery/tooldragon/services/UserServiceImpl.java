package com.skilldistillery.tooldragon.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.User;
import com.skilldistillery.tooldragon.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User getUserById(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		User user = null;
		if (userOpt.isPresent()) {
			user = userOpt.get();
		}
		return user;
	}

	@Override
	public Set<User> index(String username) {
		if (userRepo.findByUsername_Username(username) == null) {
			return null;
		}
		return null;
	}

	@Override
	public User show(String username, int accountId) {
		return userRepo.findByIdAndUsername_Username(accountId, username);
	}

	@Override
	public User create(String username, User user) {
		User name = userRepo.findByUsername(username);
		if (user != null) {
			user.setUsername(username);
			userRepo.saveAndFlush(user);
			return user;
		}
		return null;
	}

	@Override
	public User update(String username, int accountId, User user) {
		User existing = userRepo.findByUsername(username);
		if (existing != null) {
			existing.setFirstName(user.getFirstName());
			existing.setLastName(user.getLastName());
			existing.setUsername(user.getUsername());
			existing.setPassword(user.getPassword());
			existing.setImageUrl(user.getImageUrl());
			existing.setDescription(user.getDescription());
			existing.setCreatedAt(user.getCreatedAt());
			existing.setUpdatedAt(user.getUpdatedAt());
			existing.setLastLogin(user.getLastLogin());
			existing.setBackgroundImageUrl(user.getBackgroundImageUrl());
			existing.setRole(user.getRole());
			userRepo.saveAndFlush(existing);
			return existing;
		}
		return user;
	}

	@Override
	public boolean destroy(String username, int accountId) {
		boolean deleted = false;
		User toDelete = userRepo.findByUsername(username);
		if (toDelete != null) {
			toDelete.setEnabled(false);
			userRepo.saveAndFlush(toDelete);
			deleted = true;
		}
		return deleted;
	}

}
