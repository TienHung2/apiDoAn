package com.hungle.doan.dao;

import java.util.List;

import com.hungle.doan.model.User;

public interface UserDAO {
	/**
	 * Get all user
	 */
	List<User> list();
	
	/**
	 * Save new user
	 */
	void save(User user);
	
	/**
	 * Get user by id
	 */
	User get(long id);
	
	/**
	 * Update user
	 */
	void update(long id,User user);
	
	/**
	 * Delete a user
	 */
	void delete(long id);
	
	String login(String userName, String password);
}
