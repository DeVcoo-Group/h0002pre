package com.devcoo.agencyflight.core.user;

import org.springframework.data.repository.Repository;


public interface UserDAO extends Repository<User,Integer> {
	User getAuthoriseUser(String name,String password);
}
