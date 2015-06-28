package com.devcoo.agencyflight.core.customer;

import com.devcoo.agencyflight.core.std.StdEnum;

public enum Nationality implements StdEnum {
	CAMBODIAN(1, "Cambodia"),
	CHINESE(2, "Chinese");
	
	private int id;
	private String code;
	
	private Nationality(int id, String code) {
		this.id = id;
		this.code = code;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getCode() {
		return this.code;
	}

}
