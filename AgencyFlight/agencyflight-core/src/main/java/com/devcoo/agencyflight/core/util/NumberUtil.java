package com.devcoo.agencyflight.core.util;

import java.text.DecimalFormat;

import com.vaadin.ui.AbstractTextField;

public class NumberUtil {
	
	public static Double getDouble(AbstractTextField field) {
		if (field == null || field.getValue() == null || field.getValue().isEmpty()) {
			return null;
		} else {
			return Double.valueOf(field.getValue());
		}
	}
	
	public static Integer getInteger(AbstractTextField field) {
		if (field == null || field.getValue() == null || field.getValue().isEmpty()) {
			return null;
		} else {
			return Integer.valueOf(field.getValue());
		}
	}
	
	public static String formatCurrency(Double value) {
		return formatCurrency(value, "#0.00");
	}
	
	public static String formatCurrency(Double value, String format) {
		if (value == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat(format);
		return df.format(value);
	}

}
