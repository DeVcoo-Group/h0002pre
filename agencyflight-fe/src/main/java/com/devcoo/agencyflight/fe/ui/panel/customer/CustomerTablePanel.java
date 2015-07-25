package com.devcoo.agencyflight.fe.ui.panel.customer;

import com.devcoo.agencyflight.core.ui.layout.customer.BaseCustomerTablePanel;

public class CustomerTablePanel extends BaseCustomerTablePanel {

	private static final long serialVersionUID = -8404816217718857984L;

	public CustomerTablePanel() {
		super("customerServiceImp");
	}

	@Override
	protected void initGUI() {
		setCaption("Customers");
		buildDefaultCRUDBar();
		table.setCaption("List Customers");
	}

	@Override
	public CustomerSearchPanel buildSearchPanel() {
		return new CustomerSearchPanel();
	}

}
