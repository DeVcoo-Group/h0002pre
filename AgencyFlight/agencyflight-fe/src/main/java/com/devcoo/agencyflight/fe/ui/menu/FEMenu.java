package com.devcoo.agencyflight.fe.ui.menu;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;

public class FEMenu extends MenuBar {
	
	private static final long serialVersionUID = -4241661350709674394L;

	public FEMenu() {
		setStyleName(ValoTheme.MENUBAR_SMALL);
		MenuItem file = addItem("File", null);
		file.addItem("New", null);
		file.addItem("Open", null);
		file.addItem("Exit", null);
		
		MenuItem view = addItem("View", null);
		view.addItem("Edit", null);
		view.addItem("View", null);
	}

}
