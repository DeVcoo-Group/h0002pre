package com.devcoo.agencyflight.fe.ui.menu;

import com.devcoo.agencyflight.core.ui.menu.AbstractMenuBar;
import com.devcoo.agencyflight.fe.ui.panel.customer.CustomerHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.dashboard.Dashboard;
import com.devcoo.agencyflight.fe.ui.panel.login.LoginPanel;
import com.devcoo.agencyflight.fe.ui.panel.product.ProductHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.user.UserHolderPanel;

public class FEMenu extends AbstractMenuBar {
	
	private static final long serialVersionUID = -4241661350709674394L;

	protected void buildMenu() {
		MenuItem file = addItem("File", null);
		file.addItem("Dashboard", new MenuCommand(Dashboard.NAME));
		file.addItem("Login", new MenuCommand(LoginPanel.NAME));
		file.addItem("Exit", null);
		
		MenuItem customers = addItem("Customers", null);
		customers.addItem("Customer", new MenuCommand(CustomerHolderPanel.NAME));
		
		MenuItem products = addItem("Products", null);
		products.addItem("Product", new MenuCommand(ProductHolderPanel.NAME));
		
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
