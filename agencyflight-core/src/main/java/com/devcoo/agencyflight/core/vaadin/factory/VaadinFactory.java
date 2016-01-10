package com.devcoo.agencyflight.core.vaadin.factory;

import java.util.List;

import com.devcoo.agencyflight.core.std.StdField;
import com.devcoo.agencyflight.core.ui.field.selelct.ComboBox;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
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
		return getTextField(caption, 200, false);
	}
	
	public static TextField getTextField(String caption, float width) {
		return getTextField(caption, width, false);
	}
	
	public static TextField getTextField(String caption, boolean required) {
		return getTextField(caption, 200, required);
	}
	
	public static TextField getTextField(String caption, float width, boolean required) {
		TextField txt = new TextField(caption);
		txt.setWidth(width, Unit.PIXELS);
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
	
	public static <T extends StdField> ComboBox<T> getComboBox(List<T> values) {
		return getComboBox("", values);
	}
	
	public static <T extends StdField> ComboBox<T> getComboBox(String caption, List<T> values) {
		return getComboBox(caption, values, false);
	}
	
	public static <T extends StdField> ComboBox<T> getComboBox(String caption, List<T> values, boolean required) {
		ComboBox<T> cbo = new ComboBox<T>(caption, values);
		cbo.setWidth(200, Unit.PIXELS);
		cbo.setRequired(required);
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
	
	public static Button getButtonPrinter(Class<? extends UI> uiPrinter) {
		Button btnPrint = VaadinFactory.getButton("Print");
		BrowserWindowOpener opener;
		opener = new BrowserWindowOpener(uiPrinter);
		opener.setFeatures("height=800,width=600,resizable");
		opener.extend(btnPrint);
		return btnPrint;
		
	}
	
}
