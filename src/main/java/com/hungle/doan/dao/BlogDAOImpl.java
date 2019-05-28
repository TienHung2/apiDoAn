package com.hungle.doan.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hungle.doan.model.Blog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hungle.doan.dao.BlogDAO;

@Repository
public class BlogDAOImpl implements BlogDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Save new blog
	 */
	public void save(Blog blog) {
		sessionFactory.getCurrentSession().save(blog);
	}
	
	/**
	 * Get blog by id
	 */
	public Blog get(long id) {
		return sessionFactory.getCurrentSession().get(Blog.class, id);
	}
	
	/**
	 * Update blog
	 */
	public void update(long id,Blog blog) {
		Session session = sessionFactory.getCurrentSession();
		Blog oldBlog = session.byId(Blog.class).load(id);
		
		Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("dd MMM yyyy HH:mm:ss z");
		
		String date = (ft.format(dNow)).toString();
		blog.setDate(date);
		
		oldBlog.setTitle(blog.getTitle());
		oldBlog.setAuthor(blog.getAuthor());
		oldBlog.setCategory(blog.getCategory());
		oldBlog.setContent(blog.getContent());
		oldBlog.setImg(blog.getImg());
		oldBlog.setDate(blog.getDate());
		session.flush();
	}
	
	/**
	 * Delete a blog
	 */
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		Blog blog = session.byId(Blog.class).load(id);
		session.delete(blog);
	}

	/**
	 * Get all blog
	 */
	public List<Blog> list(){
		return sessionFactory.getCurrentSession().createQuery("from Blog").list();
	}

	public String getBlog(String idCategory) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> setData = new HashMap<String, Object>();
		String json = null;
		String sql = "from Blog where Category like :idCategory";
		Query selectQuery = sessionFactory.getCurrentSession()
				.createQuery(sql)
				.setParameter("idCategory", "%"+idCategory+"%");
		List<Blog> blogs = selectQuery.list();
		setData.put("blogs", blogs);
		try {
			json = mapper.writeValueAsString(setData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
