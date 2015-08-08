package com.devcoo.agencyflight.fe.ui.panel.customer;

import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.customer.CustomerService;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.ui.layout.customer.BaseCustomerFormPanel;
import com.vaadin.ui.Component;

public class CustomerFormPanel extends AbstractFormLayout<CustomerService, Customer> {

	private static final long serialVersionUID = -7906468708276408546L;
	
	private BaseCustomerFormPanel customerForm;

	public CustomerFormPanel() {
		super("customerServiceImp");
	}

	@Override
	protected void save() {
		service.save(customerForm.getCustomerInfo(entity));
	}

	@Override
	protected Component initGUI() {
		customerForm = new BaseCustomerFormPanel((CountryService) ctx.getBean("countryServiceImp"));
		return customerForm;
	}
	
	@Override
	protected void assignValues(Integer entityId) {
		reset();
		if (entityId == null) {
			entity = new Customer();
		} else {
			entity = service.find(entityId);
			customerForm.assignValues(entity);
		}
	}

	@Override
	protected void reset() {
		customerForm.reset();
	}

	@Override
	protected boolean validate() {
		return customerForm.validate();
	}

}
