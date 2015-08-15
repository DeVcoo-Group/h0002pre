package com.devcoo.agencyflight.fe.ui.panel.product.visa.period;

import com.devcoo.agencyflight.core.product.visa.period.Period;
import com.devcoo.agencyflight.core.product.visa.period.PeriodService;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class PeriodFormPanel extends AbstractFormLayout<PeriodService, Period> {

	private static final long serialVersionUID = -2282359696230792087L;
	
	private TextField txtDays;

	public PeriodFormPanel() {
		super("periodServiceImp");
	}
	
	@Override
	protected Component initGUI() {
		txtDays = VaadinFactory.getTextField("Days");
		txtDays.setRequired(true);
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(VaadinFactory.getLabel("Period Detail"));
		formLayout.addComponent(txtDays);
		return formLayout;
	}

	@Override
	protected void save() {
		entity.setDay(txtDays.getValue());
		service.save(entity);
	}

	@Override
	protected void assignValues(Integer entityId) {
		reset();
		if (entityId == null) {
			entity = new Period();
		} else {
			entity = service.find(entityId);
			txtDays.setValue(entity.getDay());
		}
	}

	@Override
	protected void reset() {
		txtDays.setValue("");
		
		txtDays.setComponentError(null);
	}

	@Override
	protected boolean validate() {
		boolean valid = true;
		
		if (!ValidationUtil.validateRequiredTextField(txtDays)) {
			valid = false;
		}
		return valid;
	}

}
