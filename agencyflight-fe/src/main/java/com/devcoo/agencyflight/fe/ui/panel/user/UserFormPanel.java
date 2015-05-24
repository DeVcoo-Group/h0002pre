package com.devcoo.agencyflight.fe.ui.panel.user;

import com.devcoo.agencyflight.core.std.StdEntity;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class UserFormPanel extends AbstractFormLayout {

	private static final long serialVersionUID = 2334203444202798134L;

	@Override
	public Component initGUI() {
		setCaption("User");
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(new TextField("User name"));
		formLayout.addComponent(new TextField("User name"));
		formLayout.addComponent(new TextField("User name"));
		formLayout.addComponent(new TextField("User name"));
		return formLayout;
	}

	@Override
	protected void assignValues(Long entityId) {
		
	}

	@Override
	protected void reset() {
		
	}

	@Override
	protected StdEntity getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

}
