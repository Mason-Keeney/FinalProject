package com.skilldistillery.tooldragon.services;

import java.util.List;

import com.skilldistillery.tooldragon.entities.Status;

public interface StatusService {
	
	public Status getUserById(int statusId);

	public List<Status> index(String username);

	public Status show(String username, int statusId);

	public Status create(String username, Status status);

	public Status update(String username, int statusId, Status status);

	public boolean destroy(String username, int statusId);

}
