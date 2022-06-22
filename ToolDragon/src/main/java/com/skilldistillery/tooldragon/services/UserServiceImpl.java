package com.skilldistillery.tooldragon.services;

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

}
