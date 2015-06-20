package com.devcoo.agencyflight.fe.ui.panel.supplier;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.supplier.Supplier;
import com.devcoo.agencyflight.core.supplier.SupplierService;
import com.devcoo.agencyflight.core.ui.layout.AbstractTabsheet;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(SupplierHolderPanel.NAME)
public class SupplierHolderPanel extends AbstractTabsheet<SupplierService, Supplier> {

	private static final long serialVersionUID = 8980059196093846490L;
	public static final String NAME = "fe.supplier";
	
	private SupplierFormPanel formPanel = new SupplierFormPanel();

	@Override
	protected SupplierTablePanel getListLayout() {
		return new SupplierTablePanel();
	}

	@Override
	protected void addNewEntity() {
		formPanel.setCaption("New Supplier");
		formPanel.assignValues(null);
		addFormLayout(formPanel);
	}

	@Override
	protected void editEntity(Integer entityId) {
		formPanel.setCaption("Edit Supplier");
		formPanel.assignValues(entityId);
		addFormLayout(formPanel);
	}

}
