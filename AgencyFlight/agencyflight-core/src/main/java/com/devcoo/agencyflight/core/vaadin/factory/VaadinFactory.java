package com.devcoo.agencyflight.core.vaadin.factory;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class VaadinFactory {

	private VaadinFactory() {
	}
	
	// ============== Label ================== //
	public static Label getLabel(String caption) {
		return new Label(caption);
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
}
