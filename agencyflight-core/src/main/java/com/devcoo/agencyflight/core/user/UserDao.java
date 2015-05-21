package com.devcoo.agencyflight.core.user;

import java.util.List;

import com.devcoo.agencyflight.core.std.StdDao;
public interface UserDao extends StdDao<User> {
	List<User> findByNameAndPassword(String name,String password);
}
