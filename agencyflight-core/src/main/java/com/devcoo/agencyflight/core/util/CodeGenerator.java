package com.devcoo.agencyflight.core.util;

import org.apache.commons.lang3.StringUtils;

import com.devcoo.agencyflight.core.std.StdEntity;

public class CodeGenerator {
	public static Integer TYPE_INVOICE = 1;
	
	public static String V_TYPE_INVOICE = "I";
	public static String getGenerateCode(Integer type, StdEntity entity) {
		String code = "";
		if(type == TYPE_INVOICE) {
			code += V_TYPE_INVOICE;
		}
		String id = String.valueOf(entity.getId());
		code += StringUtils.leftPad(id, 5, "0");
		return code;
	}
}
