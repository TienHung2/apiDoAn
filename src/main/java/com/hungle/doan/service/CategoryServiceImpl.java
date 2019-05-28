package com.hungle.doan.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hungle.doan.dao.CategoryDAO;
import com.hungle.doan.model.Category;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDAO categoryDao;
	
	public List<Category> list() {
		return categoryDao.list();
	}

	public void save(Category category) {
		categoryDao.save(category);
	}

	public Category get(long id) {
		return categoryDao.get(id);
	}

	public void update(long id,Category category) {
		categoryDao.update(id,category);
	}

	public void delete(long id) {
		categoryDao.delete(id);
	}

}
