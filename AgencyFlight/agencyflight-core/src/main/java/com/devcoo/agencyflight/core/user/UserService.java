package com.devcoo.agencyflight.core.user;

import java.util.List;

import com.devcoo.agencyflight.core.std.StdService;

public interface UserService extends StdService<User> {
	List<User> findUser(String name,String password);
	List<User> getAll();
}
