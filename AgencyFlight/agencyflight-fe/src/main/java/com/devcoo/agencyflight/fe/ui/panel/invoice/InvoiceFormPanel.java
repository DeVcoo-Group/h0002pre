package com.devcoo.agencyflight.fe.ui.panel.invoice;

import java.text.DecimalFormat;
import java.util.Date;

import com.devcoo.agencyflight.core.context.WebContext;
import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.customer.CustomerService;
import com.devcoo.agencyflight.core.invoice.InvoiceVisa;
import com.devcoo.agencyflight.core.invoice.InvoiceVisaService;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.devcoo.agencyflight.fe.ui.panel.invoice.artical.InvoiceVisaArticleTablePanel;
import com.vaadin.server.Page;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class InvoiceFormPanel extends AbstractFormLayout<InvoiceVisaService, InvoiceVisa> {

	private static final long serialVersionUID = 7155913963716727055L;
	
	private TextField txtCode;
	private TextField txtCustomerFirstName;
	private TextField txtCustomerLastName;
	private TextField txtEmployee;
	private TextField txtAmountReceive;
	
	private InvoiceVisa invoiceVisa;
	private Integer customerId;
	private CustomerService customerService = (CustomerService) ctx.getBean("customerServiceImp");
	private InvoiceVisaArticleTablePanel articleTablePanel;

	public InvoiceFormPanel() {
		super("invoiceVisaServiceImp");
	}

	@Override
	protected void save() {
		invoiceVisa.setCode(txtCode.getValue());
		service.save(invoiceVisa);
	}

	@Override
	protected Component initGUI() {
		initControls();
		setEnabledControls(false);
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		verticalLayout.addComponent(buildInvoiceDetailPanel());
		verticalLayout.addComponent(articleTablePanel);
		
		return verticalLayout;
	}
	
	private void initControls() {
		txtCode = VaadinFactory.getTextField("Invoice Code", 200);
		txtCustomerFirstName = VaadinFactory.getTextField("Customer first name", 200);
		txtCustomerLastName = VaadinFactory.getTextField("Customer last name", 200);
		txtEmployee = VaadinFactory.getTextField("Employee", 200);
		txtAmountReceive = VaadinFactory.getTextField("Amount receive", 200);
		articleTablePanel = new InvoiceVisaArticleTablePanel();
	}
	
	private Panel buildInvoiceDetailPanel() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		horizontalLayout.setMargin(true);
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(txtCode);
		formLayout.addComponent(txtCustomerFirstName);
		horizontalLayout.addComponent(formLayout);
		
		formLayout = new FormLayout();
		formLayout.addComponent(txtEmployee);
		formLayout.addComponent(txtCustomerLastName);
		horizontalLayout.addComponent(formLayout);
		
		formLayout = new FormLayout();
		formLayout.addComponent(txtAmountReceive);
		horizontalLayout.addComponent(formLayout);
		
		Panel panel = new Panel("Customer Invoice Detail");
		panel.setContent(horizontalLayout);
		return panel;
	}

	@Override
	protected void assignValues(Integer entityId) {
		if (entityId == null) {
			invoiceVisa = new InvoiceVisa();
			if (this.customerId != null) {
				Customer customer = customerService.find(this.customerId);
				invoiceVisa.setCode(new Date() + "");
				invoiceVisa.setCustomer(customer);
				WebContext context = (WebContext) UI.getCurrent().getSession().getAttribute(WebContext.WEB_CONTEXT);
				User employee = context.getLog_user();
				invoiceVisa.setEmployee(employee);
				service.save(invoiceVisa);
				entityId = invoiceVisa.getId();
			} else {
				String msg = "To create invoice, a customer must be exist";
				Notification info = VaadinFactory.getNotification("Error", msg, Type.ERROR_MESSAGE);
				info.show(Page.getCurrent());
			}
		} else {
			invoiceVisa = service.find(entityId);
			txtCode.setValue(invoiceVisa.getCode());
			DecimalFormat df = new DecimalFormat("#0.00");
			Double amountReceive = invoiceVisa.getAmountReceive();
			if (amountReceive == null) {
				amountReceive = 0d;
			}
			txtAmountReceive.setValue(df.format(amountReceive));
		}
		txtCustomerFirstName.setValue(invoiceVisa.getCustomer().getFirstName());
		txtCustomerLastName.setValue(invoiceVisa.getCustomer().getLastName());
		txtEmployee.setValue(invoiceVisa.getEmployee().getName());
		articleTablePanel.assignValues(entityId);
	}
	
	public void assignValues(Integer entityId, Integer customerId) {
		this.customerId = customerId;
		assignValues(entityId);
	}
	
	protected void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Override
	protected void reset() {
		this.customerId = null;
		txtCode.setValue("");
		txtCustomerFirstName.setValue("");
		txtCustomerLastName.setValue("");
		txtEmployee.setValue("");
		txtAmountReceive.setValue("");
	}
	
	private void setEnabledControls(boolean enabled) {
//		txtCode.setEnabled(enabled);
		txtCustomerFirstName.setEnabled(enabled);
		txtCustomerLastName.setEnabled(enabled);
		txtEmployee.setEnabled(enabled);
		txtAmountReceive.setEnabled(enabled);
	}

	@Override
	protected boolean validate() {
		
		return true;
	}

	@Override
	public InvoiceVisa getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
