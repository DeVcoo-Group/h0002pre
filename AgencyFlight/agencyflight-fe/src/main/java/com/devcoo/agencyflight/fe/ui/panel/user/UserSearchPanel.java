package com.devcoo.agencyflight.fe.ui.panel.user;


import javax.persistence.criteria.CriteriaBuilder;

import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class UserSearchPanel extends AbstractSearchLayout {
	
	private static final long serialVersionUID = 8659488845400101462L;
	
	private TextField txtTest;

	@Override
	protected Component initGUI() {
		txtTest = new TextField("Testing textt");
		return new FormLayout(txtTest);
	}

	@Override
	public CriteriaBuilder getRestrictions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO to reset component
	}

}
