package com.devcoo.agencyflight.fe.ui.panel.product.visa.type;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.product.visa.type.VisaType;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeService;
import com.devcoo.agencyflight.core.ui.layout.AbstractTabsheet;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(VisaTypeHolderPanel.NAME)
public class VisaTypeHolderPanel extends AbstractTabsheet<VisaTypeService, VisaType> {

	private static final long serialVersionUID = -5599488236321384014L;
	
	public static final String NAME = "fe.visa.type";
	
	private VisaTypeFormPanel formPanel = new VisaTypeFormPanel();

	@Override
	protected VisaTypeTablePanel getListLayout() {
		return new VisaTypeTablePanel();
	}

	@Override
	protected void addNewEntity() {
		formPanel.setCaption("New Visa Type");
		formPanel.assignValues(null);
		addFormLayout(formPanel);
	}

	@Override
	protected void editEntity(Integer entityId) {
		formPanel.setCaption("Edit Visa Type");
		formPanel.assignValues(entityId);
		addFormLayout(formPanel);
	}

}
