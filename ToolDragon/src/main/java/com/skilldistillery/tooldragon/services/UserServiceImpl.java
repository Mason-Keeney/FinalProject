package com.skilldistillery.tooldragon.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public List<User> index(String username) {
		List<User> users = null;
		List<User> filteredUsers = null;
		if (userRepo.findByUsername(username) != null) {
			users = userRepo.findAll();
			filteredUsers = new ArrayList<>();
			if (users != null && !users.isEmpty()) {
				for (User user : users) {
					if (user.isEnabled()) {
						filteredUsers.add(user);
					}
				}
			}
		}
		return filteredUsers;
	}

	@Override
	public User show(String username, int accountId) {
		User user = null;
		if (userRepo.findByUsername(username) != null) {
			Optional<User> op = userRepo.findById(accountId);
			if (op.isPresent()) {
				user = op.get();
				if (!user.isEnabled()) {
					user = null;
				}
			}
		}
		return user;
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
		User existing = null;
		User sessionUser = userRepo.findByUsername(username);
		Optional<User> op = userRepo.findById(accountId);
		if (op.isPresent()) {
			existing = op.get();
			if (!existing.getUsername().equals(username)|| !sessionUser.getRole().equals("role_admin")) {
				existing = null;
			}
		}
		if (existing != null && existing.getUsername().equals(username)) {
			existing.setFirstName(user.getFirstName());
			existing.setLastName(user.getLastName());
			existing.setImageUrl(user.getImageUrl());
			existing.setDescription(user.getDescription());
			existing.setCreatedAt(user.getCreatedAt());
			existing.setUpdatedAt(user.getUpdatedAt());
			existing.setLastLogin(user.getLastLogin());
			existing.setBackgroundImageUrl(user.getBackgroundImageUrl());
			existing.setRole(user.getRole());
			userRepo.saveAndFlush(existing);
		}

		return existing;
	}

	@Override
	public boolean destroy(String username, int accountId) {
		boolean deleted = false;
		User toDelete = null;
		User sessionUser = userRepo.findByUsername(username);
		Optional<User> op = userRepo.findById(accountId);
		if (op.isPresent()) {
			toDelete = op.get();
			if (!toDelete.getUsername().equals(username) || !sessionUser.getRole().equals("role_admin")) {
				toDelete = null;
			}
		}
		if (toDelete != null) {
			toDelete.setEnabled(false);
			userRepo.saveAndFlush(toDelete);
			deleted = true;
		}
		return deleted;
	}

}
