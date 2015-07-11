package com.devcoo.agencyflight.core.util;

import com.devcoo.agencyflight.core.std.StdField;

public class Tools {
	public static StdField getEnum(Integer value, StdField[] enumerations) {
		if (value == null) return null;
		for (StdField typeEnum : enumerations) {
			if(value == typeEnum.getId()) {
				return typeEnum;
			}
		}
		return null;
	}
	public static String getEnumToString(Integer value, StdField[] enumerations) {
		StdField stdEnum = getEnum(value,enumerations);
		if (stdEnum == null) {
			return null;
		} else {
			return stdEnum.getDisplayName();
		}
	}
}
