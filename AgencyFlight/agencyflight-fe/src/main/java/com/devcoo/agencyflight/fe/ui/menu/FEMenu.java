package com.devcoo.agencyflight.fe.ui.menu;

import com.devcoo.agencyflight.core.ui.menu.AbstractMenuBar;
import com.devcoo.agencyflight.fe.ui.panel.dashboard.Dashboard;
import com.devcoo.agencyflight.fe.ui.panel.login.LoginPanel;
import com.devcoo.agencyflight.fe.ui.panel.user.UserHolderPanel;

public class FEMenu extends AbstractMenuBar {
	
	private static final long serialVersionUID = -4241661350709674394L;

	protected void buildMenu() {
		MenuItem file = addItem("File", null);
		file.addItem("New", new MenuCommand(Dashboard.NAME));
		file.addItem("Open", new MenuCommand(LoginPanel.NAME));
		file.addItem("Exit", null);
		
		MenuItem view = addItem("View", null);
		view.addItem("Edit", null);
		view.addItem("View", null);
		
		addItem("User", new MenuCommand(UserHolderPanel.NAME));
		addItem("Payment", null);
		addItem("Tool", null);
		addItem("Report", null);
		addItem("Setting", null);
		addItem("About", null);
	}

}
