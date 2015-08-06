package com.devcoo.agencyflight.fe.ui.panel.country;

import java.util.Iterator;

import com.devcoo.agencyflight.core.country.Country;
import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.country.CountrySpecification;
import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class CountrySearchPanel extends AbstractSearchLayout<CountryService, Country> {

	private static final long serialVersionUID = -6068898222940843497L;
	private CountrySpecification specification = new CountrySpecification();
	
	private TextField txtName;
	private TextField txtCode2;
	private TextField txtCode3;

	public CountrySearchPanel() {
		super("countryServiceImp");
	}

	@Override
	protected Component initGUI() {
		initControls();
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		horizontalLayout.addComponent(new FormLayout(txtName));
		horizontalLayout.addComponent(new FormLayout(txtCode2));
		horizontalLayout.addComponent(new FormLayout(txtCode3));
		return horizontalLayout;
	}
	
	private void initControls() {
		txtName = VaadinFactory.getTextField("Country");
		txtCode2 = VaadinFactory.getTextField("Code 2");
		txtCode3 = VaadinFactory.getTextField("Code 3");
		
		txtCode2.setMaxLength(2);
		txtCode3.setMaxLength(3);
	}

	@Override
	public Iterator<Country> getRestrictions() {
		specification.setName(txtName.getValue());
		specification.setCode2(txtCode2.getValue());
		specification.setCode3(txtCode3.getValue());
		return service.findAll(specification).iterator();
	}

	@Override
	public void reset() {
		txtName.setValue("");
		txtCode2.setValue("");
		txtCode3.setValue("");
	}

}
