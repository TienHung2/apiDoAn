package com.hungle.doan.service;

import java.util.List;

import com.hungle.doan.model.Blog;

public interface BlogService {

	/**
	 * Save new blog
	 */
	void save(Blog blog);
	
	/**
	 * Get blog by id
	 */
	Blog get(long id);
	
	/**
	 * Update blog
	 */
	void update(long id,Blog blog);
	
	/**
	 * Delete a blog
	 */
	void delete(long id);
	
	/**
	 * Get all blog
	 */
	List<Blog> list();
	
	String getBlog(String idCategory);

}
