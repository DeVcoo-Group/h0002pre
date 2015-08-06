package com.devcoo.agencyflight.fe.ui.panel.country;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.country.Country;
import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.ui.layout.AbstractTabsheet;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(CountryHolderPanel.NAME)
public class CountryHolderPanel extends AbstractTabsheet<CountryService, Country> {

	private static final long serialVersionUID = -6860736319664116059L;
	
	public static final String NAME = "fe.country";
	
	private CountryFormPanel formPanel = new CountryFormPanel();

	@Override
	protected CountryTablePanel getListLayout() {
		return new CountryTablePanel();
	}

	@Override
	protected void addNewEntity() {
		formPanel.setCaption("New Country");
		formPanel.assignValues(null);
		addFormLayout(formPanel);
	}

	@Override
	protected void editEntity(Integer entityId) {
		formPanel.setCaption("Edit Country");
		formPanel.assignValues(entityId);
		addFormLayout(formPanel);
	}

}
