package com.hungle.doan.dao;

import java.util.List;

import com.hungle.doan.model.Blog;

public interface BlogDAO {
	
	/**
	 * Get all blog
	 */
	List<Blog> list();
	
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
	void update(long id, Blog blog);
	
	/**
	 * Delete a blog
	 */
	void delete(long id);
	
	/**
	 * Get blog by idCategory
	 */
	String getBlog(String idCategory);

}
