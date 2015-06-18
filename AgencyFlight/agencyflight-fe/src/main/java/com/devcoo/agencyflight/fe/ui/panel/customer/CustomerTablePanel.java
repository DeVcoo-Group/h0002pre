package com.devcoo.agencyflight.fe.ui.panel.customer;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.customer.CustomerService;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.vaadin.data.Item;
import com.vaadin.ui.Table.Align;

public class CustomerTablePanel extends AbstractListLayout<CustomerService, Customer> {

	private static final long serialVersionUID = -8404816217718857984L;
	
	private static final String ID = "id";
	private static final String CODE = "code";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String NATIONALITY = "nationality";

	public CustomerTablePanel() {
		super("customerServiceImp");
	}

	@Override
	protected void initGUI() {
		setCaption("Customers");
		buildDefaultCRUDBar();
		table.setCaption("List Customers");
		refresh();
	}

	@Override
	public CustomerSearchPanel buildSearchPanel() {
		return new CustomerSearchPanel();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void renderRow(Item item, Customer entity) {
		item.getItemProperty(ID).setValue(entity.getId());
		item.getItemProperty(CODE).setValue(entity.getCode());
		item.getItemProperty(FIRST_NAME).setValue(entity.getFirstName());
		item.getItemProperty(LAST_NAME).setValue(entity.getLastName());
		item.getItemProperty(NATIONALITY).setValue("");
	}

	@Override
	protected List<Column> buildColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(ID, "Id", Integer.class, Align.RIGHT, 100));
		columns.add(new Column(CODE, "Customer Code", String.class, Align.LEFT, 150));
		columns.add(new Column(FIRST_NAME, "First name", String.class, Align.LEFT, 150));
		columns.add(new Column(LAST_NAME, "Last name", String.class, Align.LEFT, 150));
		columns.add(new Column(NATIONALITY, "Nationality", String.class, Align.LEFT, 150));
		return columns;
	}

	@Override
	public Customer getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
