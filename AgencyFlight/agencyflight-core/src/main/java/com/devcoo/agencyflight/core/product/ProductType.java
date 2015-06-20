package com.devcoo.agencyflight.core.product;

import com.devcoo.agencyflight.core.std.StdEnum;


public enum ProductType implements StdEnum{
	AIRTICKET(1, "Air-Ticket"),
	PASSPORT_VISA(2, "Passport and Visa"),
	TOUR(3,"Tour"),
	BUS(4,"Bus");
	
	private int id;
	private String code;
	
	private ProductType(int id, String code) {
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
