package com.devcoo.agencyflight.fe.ui.menu;

import com.devcoo.agencyflight.core.std.ApplicationContext;
import com.devcoo.agencyflight.core.ui.menu.AbstractMenuBar;
import com.devcoo.agencyflight.fe.ui.panel.country.CountryHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.customer.CustomerHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.dashboard.Dashboard;
import com.devcoo.agencyflight.fe.ui.panel.invoice.InvoiceHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.product.ProductHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.product.visa.period.PeriodHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.product.visa.type.VisaTypeHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.supplier.SupplierHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.user.UserHolderPanel;

public class FEMenu extends AbstractMenuBar {
	
	private static final long serialVersionUID = -4241661350709674394L;

	protected void buildMenu() {
		addItem("Dashboard", new MenuCommand(Dashboard.NAME));
		
		MenuItem customers = addItem("Customers", null);
		customers.addItem("Customer", new MenuCommand(CustomerHolderPanel.NAME));
		customers.addItem("Invoice", new MenuCommand(InvoiceHolderPanel.NAME));
		
		MenuItem products = addItem("Products", null);
		products.addItem("Product", new MenuCommand(ProductHolderPanel.NAME));
		products.addItem("Visa Period", new MenuCommand(PeriodHolderPanel.NAME));
		products.addItem("Visa type", new MenuCommand(VisaTypeHolderPanel.NAME));
		
		addItem("Payment", null);
		addItem("Suppliers", new MenuCommand(SupplierHolderPanel.NAME));
		
		MenuItem users = addItem("User", null);
		users.addItem("Users", new MenuCommand(UserHolderPanel.NAME));
		users.addItem("Log out", new LogOutCommand());
		
		addItem("Report", null);
		MenuItem settings = addItem("Setting", null);
		settings.addItem("Country", new MenuCommand(CountryHolderPanel.NAME));
		addItem("About", null);
	}
	
	private class LogOutCommand implements Command {

		private static final long serialVersionUID = 8626372551321503976L;

		@Override
		public void menuSelected(MenuItem selectedItem) {
			ApplicationContext.logOut();
		}
		
	}

}
