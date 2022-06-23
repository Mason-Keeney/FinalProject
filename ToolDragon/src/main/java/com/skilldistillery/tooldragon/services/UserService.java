package com.skilldistillery.tooldragon.services;

import java.util.Set;

import com.skilldistillery.tooldragon.entities.User;

public interface UserService {

	public User getUserById(int userId);

	public Set<User> index(String username);

	public User show(String username, int accountId);

	public User create(String username, User user);

	public User update(String username, int accountId, User user);

	public boolean destroy(String username, int accountId);

}
