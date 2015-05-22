package com.devcoo.agencyflight.core.user;

public enum UserRole {
	ADMINISTRATO(0,"Administrator"),
	MANAGER(1,"Manager"),
	Staff(2,"Staff");
	private int id;
    private String code;

    private UserRole(int id, String code) {
    	this.id = id;
        this.code = code;
    }
}
