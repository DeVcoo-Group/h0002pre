package com.devcoo.agencyflight.core.invoice;

import com.devcoo.agencyflight.core.std.StdField;

public enum InvoiceStatus implements StdField {
	NEW(1, "New"),
	FULL_PAY(2, "Full pay"),
	CANCEL(3, "Cancel");
	
	private int id;
    private String code;
    
    private InvoiceStatus(int id, String code) {
    	this.id = id;
    	this.code = code;
    }

	@Override
	public int getId() {
		return this.id;
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
