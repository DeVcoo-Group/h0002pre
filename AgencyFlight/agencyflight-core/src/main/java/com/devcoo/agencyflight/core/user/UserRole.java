package com.devcoo.agencyflight.core.user;

import com.devcoo.agencyflight.core.std.StdField;

public enum UserRole implements StdField{
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

	@Override
	public String getDisplayName() {
		return getCode();
	}
}
