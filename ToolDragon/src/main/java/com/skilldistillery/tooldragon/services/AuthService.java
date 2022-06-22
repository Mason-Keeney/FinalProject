package com.skilldistillery.tooldragon.services;

import com.skilldistillery.tooldragon.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);

}
