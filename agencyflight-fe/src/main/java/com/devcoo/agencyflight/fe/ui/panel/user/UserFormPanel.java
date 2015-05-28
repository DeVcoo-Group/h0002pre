package com.devcoo.agencyflight.fe.ui.panel.user;

import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.user.UserRole;
import com.devcoo.agencyflight.core.user.UserService;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class UserFormPanel extends AbstractFormLayout<UserService> {

	public UserFormPanel(String serviceName) {
		super("userServiceImp");
	}

	private static final long serialVersionUID = 2334203444202798134L;
	private User user;
	private TextField txtUserName;
	private TextField txtUserPassword;
	private TextField txtConfirmPassword;
	private ComboBox cboUserRole;

	@Override
	public Component initGUI() {
		txtUserName = new TextField("User Name:");
		txtUserPassword = new TextField("User Password:");
		txtConfirmPassword = new TextField("Confirm Password:");
		cboUserRole = new ComboBox("User Role:");
		
		setCaption(getCaption());
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(new Label("User Form"));
		
		txtUserName.setRequired(true);
		txtUserPassword.setRequired(true);
		txtUserName.setRequiredError("test");
//		txtUserPassword.setRequiredError(MessageLabel.NOT_EMPTY_FILED);
		//txtConfirmPassword.addValidator(new CompositeValidator(txtUserPassword.equals(txtUserPassword), MessageLabel.MUST_THE_SAME_PASS_CONFIRM));
		
		
		formLayout.addComponent(txtUserName);
		formLayout.addComponent(txtUserPassword);
		formLayout.addComponent(txtConfirmPassword);
		for (UserRole role: UserRole.values()) {
			cboUserRole.addItem(role.getId());
			cboUserRole.setItemCaption(role.getId(),role.getCode());
		}
		cboUserRole.setRequired(true);
//		cboUserRole.setRequiredError(MessageLabel.NOT_EMPTY_FILED);
		formLayout.addComponent(cboUserRole);
		return formLayout;
	}

	@Override
	public void assignValues(Long entityId) {
		if (entityId == null) {
			user = new User();
		}
	}

	@Override
	protected void reset() {
		
	}

	@Override
	protected boolean validate() {
			return true;
	}

	@Override
	protected void save() {
		user.setName(txtUserName.getValue());
		user.setPassword(txtUserPassword.getValue());
		user.setRole((Integer)cboUserRole.getValue());
		service.save(user);
	}

}
