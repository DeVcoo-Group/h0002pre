package com.devcoo.agencyflight.core.document;

import com.devcoo.agencyflight.core.std.StdField;

public enum DocumentType implements StdField {
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

	public String getCode() {
		return this.code;
	}

	@Override
	public String getDisplayName() {
		return getCode();
	}

}
