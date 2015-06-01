package com.devcoo.agencyflight.fe.ui.panel.user;

import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.user.UserRole;
import com.devcoo.agencyflight.core.user.UserService;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class UserFormPanel extends AbstractFormLayout<UserService> {

	public UserFormPanel(String serviceName) {
		super("userServiceImp");
	}

	private static final long serialVersionUID = 2334203444202798134L;
	private User user;
	private TextField txtUserName;
	private PasswordField txtUserPassword;
	private PasswordField txtConfirmPassword;
	private ComboBox cboUserRole;

	@Override
	public Component initGUI() {
		txtUserName = VaadinFactory.getTextField("User Name", 200, true);
		txtUserPassword = VaadinFactory.getPasswordField("User Password", 200, true);
		txtConfirmPassword = VaadinFactory.getPasswordField("Confirm Password", 200);
		cboUserRole = new ComboBox("User Role");
		cboUserRole.setWidth(200, Unit.PIXELS);
		for (UserRole role: UserRole.values()) {
			cboUserRole.addItem(role.getId());
			cboUserRole.setItemCaption(role.getId(),role.getCode());
		}
		cboUserRole.setRequired(true);
		
		setCaption(getCaption());
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(new Label("User Form"));
		
		//txtConfirmPassword.addValidator(new CompositeValidator(txtUserPassword.equals(txtUserPassword), MessageLabel.MUST_THE_SAME_PASS_CONFIRM));
		
		formLayout.addComponent(txtUserName);
		formLayout.addComponent(txtUserPassword);
		formLayout.addComponent(txtConfirmPassword);
		formLayout.addComponent(cboUserRole);
		
		return formLayout;
	}

	@Override
	public void assignValues(Long entityId) {
		if (entityId == null) {
			user = new User();
			reset();
		} else {
			txtUserName.setValue(user.getName());
		}
	}

	@Override
	protected void reset() {
		txtUserName.setValue("");
		txtUserPassword.setValue("");
		txtConfirmPassword.setValue("");
		cboUserRole.setValue(null);
	}

	@Override
	protected boolean validate() {
		boolean valid = true;
		
		if (!ValidationUtil.validateRequiredTextField(txtUserName)) {
			valid = false;
		}
		if (!ValidationUtil.validateRequiredTextField(txtUserPassword)) {
			valid = false;
		}
		if (!ValidationUtil.validateRequiredSelectField(cboUserRole)) {
			valid = false;
		}
		
		return valid;
	}

	@Override
	protected void save() {
		user.setName(txtUserName.getValue());
		user.setPassword(txtUserPassword.getValue());
		user.setRole((Integer)cboUserRole.getValue());
		service.save(user);
	}

}
