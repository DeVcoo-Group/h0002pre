package com.devcoo.agencyflight.fe.ui.panel.invoice;

import java.util.Iterator;

import com.devcoo.agencyflight.core.invoice.visa.InvoiceVisa;
import com.devcoo.agencyflight.core.invoice.visa.InvoiceVisaService;
import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class InvoiceSearchPanel extends AbstractSearchLayout<InvoiceVisaService, InvoiceVisa> {

	private static final long serialVersionUID = -3058329587541361141L;
	
	private TextField txtCode;
	private TextField txtCustomerFirstName;
	private TextField txtCustomerLastName;
	private TextField txtEmployee;

	public InvoiceSearchPanel() {
		super("invoiceVisaServiceImp");
	}

	@Override
	protected Component initGUI() {
		initControls();
		
		HorizontalLayout content = new HorizontalLayout();
		content.setSpacing(true);
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(txtCode);
		formLayout.addComponent(txtCustomerFirstName);
		content.addComponent(formLayout);
		
		formLayout = new FormLayout();
		formLayout.addComponent(txtEmployee);
		formLayout.addComponent(txtCustomerLastName);
		content.addComponent(formLayout);
		
		return content;
	}
	
	private void initControls() {
		txtCode = VaadinFactory.getTextField("Invoice Code", 200);
		txtCustomerFirstName = VaadinFactory.getTextField("Customer first name", 200);
		txtCustomerLastName = VaadinFactory.getTextField("Customer last name", 200);
		txtEmployee = VaadinFactory.getTextField("Employee", 200);
	}

	@Override
	public Iterator<InvoiceVisa> getRestrictions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		txtCode.setValue("");
		txtCustomerFirstName.setValue("");
		txtCustomerLastName.setValue("");
		txtEmployee.setValue("");
	}

}
