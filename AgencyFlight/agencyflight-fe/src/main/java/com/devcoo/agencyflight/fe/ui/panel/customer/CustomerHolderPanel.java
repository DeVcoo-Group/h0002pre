package com.devcoo.agencyflight.fe.ui.panel.customer;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.customer.CustomerService;
import com.devcoo.agencyflight.core.ui.layout.AbstractTabsheet;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(CustomerHolderPanel.NAME)
public class CustomerHolderPanel extends AbstractTabsheet<CustomerService, Customer> {

	private static final long serialVersionUID = 8980059196093846490L;
	public static final String NAME = "fe.customer";
	
	private CustomerFormPanel formPanel = new CustomerFormPanel();

	@Override
	protected CustomerTablePanel getListLayout() {
		return new CustomerTablePanel();
	}

	@Override
	protected void addNewEntity() {
		formPanel.setCaption("New Customer");
		formPanel.assignValues(null);
		addFormLayout(formPanel);
	}

	@Override
	protected void editEntity(Integer entityId) {
		formPanel.setCaption("Edit Customer");
		formPanel.assignValues(entityId);
		addFormLayout(formPanel);
	}

}
