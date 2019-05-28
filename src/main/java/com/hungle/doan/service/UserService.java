package com.hungle.doan.service;

import java.util.List;

import com.hungle.doan.model.User;

public interface UserService {
	/**
	 * Save new blog
	 */
	void save(User user);
	
	/**
	 * Get blog by id
	 */
	User get(long id);
	
	/**
	 * Update blog
	 */
	void update(long id,User user);
	
	/**
	 * Delete a blog
	 */
	void delete(long id);
	
	/**
	 * Get all blog
	 */
	List<User> list();
	
	String login(String userName, String password);
}
