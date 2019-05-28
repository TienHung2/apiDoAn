package com.hungle.doan.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hungle.doan.model.User;
import com.hungle.doan.dao.UserDAO;

@Repository
public class UserDaoImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	public List<User> list() {
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public User get(long id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	public void update(long id, User user) {
		Session session = sessionFactory.getCurrentSession();
		User oldUser = session.byId(User.class).load(id);
		oldUser.setAge(user.getAge());
		oldUser.setBrithDay(user.getBrithDay());
		oldUser.setEmail(user.getEmail());
		oldUser.setName(user.getName());
		oldUser.setPassword(user.getPassword());
		oldUser.setUserName(user.getUserName());
		session.flush();
	}

	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.byId(User.class).load(id);
		session.delete(user);
	}

	public String login(String userName, String password) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> setData = new HashMap<String, Object>();
		String json = null;
		String sql = "from User where (userName like :userName and password like :password)";
		Query selectQuery = sessionFactory.getCurrentSession()
				.createQuery(sql)
				.setParameter("userName", "%"+userName+"%")
				.setParameter("password", "%"+password+"%");
		List<User> users = selectQuery.list();
		setData.put("users", users);
		try {
			json = mapper.writeValueAsString(setData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
