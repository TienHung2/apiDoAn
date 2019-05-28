package com.hungle.doan.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hungle.doan.dao.BlogDAO;
import com.hungle.doan.model.Blog;

@Transactional
@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogDAO blogDao;
	
	public void save(Blog blog) {
		blogDao.save(blog);
	}

	public Blog get(long id) {
		return blogDao.get(id);
	}

	public void update(long id,Blog blog) {
		blogDao.update(id, blog);
	}

	public void delete(long id) {
		blogDao.delete(id);
	}
	
	public List<Blog> list(){
		return blogDao.list();
	}

	public String getBlog(String idCategory) {
		return blogDao.getBlog(idCategory);
	}

}
