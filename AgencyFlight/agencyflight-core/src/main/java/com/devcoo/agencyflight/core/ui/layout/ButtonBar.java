package com.devcoo.agencyflight.core.ui.layout;

import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class ButtonBar extends HorizontalLayout {

	private static final long serialVersionUID = 3686260973812736441L;
	
	public ButtonBar() {
		setSpacing(true);
	}
	
	public static ButtonBar buildDefaultCrudBar() {
		ButtonBar buttonBar = new ButtonBar();
		Button btnNew = VaadinFactory.getButtonDanger("New");
		btnNew.setIcon(FontAwesome.PLUS);
		buttonBar.addComponent(btnNew);
		buttonBar.addComponent(VaadinFactory.getButton("Edit"));
		buttonBar.addComponent(VaadinFactory.getButton("Delete"));
		return buttonBar;
	}

}
