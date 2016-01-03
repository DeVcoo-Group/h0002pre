package com.devcoo.agencyflight.fe.ui.panel.invoice;

import java.util.Iterator;

import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.customer.CustomerService;
import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.invoice.InvoiceService;
import com.devcoo.agencyflight.core.invoice.InvoiceSpecification;
import com.devcoo.agencyflight.core.std.ApplicationContext;
import com.devcoo.agencyflight.core.ui.field.selelct.ComboBox;
import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.user.UserService;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class InvoiceSearchPanel extends AbstractSearchLayout<InvoiceService, Invoice> {

	private static final long serialVersionUID = -3058329587541361141L;
	
	private TextField txtCode;
	private ComboBox<User> cbEmployee;
	private ComboBox<Customer> cbCustomer;
	
	private InvoiceSpecification specification = new InvoiceSpecification();

	public InvoiceSearchPanel() {
		super("invoiceServiceImp");
	}
	
	private void initControls() {
		UserService userService = (UserService) ApplicationContext.getContext().getBean("userServiceImp");
		CustomerService customerService = (CustomerService) ApplicationContext.getContext().getBean("customerServiceImp");
		txtCode = VaadinFactory.getTextField("Invoice Code", 200);
		cbEmployee = VaadinFactory.getComboBox("Employee", userService.findAllNotDelete());
		cbCustomer = VaadinFactory.getComboBox("Customer", customerService.findAllNotDelete());
	}

	@Override
	protected Component initGUI() {
		initControls();
		
		HorizontalLayout content = new HorizontalLayout();
		content.setSpacing(true);
		content.setMargin(true);
		
		content.addComponent(txtCode);
		content.addComponent(cbEmployee);
		content.addComponent(cbCustomer);
		
		return content;
	}
	
	@Override
	public Iterator<Invoice> getRestrictions() {
		specification.setCode(txtCode.getValue());
		specification.setEmployee(cbEmployee.getEntity());
		specification.setCustomer(cbCustomer.getEntity());
		return service.findAll(specification).iterator();
	}

	@Override
	public void reset() {
		txtCode.setValue("");
		cbEmployee.setEntity(null);
		cbCustomer.setEntity(null);
	}

}
