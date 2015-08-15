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
	
	public static boolean validateConfirmPassword(AbstractTextField passwordField, AbstractTextField confirmField) {
		return validateConfirmPassword(passwordField, confirmField, "Password not match!");
	}
	
	public static boolean validateConfirmPassword(AbstractTextField passwordField, AbstractTextField confirmField, String errorMessage) {
		boolean valid = false;
		
		if (!validateRequiredTextField(passwordField)) return valid;
		if (!validateRequiredTextField(confirmField)) return valid;
		if (passwordField.getValue().equals(confirmField.getValue())) valid = true;
		
		if (valid) {
			confirmField.setComponentError(null);
		} else {
			confirmField.setComponentError(new UserError(errorMessage));
		}
		
		return valid;
	}
	
	public static boolean validateDoubleField(AbstractTextField textField) {
		String fieldName = "Field";
		if (textField != null) {
			fieldName = textField.getCaption();
		}
		return  validateDoubleField(textField, fieldName + " should be a decimal value!");
	}
	
	public static boolean validateDoubleField(AbstractTextField textField, String errorMessage) {
		boolean valid = false;
		if (textField != null) {
			String value = textField.getValue();
			if (value == null || value.isEmpty()) {
				textField.setComponentError(null);
				return true;
			}
			try {
				Double.valueOf(value);
				valid = true;
			} catch (NumberFormatException e) { }
			if (!valid) {
				textField.setComponentError(new UserError(errorMessage));
			} else {
				textField.setComponentError(null);
			}
		}
		return valid;
	}
	
	public static boolean validateIntegerField(AbstractTextField textField) {
		String fieldName = "Field";
		if (textField != null) {
			fieldName = textField.getCaption();
		}
		return  validateIntegerField(textField, fieldName + " should be an integer value!");
	}
	
	public static boolean validateIntegerField(AbstractTextField textField, String errorMessage) {
		boolean valid = false;
		if (textField != null) {
			String value = textField.getValue();
			if (value == null || value.isEmpty()) {
				textField.setComponentError(null);
				return true;
			}
			try {
				Integer.valueOf(value);
				valid = true;
			} catch (NumberFormatException e) { }
			if (!valid) {
				textField.setComponentError(new UserError(errorMessage));
			} else {
				textField.setComponentError(null);
			}
		}
		return valid;
	}

}
