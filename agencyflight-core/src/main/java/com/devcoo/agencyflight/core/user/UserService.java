package com.devcoo.agencyflight.core.user;

import java.util.List;

public interface UserService {
	List<User> findUser(String name,String password);
	List<User> getAll();
	void insert(User user);
}
