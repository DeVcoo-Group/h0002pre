package com.devcoo.agencyflight.fe.ui.panel.invoice.customer;

import com.devcoo.agencyflight.core.ui.layout.customer.BaseCustomerTablePanel;
import com.devcoo.agencyflight.fe.ui.panel.customer.CustomerSearchPanel;

public class InvoiceCustomerTablePanel extends BaseCustomerTablePanel {

	private static final long serialVersionUID = 2249854319142654924L;
	
	public InvoiceCustomerTablePanel() {
		super("customerServiceImp");
	}

	@Override
	protected void initGUI() {
		table.setCaption("List Customers");
		refresh();
	}

	@Override
	protected CustomerSearchPanel buildSearchPanel() {
		return new CustomerSearchPanel();
	}
	
}
