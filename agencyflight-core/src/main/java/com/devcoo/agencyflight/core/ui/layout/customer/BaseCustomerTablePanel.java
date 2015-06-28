package com.devcoo.agencyflight.core.ui.layout.customer;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.customer.CustomerService;
import com.devcoo.agencyflight.core.customer.Nationality;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.devcoo.agencyflight.core.util.Tools;
import com.vaadin.data.Item;
import com.vaadin.ui.Table.Align;

public abstract class BaseCustomerTablePanel extends AbstractListLayout<CustomerService, Customer> {

	private static final long serialVersionUID = 3794312002070557345L;

	private static final String ID = "id";
	private static final String CODE = "code";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String NATIONALITY = "nationality";
	
	public BaseCustomerTablePanel(String serviceName) {
		super(serviceName);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void renderRow(Item item, Customer entity) {
		item.getItemProperty(ID).setValue(entity.getId());
		item.getItemProperty(CODE).setValue(entity.getCode());
		item.getItemProperty(FIRST_NAME).setValue(entity.getFirstName());
		item.getItemProperty(LAST_NAME).setValue(entity.getLastName());
		item.getItemProperty(NATIONALITY).setValue(Tools.getEnumToString(entity.getNationality(), Nationality.values()));
	}
	
	@Override
	protected List<Column> buildColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(ID, "Id", Integer.class, Align.RIGHT, 100));
		columns.add(new Column(CODE, "Customer Code", String.class, Align.LEFT));
		columns.add(new Column(FIRST_NAME, "First name", String.class, Align.LEFT));
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
