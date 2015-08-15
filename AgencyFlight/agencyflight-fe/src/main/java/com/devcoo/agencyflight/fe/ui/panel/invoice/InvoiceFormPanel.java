package com.devcoo.agencyflight.fe.ui.panel.invoice;

import java.text.DecimalFormat;
import java.util.Date;

import com.devcoo.agencyflight.core.context.WebContext;
import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.customer.CustomerService;
import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.invoice.InvoiceService;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.devcoo.agencyflight.fe.ui.panel.invoice.artical.InvoiceArticleTablePanel;
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

public class InvoiceFormPanel extends AbstractFormLayout<InvoiceService, Invoice> {

	private static final long serialVersionUID = 7155913963716727055L;
	
	private TextField txtCode;
	private TextField txtCustomerFirstName;
	private TextField txtCustomerLastName;
	private TextField txtEmployee;
	private TextField txtAmountReceive;
	
	private Integer customerId;
	private CustomerService customerService = (CustomerService) ctx.getBean("customerServiceImp");
	private InvoiceArticleTablePanel articleTablePanel;

	public InvoiceFormPanel() {
		super("invoiceServiceImp");
	}

	@Override
	protected void save() {
		entity.setCode(txtCode.getValue());
		service.save(entity);
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
		articleTablePanel = new InvoiceArticleTablePanel();
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
			entity = new Invoice();
			if (this.customerId != null) {
				Customer customer = customerService.find(this.customerId);
				entity.setCode(new Date() + "");
				entity.setCustomer(customer);
				WebContext context = (WebContext) UI.getCurrent().getSession().getAttribute(WebContext.WEB_CONTEXT);
				User employee = context.getLog_user();
				entity.setEmployee(employee);
				service.save(entity);
				entityId = entity.getId();
			} else {
				String msg = "To create invoice, a customer must be exist";
				Notification info = VaadinFactory.getNotification("Error", msg, Type.ERROR_MESSAGE);
				info.show(Page.getCurrent());
			}
		} else {
			entity = service.find(entityId);
			txtCode.setValue(entity.getCode());
			DecimalFormat df = new DecimalFormat("#0.00");
			Double amountReceive = entity.getAmountReceive();
			if (amountReceive == null) {
				amountReceive = 0d;
			}
			txtAmountReceive.setValue(df.format(amountReceive));
		}
		txtCustomerFirstName.setValue(entity.getCustomer().getFirstName());
		txtCustomerLastName.setValue(entity.getCustomer().getLastName());
		txtEmployee.setValue(entity.getEmployee().getName());
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

}
