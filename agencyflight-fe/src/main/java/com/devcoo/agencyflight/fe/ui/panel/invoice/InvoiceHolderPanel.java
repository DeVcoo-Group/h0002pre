package com.devcoo.agencyflight.fe.ui.panel.invoice;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.invoice.InvoiceService;
import com.devcoo.agencyflight.core.ui.layout.AbstractTabsheet;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(InvoiceHolderPanel.NAME)
public class InvoiceHolderPanel extends AbstractTabsheet<InvoiceService, Invoice> {

	private static final long serialVersionUID = 67491144499007131L;
	public static final String NAME = "fe.invoice";
	
	private InvoiceFormPanel formPanel;

	@Override
	protected InvoiceTablePanel getListLayout() {
		return new InvoiceTablePanel();
	}

	@Override
	protected void addNewEntity() {
		// Add new invoice, not allow here
	}

	@Override
	protected void editEntity(Integer entityId) {
		// Edit invoice, not allow here
	}
	
	@Override
	protected void viewEntity(Integer entityId) {
		formPanel.setCaption("View Invoice");
		formPanel.assignValues(entityId);
		addFormLayout(formPanel);
	}

}
