package com.devcoo.agencyflight.fe.ui.panel.user;


import java.util.Iterator;

import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.user.UserRole;
import com.devcoo.agencyflight.core.user.UserService;
import com.devcoo.agencyflight.core.user.UserSpecification;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class UserSearchPanel extends AbstractSearchLayout<UserService, User> {
	private static String serviceName = "userServiceImp";
	
	private static final long serialVersionUID = 8659488845400101462L;
	private UserSpecification userQuery = new UserSpecification();
	
	private TextField txtUserName;
	private ComboBox cboRole;

	public UserSearchPanel() {
		super(serviceName);
	}
	
	@Override
	protected Component initGUI() {
		txtUserName = VaadinFactory.getTextField("User name");
		cboRole = VaadinFactory.getComboBox("User role", 200, false, UserRole.values());
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		horizontalLayout.addComponent(new FormLayout(txtUserName));
		horizontalLayout.addComponent(new FormLayout(cboRole));
		return horizontalLayout;
	}

	@Override
	public Iterator<User> getRestrictions() {
		userQuery.setName(txtUserName.getValue());
		userQuery.setRole((Integer) cboRole.getValue());
		return service.findAll(userQuery).iterator();
	}

	@Override
	public void reset() {
		txtUserName.setValue("");
		cboRole.setValue(null);
	}

}
