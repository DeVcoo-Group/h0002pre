package com.devcoo.agencyflight.fe.ui.panel.user;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.devcoo.agencyflight.core.ui.layout.AbstractTabsheet;
import com.devcoo.agencyflight.core.user.User;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(UserHolderPanel.NAME)
public class UserHolderPanel extends AbstractTabsheet<User> {

	private static final long serialVersionUID = -1435297102227846808L;
	public static final String NAME = "fe.user";
	
	private AbstractFormLayout formLayout = new UserFormPanel();
	
	@Override
	public void initSelectedTab(com.vaadin.ui.Component selectedTab) {
		if (selectedTab == formLayout) {
			
		}
	}

	@Override
	public AbstractListLayout<User> getListLayout() {
		return new UserTablePanel();
	}

	@Override
	protected void addNewEntity() {
		addFormLayout(formLayout);
	}

	@Override
	protected void EditEntity() {
		addFormLayout(formLayout);
	}

}
