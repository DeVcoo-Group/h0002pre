package com.devcoo.agencyflight.fe.ui.menu;

import com.devcoo.agencyflight.core.context.WebContext;
import com.devcoo.agencyflight.core.ui.menu.AbstractMenuBar;
import com.devcoo.agencyflight.fe.ui.panel.country.CountryHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.customer.CustomerHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.dashboard.Dashboard;
import com.devcoo.agencyflight.fe.ui.panel.invoice.InvoiceHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.login.LoginPanel;
import com.devcoo.agencyflight.fe.ui.panel.product.ProductHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.product.visa.period.PeriodHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.product.visa.type.VisaTypeHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.supplier.SupplierHolderPanel;
import com.devcoo.agencyflight.fe.ui.panel.user.UserHolderPanel;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;

public class FEMenu extends AbstractMenuBar {
	
	private static final long serialVersionUID = -4241661350709674394L;

	protected void buildMenu() {
		MenuItem file = addItem("File", null);
		file.addItem("Dashboard", new MenuCommand(Dashboard.NAME));
		file.addItem("Login", new MenuCommand(LoginPanel.NAME));
		file.addItem("Exit",new Command() {
			private static final long serialVersionUID = -191961032486477315L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				UI.getCurrent().getSession().setAttribute("isLogin", false);
				((WebContext) UI.getCurrent().getSession().getAttribute(WebContext.WEB_CONTEXT)).setLog_user(null);
				Page.getCurrent().setUriFragment("!");
			}
		});
		
		MenuItem customers = addItem("Customers", null);
		customers.addItem("Customer", new MenuCommand(CustomerHolderPanel.NAME));
		customers.addItem("Invoice", new MenuCommand(InvoiceHolderPanel.NAME));
		
		MenuItem products = addItem("Products", null);
		products.addItem("Product", new MenuCommand(ProductHolderPanel.NAME));
		products.addItem("Visa Period", new MenuCommand(PeriodHolderPanel.NAME));
		products.addItem("Visa type", new MenuCommand(VisaTypeHolderPanel.NAME));
		
		MenuItem view = addItem("View", null);
		view.addItem("Edit", null);
		view.addItem("View", null);
		
		addItem("User", new MenuCommand(UserHolderPanel.NAME));
		addItem("Suppliers", new MenuCommand(SupplierHolderPanel.NAME));
		addItem("Payment", null);
		addItem("Tool", null);
		addItem("Report", null);
		MenuItem settings = addItem("Setting", null);
		settings.addItem("Country", new MenuCommand(CountryHolderPanel.NAME));
		addItem("About", null);
	}

}
