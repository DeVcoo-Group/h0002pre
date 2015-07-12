package com.devcoo.agencyflight.fe.ui.panel.invoice;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.invoice.visa.InvoiceVisa;
import com.devcoo.agencyflight.core.invoice.visa.InvoiceVisaService;
import com.devcoo.agencyflight.core.ui.layout.AbstractTabsheet;
import com.devcoo.agencyflight.fe.ui.panel.invoice.customer.InvoiceCustomerFormPanel;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(InvoiceHolderPanel.NAME)
public class InvoiceHolderPanel extends AbstractTabsheet<InvoiceVisaService, InvoiceVisa> {

	private static final long serialVersionUID = 67491144499007131L;
	public static final String NAME = "fe.invoice";
	
	private InvoiceCustomerFormPanel invoiceCustomerFormPanel = new InvoiceCustomerFormPanel();
	private InvoiceFormPanel formPanel = new InvoiceFormPanel();

	@Override
	protected InvoiceTablePanel getListLayout() {
		return new InvoiceTablePanel();
	}

	@Override
	protected void addNewEntity() {
		invoiceCustomerFormPanel.reset();
		invoiceCustomerFormPanel.setCaption("New Invoice");
		invoiceCustomerFormPanel.assignValues(null);
		addFormLayout(invoiceCustomerFormPanel);
	}
	
	public void addInvoiceTab(Integer customerId) {
		removeFormLayout(invoiceCustomerFormPanel);
		formPanel.setCaption("New Invoice");
		formPanel.reset();
		formPanel.assignValues(null, customerId);
		addFormLayout(formPanel);
	}

	@Override
	protected void editEntity(Integer entityId) {
		formPanel.setCaption("New Invoice");
		formPanel.reset();
		formPanel.assignValues(entityId);
		addFormLayout(formPanel);
	}
	
	@Override
	protected void viewEntity(Integer entityId) {
		formPanel.setCaption("View Invoice");
		formPanel.assignValues(entityId);
		addFormLayout(formPanel);
	}

}
