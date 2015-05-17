package com.devcoo.agencyflight.fe.ui.menu;

import com.devcoo.agencyflight.core.ui.menu.AbstractMenuBar;
import com.devcoo.agencyflight.fe.ui.panel.dashboard.Dashboard;
import com.devcoo.agencyflight.fe.ui.panel.login.LoginPanel;

public class FEMenu extends AbstractMenuBar {
	
	private static final long serialVersionUID = -4241661350709674394L;

	protected void buildMenu() {
		MenuItem file = addItem("File", null);
		file.addItem("New", getCommand(Dashboard.NAME));
		file.addItem("Open", getCommand(LoginPanel.NAME));
		file.addItem("Exit", null);
		
		MenuItem view = addItem("View", null);
		view.addItem("Edit", null);
		view.addItem("View", null);
	}

}
