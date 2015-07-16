package com.devcoo.agencyflight.fe.ui.panel.user;


import java.util.Iterator;

import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.user.UserService;
import com.devcoo.agencyflight.core.user.UserSpecification;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class UserSearchPanel extends AbstractSearchLayout<UserService, User> {
	private static String serviceName = "userServiceImp";
	
	private static final long serialVersionUID = 8659488845400101462L;
	private UserSpecification userQuery = new UserSpecification();
	
	private TextField txtTest;

	public UserSearchPanel() {
		super(serviceName);
	}
	
	@Override
	protected Component initGUI() {
		txtTest = new TextField("Testing textt");
		return new FormLayout(txtTest);
	}

	@Override
	public Iterator<User> getRestrictions() {
		userQuery.setSearchText(txtTest.getValue());
		return service.findAll(userQuery).iterator();
	}

	@Override
	public void reset() {
		// TODO to reset component
	}

}
