package com.devcoo.agencyflight.fe.ui.panel.user;

import java.util.ArrayList;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.devcoo.agencyflight.core.ui.layout.AbstractTabsheet;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.user.UserService;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(UserHolderPanel.NAME)
public class UserHolderPanel extends AbstractTabsheet<UserService,User> {
	private static final long serialVersionUID = -1435297102227846808L;
	public static final String NAME = "fe.user";
	private UserFormPanel formLayout = new UserFormPanel("userServiceImp");

	@Override
	public AbstractListLayout<UserService,User> getListLayout() {
		return new UserTablePanel();
	}

	@Override
	protected void addNewEntity() {
		formLayout.setCaption("New User");
		formLayout.assignValues(null);
		addFormLayout(formLayout);
	}

	@Override
	protected void editEntity(Integer entityId) {
		formLayout.setCaption("Edit User");
		formLayout.assignValues(entityId);
		addFormLayout(formLayout);
	}

	@Override
	protected ArrayList<AbstractFormLayout<UserService, User>> getListAbstractFormLayout() {
		return new ArrayList<AbstractFormLayout<UserService,User>>();
	}
}
