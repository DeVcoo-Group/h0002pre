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

public class UserFormPanel extends AbstractFormLayout<UserService,User> {
	
	private static final long serialVersionUID = 2334203444202798134L;
	private TextField txtUserName;
	private PasswordField txtUserPassword;
	private PasswordField txtConfirmPassword;
	private ComboBox cboUserRole;

	public UserFormPanel() {
		super("userServiceImp");
	}

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
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(new Label("User Form"));
		formLayout.addComponent(txtUserName);
		formLayout.addComponent(txtUserPassword);
		formLayout.addComponent(txtConfirmPassword);
		formLayout.addComponent(cboUserRole);
		
		return formLayout;
	}

	@Override
	public void assignValues(Integer entityId) {
		reset();
		if (entityId == null) {
			entity = new User();
		} else {
			entity = service.find(entityId);
			txtUserName.setValue(entity.getName());
			cboUserRole.setValue(entity.getRole());
		}
	}

	@Override
	protected void reset() {
		txtUserName.setValue("");
		txtUserPassword.setValue("");
		txtConfirmPassword.setValue("");
		cboUserRole.setValue(null);
		
		txtUserName.setComponentError(null);
		txtUserPassword.setComponentError(null);
		txtConfirmPassword.setComponentError(null);
		cboUserRole.setComponentError(null);
	}

	@Override
	protected boolean validate() {
		boolean valid = true;
		txtConfirmPassword.setComponentError(null);
		
		if (!ValidationUtil.validateRequiredTextField(txtUserName)) {
			valid = false;
		}
		if (this.entity.getId() <= 0 || (this.entity.getId() > 0 && !"".equals(txtUserPassword.getValue()))) {
			
			if (!ValidationUtil.validateConfirmPassword(txtUserPassword, txtConfirmPassword)) {
				valid = false;
			}
		}
		if (!ValidationUtil.validateRequiredSelectField(cboUserRole)) {
			valid = false;
		}
		
		return valid;
	}
	
	protected void save() {
		entity.setName(txtUserName.getValue());
		if (entity.getId() <= 0) {
			entity.setPassword(txtUserPassword.getValue());
		}
		entity.setRole((Integer)cboUserRole.getValue());
		service.save(entity);
	}

}
