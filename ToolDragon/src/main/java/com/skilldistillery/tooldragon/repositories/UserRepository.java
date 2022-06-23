package com.skilldistillery.tooldragon.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tooldragon.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Set<User> findByUsername_Username(String username);

	User findByIdAndUsername_Username(int accountId, String username);

	User findByUsername(String username);

}
