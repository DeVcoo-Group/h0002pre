package com.devcoo.agencyflight.core.util;

import com.devcoo.agencyflight.core.std.StdEnum;

public class Tools {
	public static StdEnum getEnum(Integer value, StdEnum[] enumerations) {
		if (value == null) return null;
		for (StdEnum typeEnum : enumerations) {
			if(value == typeEnum.getId()) {
				return typeEnum;
			}
		}
		return null;
	}
	public static String getEnumToString(Integer value, StdEnum[] enumerations) {
		StdEnum stdEnum = getEnum(value,enumerations);
		if (stdEnum == null) {
			return null;
		} else {
			return stdEnum.getCode();
		}
	}
}
