package com.devcoo.agencyflight.fe.ui.panel.invoice;

import java.text.DecimalFormat;

import org.vaadin.dialogs.ConfirmDialog;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.invoice.InvoiceService;
import com.devcoo.agencyflight.core.invoice.InvoiceStatus;
import com.devcoo.agencyflight.core.payment.PaymentService;
import com.devcoo.agencyflight.core.std.ApplicationContext;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.devcoo.agencyflight.fe.ui.panel.invoice.artical.InvoiceArticleTablePanel;
import com.devcoo.agencyflight.fe.ui.panel.invoice.payment.InvoicePaymentTablePanel;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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
	
	private Button btnFullPay;
	
	private Integer customerId;
	private PaymentService paymentService = (PaymentService) ApplicationContext.getContext().getBean("paymentServiceImp");
	private InvoiceArticleTablePanel articleTablePanel;
	private InvoicePaymentTablePanel paymentTablePanel;

	public InvoiceFormPanel() {
		super("invoiceServiceImp");
	}

	@Override
	protected void save() {
		entity = articleTablePanel.getEntity();
		if (entity.getId() < 1) {
			entity = service.createInvoice(entity);
		} else {
			entity = service.save(entity);
		}
	}

	@Override
	protected Component initGUI() {
		initControls();
		setEnabledControls(false);
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		verticalLayout.addComponent(buildInvoiceDetailPanel());
		verticalLayout.addComponent(articleTablePanel);
		verticalLayout.addComponent(paymentTablePanel);
		
		return verticalLayout;
	}
	
	private void initControls() {
		txtCode = VaadinFactory.getTextField("Invoice Code", 200);
		txtCustomerFirstName = VaadinFactory.getTextField("Customer first name", 200);
		txtCustomerLastName = VaadinFactory.getTextField("Customer last name", 200);
		txtEmployee = VaadinFactory.getTextField("Employee", 200);
		txtAmountReceive = VaadinFactory.getTextField("Amount receive", 200);
		btnFullPay = VaadinFactory.getButton("Full pay");
		btnFullPay.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1419062435575782097L;
			@Override
			public void buttonClick(ClickEvent event) {
				ConfirmDialog.show(UI.getCurrent(),
						"Full pay",
						"Pay the full amount",
						"Paid", "Cancel",
						new ConfirmDialog.Listener() {
					private static final long serialVersionUID = -1434125433119430677L;
					@Override
					public void onClose(ConfirmDialog conform) {
						if (conform.isConfirmed()) {
							paymentService.paid(entity);
							btnFullPay.setEnabled(false);
							paymentTablePanel.assignValues(entity);
							articleTablePanel.assignValues(entity);
						}
					}
				});
			}
		});
		articleTablePanel = new InvoiceArticleTablePanel();
		paymentTablePanel = new InvoicePaymentTablePanel();
	}
	
	private Panel buildInvoiceDetailPanel() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		horizontalLayout.setMargin(true);
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(txtCode);
		formLayout.addComponent(txtCustomerFirstName);
		formLayout.addComponent(txtAmountReceive);
		horizontalLayout.addComponent(formLayout);
		
		formLayout = new FormLayout();
		formLayout.addComponent(txtEmployee);
		formLayout.addComponent(txtCustomerLastName);
		formLayout.addComponent(btnFullPay);
		horizontalLayout.addComponent(formLayout);
		
		formLayout = new FormLayout();
		horizontalLayout.addComponent(formLayout);
		
		Panel panel = new Panel("Customer Invoice Detail");
		panel.setContent(horizontalLayout);
		return panel;
	}

	@Override
	protected void assignValues(Integer entityId) {
		if (entityId == null) {
			if (this.customerId != null) {
				entity = service.newInvoice(this.customerId);
			} else {
				String msg = "To create invoice, a customer must be exist";
				Notification info = VaadinFactory.getNotification("Error", msg, Type.ERROR_MESSAGE);
				info.show(Page.getCurrent());
			}
		} else {
			entity = service.find(entityId);
			txtCode.setValue(entity.getCode());
			DecimalFormat df = new DecimalFormat("0.00");
			Double amountReceive = paymentService.getTotalAmount(entity);
			if( amountReceive != null) {
				txtAmountReceive.setValue(df.format(amountReceive));
			}
		}
		btnFullPay.setEnabled(entity.getEStatus() != InvoiceStatus.FULL_PAY);
		txtCustomerFirstName.setValue(entity.getCustomer().getFirstName());
		txtCustomerLastName.setValue(entity.getCustomer().getLastName());
		txtEmployee.setValue(entity.getEmployee().getName());
		articleTablePanel.assignValues(entity);
		if (entity != null  && entity.getId() > 0) {
			paymentTablePanel.assignValues(entity);
		} else {
			paymentTablePanel.assignValues(null);
		}
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
		txtCode.setEnabled(enabled);
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