package com.devcoo.agencyflight.core.vaadin.factory;

import java.util.List;

import com.devcoo.agencyflight.core.std.StdField;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class VaadinFactory {

	private VaadinFactory() {
	}
	
	// ============== Label ================== //
	public static Label getLabel(String caption) {
		return new Label(caption);
	}
	
	public static Label getLabelHtml(String label) {
		return new Label(label, ContentMode.HTML);
	}
	
	// ============== Button ================== //
	public static Button getButton(String caption) {
		Button btn = new Button(caption);
		btn.setStyleName(ValoTheme.BUTTON_SMALL);
		return btn;
	}
	
	public static Button getButton(String caption, String image) {
		Button btn = getButton(caption);
		btn.setIcon(new ThemeResource(image));
		return btn;
	}
	
	public static Button getButtonPrimary(String caption) {
		Button btn = getButton(caption);
		btn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		return btn;
	}
	
	public static Button getButtonDanger(String caption) {
		Button btn = getButton(caption);
		btn.addStyleName(ValoTheme.BUTTON_DANGER);
		return btn;
	}
	
	public static Button getButtonFriendly(String caption) {
		Button btn = getButton(caption);
		btn.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		return btn;
	}
	
	// ============== Textfield ================== //
	public static TextField getTextField(String caption) {
		return new TextField(caption);
	}
	
	public static TextField getTextField(String caption, float width) {
		TextField txt = getTextField(caption);
		txt.setWidth(width, Unit.PIXELS);
		return txt;
	}
	
	public static TextField getTextField(String caption, float width, boolean required) {
		TextField txt = getTextField(caption, width);
		txt.setRequired(required);
		return txt;
	}
	
	// ============== Passwordfield ================== //
	public static PasswordField getPasswordField(String caption) {
		return new PasswordField(caption);
	}
	
	public static PasswordField getPasswordField(String caption, float width) {
		PasswordField txt = getPasswordField(caption);
		txt.setWidth(width, Unit.PIXELS);
		return txt;
	}
	
	public static PasswordField getPasswordField(String caption, float width, boolean required) {
		PasswordField txt = getPasswordField(caption, width);
		txt.setRequired(required);
		return txt;
	}
	
	// ============== ComboBox ================== //
	public static ComboBox getComboBoxFromEnum(String caption, float width, boolean required, StdField[] fields) {
		ComboBox cbo = new ComboBox(caption);
		for (StdField typeEnum : fields) {
			cbo.addItem(typeEnum.getId());
			cbo.setItemCaption(typeEnum.getId(), typeEnum.getDisplayName());
		}
		cbo.setRequired(required);
		cbo.setWidth(width, Unit.PIXELS);
		return cbo;
	}
	
	public static <T extends StdField> ComboBox getComboBox(String caption, float width, boolean required, List<T> fields) {
		ComboBox cbo = new ComboBox(caption);
		for (StdField typeEnum : fields) {
			cbo.addItem(typeEnum.getId());
			cbo.setItemCaption(typeEnum.getId(), typeEnum.getDisplayName());
		}
		cbo.setRequired(required);
		cbo.setWidth(width, Unit.PIXELS);
		return cbo;
	}
	
	// ============== TextArea ================== //
	public static TextArea getTextArea(String caption, float width, float height, boolean required) {
		TextArea ta = new TextArea(caption);
		ta.setWidth(width, Unit.PIXELS);
		ta.setHeight(height, Unit.PIXELS);
		ta.setRequired(required);
		return ta;
	}
	
	// ============== TextArea ================== //
	public static Notification getNotification(String title, String msg) {
		return getNotification(title, msg, Type.HUMANIZED_MESSAGE, 2500);
	}
	
	public static Notification getNotification(String title, String msg, Type type) {
		return getNotification(title, msg, type, 2500);
	}
	
	public static Notification getNotification(String title, String msg, Type type, int delay) {
		Notification info = new Notification(title, msg, type);
		info.setDelayMsec(delay);
		return info;
	}
	
}
