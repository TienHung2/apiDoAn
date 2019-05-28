package com.hungle.doan.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hungle.doan.model.Category;

@Repository
public class CategoryImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Category> list() {
		return sessionFactory.getCurrentSession().createQuery("from Category").list();
	}

	public void save(Category category) {
		sessionFactory.getCurrentSession().save(category);
	}

	public Category get(long id) {
		return sessionFactory.getCurrentSession().get(Category.class, id);
	}

	public void update(long id, Category category) {
		Session session = sessionFactory.getCurrentSession();
		Category oldCategory = session.byId(Category.class).load(id);
		oldCategory.setCategoryName(category.getCategoryName());
		session.flush();
	}

	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		Category category = session.byId(Category.class).load(id);
		session.delete(category);
	}

}
