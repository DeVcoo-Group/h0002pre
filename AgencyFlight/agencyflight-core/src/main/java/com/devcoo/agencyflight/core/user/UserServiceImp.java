package com.devcoo.agencyflight.core.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	@Resource
	UserDao userDao;
	@Transactional
	@Override
	public List<User> findUser(String name, String password) {	
		//return userDao.findByNameAndPassword(name, password);
		return userDao.findByNameAndPassword(name, password);
	}
	@Transactional
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
	@Transactional
	@Override
	public void insert(User user) {
		userDao.save(user);
	}

}
