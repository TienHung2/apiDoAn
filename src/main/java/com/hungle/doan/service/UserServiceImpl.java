package com.hungle.doan.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hungle.doan.dao.UserDAO;
import com.hungle.doan.model.User;

@Transactional
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDao; 
	
	public void save(User user) {
		userDao.save(user);
	}

	public User get(long id) {
		return userDao.get(id);
	}

	public void update(long id,User user) {
		userDao.update(id,user);
	}

	public void delete(long id) {
		userDao.delete(id);
	}

	public List<User> list() {
		return userDao.list();
	}

	public String login(String userName, String password) {
		return userDao.login(userName, password);
	}

}
