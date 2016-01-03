package com.devcoo.agencyflight.core.payment;

import com.devcoo.agencyflight.core.std.StdField;

public enum PaymentStatus implements StdField {
	PAID(1, "Paid"),
	CANCEL(2, "Cancel");
	
	private int id;
    private String code;
	
	private PaymentStatus(int id, String code) {
		this.id = id;
		this.code = code;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getDisplayName() {
		if(getCode() != null) {
			return getCode();
		}
		return "";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
