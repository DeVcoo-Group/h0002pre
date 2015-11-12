package com.devcoo.agencyflight.core.util;

import org.apache.commons.lang3.StringUtils;

import com.devcoo.agencyflight.core.std.StdEntity;

public class CodeGenerator {
	public static final int TYPE_INVOICE = 1;
	public static final int TYPE_CUSTOMER = 2;
	public static final int TYPE_RECEIPT = 3;
	public static final int TYPE_VISA_PASSPORT = 5;
	
	public static final String V_TYPE_INVOICE = "INV";
	public static final String V_TYPE_CUSTOMER = "CUS";
	public static final String V_TYPE_RECEIPT = "REC";
	public static final String V_TYPE_VISA_PASSPORT = "VIS";
	
	public static String getGenerateCode(Integer type, StdEntity entity) {
		String code = "";
		switch (type) {
			case TYPE_INVOICE: code += V_TYPE_INVOICE; break;
			case TYPE_CUSTOMER: code += V_TYPE_CUSTOMER; break;
			case TYPE_RECEIPT: code += V_TYPE_RECEIPT; break;
			case TYPE_VISA_PASSPORT: code += V_TYPE_VISA_PASSPORT; break;
			default: break;
		}
		String id = String.valueOf(entity.getId());
		code += StringUtils.leftPad(id, 5, "0");
		return code;
	}
}
