package com.devcoo.agencyflight.fe.ui.panel.product.visa.type;

import com.devcoo.agencyflight.core.product.visa.type.VisaType;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeService;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class VisaTypeFormPanel extends AbstractFormLayout<VisaTypeService, VisaType> {

	private static final long serialVersionUID = 3772423702426635024L;
	
	private TextField txtCode;
	private TextField txtDescription;

	public VisaTypeFormPanel() {
		super("visaTypeServiceImp");
	}
	
	@Override
	protected Component initGUI() {
		initControls();
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(VaadinFactory.getLabel("Visa Type Detail"));
		formLayout.addComponent(txtCode);
		formLayout.addComponent(txtDescription);
		return formLayout;
	}
	
	private void initControls() {
		txtCode = VaadinFactory.getTextField("Visa type code");
		txtCode.setRequired(true);
		txtDescription = VaadinFactory.getTextField("Visa type description");
	}

	@Override
	protected void save() {
		entity.setCode(txtCode.getValue());
		entity.setDescription(txtDescription.getValue());
		service.save(entity);
	}

	@Override
	protected void assignValues(Integer entityId) {
		reset();
		if (entityId  == null) {
			entity = new VisaType();
		} else {
			entity = service.find(entityId);
			txtCode.setValue(entity.getCode());
			txtDescription.setValue(entity.getDescription());
		}
	}

	@Override
	protected void reset() {
		txtCode.setValue("");
		txtDescription.setValue("");
		txtCode.setComponentError(null);
	}

	@Override
	protected boolean validate() {
		boolean valid = true;
		if (!ValidationUtil.validateRequiredTextField(txtCode)) {
			valid = false;
		}
		return valid;
	}

}
