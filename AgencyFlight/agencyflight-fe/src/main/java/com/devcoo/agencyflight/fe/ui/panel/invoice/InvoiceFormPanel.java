package com.devcoo.agencyflight.fe.ui.panel.invoice;

import java.text.DecimalFormat;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.invoice.InvoiceService;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class InvoiceFormPanel extends AbstractFormLayout<InvoiceService, Invoice> {

	private static final long serialVersionUID = 7155913963716727055L;
	
	private TextField txtCode;
	private TextField txtCustomerFirstName;
	private TextField txtCustomerLastName;
	private TextField txtEmployee;
	private TextField txtAmountReceive;
	
	private Invoice invoice;

	public InvoiceFormPanel() {
		super("invoiceServiceImp");
	}

	@Override
	protected void save() {
		
	}

	@Override
	protected Component initGUI() {
		initControls();
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(VaadinFactory.getLabel("Customer Invoice Detail"));
		formLayout.addComponent(txtCode);
		formLayout.addComponent(txtAmountReceive);
		formLayout.addComponent(txtCustomerFirstName);
		formLayout.addComponent(txtCustomerLastName);
		formLayout.addComponent(txtEmployee);
		
		return formLayout;
	}
	
	private void initControls() {
		txtCode = VaadinFactory.getTextField("Invoice Code", 200);
		txtCustomerFirstName = VaadinFactory.getTextField("Customer first name", 200);
		txtCustomerLastName = VaadinFactory.getTextField("Customer last name", 200);
		txtEmployee = VaadinFactory.getTextField("Employee", 200);
		txtAmountReceive = VaadinFactory.getTextField("Amount receive", 200);
	}

	@Override
	protected void assignValues(Integer entityId) {
		reset();
		if (entityId == null) {
			invoice = new Invoice();
		} else {
			invoice = service.find(entityId);
			txtCode.setValue(invoice.getCode());
			txtCustomerFirstName.setValue(invoice.getCustomer().getFirstName());
			txtCustomerLastName.setValue(invoice.getCustomer().getLastName());
			txtEmployee.setValue(invoice.getEmployee().getName());
			DecimalFormat df = new DecimalFormat("#0.00");
			txtAmountReceive.setValue(df.format(invoice.getAmountReceive()));
		}
	}

	@Override
	protected void reset() {
		txtCode.setValue("");
		txtCustomerFirstName.setValue("");
		txtCustomerLastName.setValue("");
		txtEmployee.setValue("");
		txtAmountReceive.setValue("");
	}

	@Override
	protected boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Invoice getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
