package com.devcoo.agencyflight.fe.ui.panel.country;

import com.devcoo.agencyflight.core.country.Country;
import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class CountryFormPanel extends AbstractFormLayout<CountryService, Country> {

	private static final long serialVersionUID = 8909709130458506513L;
	
	private TextField txtName;
	private TextField txtCode2;
	private TextField txtCode3;

	public CountryFormPanel() {
		super("countryServiceImp");
	}

	@Override
	protected Component initGUI() {
		initControls();
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(txtName);
		formLayout.addComponent(txtCode2);
		formLayout.addComponent(txtCode3);
		return formLayout;
	}
	
	private void initControls() {
		txtName = VaadinFactory.getTextField("Country");
		txtCode2 = VaadinFactory.getTextField("Country Code 2");
		txtCode3 = VaadinFactory.getTextField("Country Code 3");
		txtName.setRequired(true);
		txtCode2.setRequired(true);
	}

	@Override
	protected void assignValues(Integer entityId) {
		reset();
		if (entityId == null) {
			entity = new Country();
		} else {
			entity = service.find(entityId);
			txtName.setValue(entity.getName());
			txtCode2.setValue(entity.getCode2());
			txtCode3.setValue(entity.getCode3());
		}
	}
	
	@Override
	protected void save() {
		entity.setName(txtName.getValue());
		entity.setCode2(txtCode2.getValue());
		entity.setCode3(txtCode3.getValue());
		service.save(entity);
	}

	@Override
	protected void reset() {
		txtName.setValue("");
		txtCode2.setValue("");
		txtCode3.setValue("");
		txtName.setComponentError(null);
		txtCode2.setComponentError(null);
	}

	@Override
	protected boolean validate() {
		boolean valid = true;
		
		if (!ValidationUtil.validateRequiredTextField(txtName)) {
			valid = false;
		}
		if (!ValidationUtil.validateRequiredTextField(txtCode2)) {
			valid = false;
		}
		
		return valid;
	}

}
