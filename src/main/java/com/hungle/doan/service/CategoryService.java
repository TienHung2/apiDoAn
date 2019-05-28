package com.hungle.doan.service;

import java.util.List;

import com.hungle.doan.model.Category;

public interface CategoryService {

	List<Category> list();
	
	void save(Category category);
	
	Category get(long id);
	
	void update(long id,Category category);
	
	void delete(long id);
	
}
