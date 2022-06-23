package com.skilldistillery.tooldragon.services;

import java.util.Set;

import com.skilldistillery.tooldragon.entities.User;

public interface UserService {
	User getUserById(int userId);

	public Set<User> index(String name);

}
