package com.devcoo.agencyflight.core.util;

import com.vaadin.server.UserError;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractTextField;


public class ValidationUtil {
	
	private ValidationUtil() {}
	
	public static boolean validateRequiredTextField(AbstractTextField textField) {
		String fieldName = "Field";
		if (textField != null) {
			fieldName = textField.getCaption();
		}
		return validateRequiredTextField(textField, fieldName + " is required!");
	}
	
	public static boolean validateRequiredTextField(AbstractTextField textField, String errorMessage) {
		boolean valid = false;
		if (textField != null) {
			String value = textField.getValue();
			if (value != null && !"".equals(value.trim())) {
				valid = true;
			}
			if (!valid) {
				textField.setComponentError(new UserError(errorMessage));
			} else {
				textField.setComponentError(null);
			}
		}
		return valid;
	}
	
	public static boolean validateRequiredSelectField(AbstractSelect selectField) {
		String fieldName = "Selection";
		if (selectField != null) {
			fieldName = selectField.getCaption();
		}
		return validateRequiredSelectField(selectField, fieldName + " is required!");
	}
	
	public static boolean validateRequiredSelectField(AbstractSelect selectField, String errorMessage) {
		boolean valid = false;
		if (selectField != null) {
			Object value = selectField.getValue();
			if (value != null) {
				valid = true;
			}
			if (!valid) {
				selectField.setComponentError(new UserError(errorMessage));
			} else {
				selectField.setComponentError(null);
			}
		}
		return valid;
	}

}
