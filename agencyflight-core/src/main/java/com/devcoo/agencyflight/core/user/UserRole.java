package com.devcoo.agencyflight.core.user;

import com.devcoo.agencyflight.core.std.StdEnum;

public enum UserRole implements StdEnum{
	ADMINISTRATOR(0,"Administrator"),
	MANAGER(1,"Manager"),
	Staff(2,"Staff");
	private int id;
    private String code;

    public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	private UserRole(int id, String code) {
    	this.id = id;
        this.code = code;
    }
}
