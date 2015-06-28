package com.devcoo.agencyflight.core.util;

import com.devcoo.agencyflight.core.std.StdEnum;

public class Tools {
	public static StdEnum getEnum(int value, StdEnum[] enumerations) {
		for (StdEnum typeEnum : enumerations) {
			if(value == typeEnum.getId()) {
				return typeEnum;
			}
		}
		return null;
	}
	public static String getEnumToString(int value, StdEnum[] enumerations) {
		return getEnum(value,enumerations).getCode();
	}
}
