package com.devcoo.agencyflight.fe.ui.panel.invoice.customer;

import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.customer.CustomerService;
import com.devcoo.agencyflight.core.invoice.InvoiceVisa;
import com.devcoo.agencyflight.core.invoice.InvoiceVisaService;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.ui.layout.ButtonBar;
import com.devcoo.agencyflight.core.ui.layout.customer.BaseCustomerFormPanel;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.devcoo.agencyflight.fe.ui.panel.invoice.InvoiceHolderPanel;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

public class InvoiceCustomerFormPanel extends AbstractFormLayout<InvoiceVisaService, InvoiceVisa> {
	
	private static final long serialVersionUID = 8713127304847061804L;
	private CustomerService customerService = (CustomerService) ctx.getBean("customerServiceImp");
	
	private CheckBox chkOldCustomer;
	private BaseCustomerFormPanel customerForm;
	private InvoiceCustomerTablePanel tablePanel;
	private VerticalLayout content;
	
	public InvoiceCustomerFormPanel() {
		super("invoiceVisaServiceImp");
	}

	@Override
	protected void save() {
		Integer customerId = -1;
		if (chkOldCustomer.getValue()) {
			customerId = tablePanel.getSelectedItemId();
		} else {
			Customer customer = new Customer();
			customerService.save((customerForm.getCustomerInfo(customer)));
			customerId = customer.getId();
		}
		((InvoiceHolderPanel) getMainPanel()).addInvoiceTab(customerId);
	}

	@Override
	protected Component initGUI() {
		initControls();
		content = new VerticalLayout();
		content.setSpacing(true);
		
		content.addComponent(chkOldCustomer);
		content.addComponent(customerForm);
		
		return content;
	}
	
	@Override
	protected void buildDefaultCRUDBar() {
		ButtonBar crudBar = new ButtonBar();
		Button btn = VaadinFactory.getButtonDanger("Create Invoice");
		btn.setIcon(FontAwesome.FILE);
		btn.addClickListener(this);
		crudBar.addButton(btn);
		addComponent(crudBar, 0);
	}
	
	private void initControls() {
		chkOldCustomer = new CheckBox("Old customer");
		chkOldCustomer.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 6315624784940877383L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (chkOldCustomer.getValue()) {
					content.removeComponent(customerForm);
					content.addComponent(tablePanel);
				} else {
					content.removeComponent(tablePanel);
					content.addComponent(customerForm);
				}
			}
		});
		customerForm = new BaseCustomerFormPanel();
		tablePanel = new InvoiceCustomerTablePanel();
	}

	@Override
	public void assignValues(Integer entityId) {
		reset();
	}

	@Override
	public void reset() {
		customerForm.reset();
		tablePanel.refresh();
		chkOldCustomer.setValue(false);
	}

	@Override
	protected boolean validate() {
		boolean valid = true;
		if (chkOldCustomer.getValue()) {
			if (tablePanel.getSelectedItemId() == null) {
				valid = false;
				String msg = "To create invoice, please select one customer or add new customer.";
				Notification info = VaadinFactory.getNotification("Information", msg, Type.HUMANIZED_MESSAGE);
				info.show(Page.getCurrent());
			}
		} else {
			valid = customerForm.validate();
		}
		return valid;
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (validate()) {
			save();
		}
	}
	
	@Override
	public InvoiceVisa getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
