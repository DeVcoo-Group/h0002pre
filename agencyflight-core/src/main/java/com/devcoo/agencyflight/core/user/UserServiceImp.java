package com.devcoo.agencyflight.core.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class UserServiceImp extends StdServiceImp<UserDao, User> implements UserService {
	@Transactional
	@Override
	public List<User> findUser(String name, String password) {
		return dao.findByNameAndPassword(name, password);
	}
	@Transactional
	public User find(Integer id) {
		return dao.findOne(id);
	}
}
