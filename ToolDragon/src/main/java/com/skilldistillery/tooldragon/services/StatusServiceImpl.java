package com.skilldistillery.tooldragon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tooldragon.entities.Status;
import com.skilldistillery.tooldragon.repositories.StatusRepository;
import com.skilldistillery.tooldragon.repositories.UserRepository;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Status getUserById(int statusId) {
		Optional<Status> statusOpt = statusRepo.findById(statusId);
		Status status = null;
		if (statusOpt.isPresent()) {
			status = statusOpt.get();
		}
		return status;
	}

	@Override
	public List<Status> index(String username) {
		List<Status> statuses = null;
		if (userRepo.findByUsername(username) != null) {
			statuses = statusRepo.findAll();
		}
		return statuses;

	}

	@Override
	public Status show(String username, int statusId) {
		Status status = null;
		if (userRepo.findByUsername(username) != null) {
			Optional<Status> op = statusRepo.findById(statusId);
			if (op.isPresent()) {
				status = op.get();
			}
		}
		return status;
	}

	@Override
	public Status create(String username, Status status) {
		if (userRepo.findByUsername(username) != null) {
			if (status != null) {
				status = statusRepo.saveAndFlush(status);
			}
		}
		return status;
	}

	@Override
	public Status update(String username, int statusId, Status status) {
		Status managed = null;
		if (userRepo.findByUsername(username) != null) {
			if (status != null) {
				Optional<Status> op = statusRepo.findById(statusId);
				if (op.isPresent()) {
					managed = op.get();
					if (managed != null) {
						managed.setName(status.getName());
						statusRepo.saveAndFlush(managed);
					}
				}

			}

		}
		return managed;
	}

	@Override
	public boolean destroy(String username, int statusId) {
		boolean deleted = false;
		Status toDelete = null;
		if (userRepo.findByUsername(username) != null) {
			Optional<Status> op = statusRepo.findById(statusId);
			if (op.isPresent()) {
				toDelete = op.get();
				if (toDelete != null) {
					statusRepo.delete(toDelete);
					deleted = true;
				}
			}
		}
		return deleted;
	}

}
