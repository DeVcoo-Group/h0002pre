package com.devcoo.agencyflight.core.document;

import com.devcoo.agencyflight.core.std.StdEnum;

public enum DocumentType implements StdEnum {
	PASSPORT(1, "Passport");
	
	private int id;
	private String code;
	
	private DocumentType(int id, String code) {
		this.id = id;
		this.code = code;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public String getCode() {
		return this.code;
	}

}
