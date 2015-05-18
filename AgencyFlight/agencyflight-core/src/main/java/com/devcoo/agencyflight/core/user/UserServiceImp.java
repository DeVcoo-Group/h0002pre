package com.devcoo.agencyflight.core.user;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImp implements UserService {

	@Autowired
	@Resource
	private UserDAO userDAO;

	@Transactional
	public User getAuthoriseUser(String name, String password) {
		// TODO Auto-generated method stub
		return userDAO.getAuthoriseUser(name, password);
	}

	
	
}
