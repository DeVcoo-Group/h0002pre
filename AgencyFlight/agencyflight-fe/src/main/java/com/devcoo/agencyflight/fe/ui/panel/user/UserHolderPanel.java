package com.devcoo.agencyflight.fe.ui.panel.user;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.devcoo.agencyflight.core.ui.layout.AbstractTabsheet;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(UserHolderPanel.NAME)
public class UserHolderPanel extends AbstractTabsheet {

	private static final long serialVersionUID = -1435297102227846808L;
	
	public static final String NAME = "fe.user";

	@Override
	public void initSelectedTab(com.vaadin.ui.Component selectedTab) {
		
	}

	@Override
	public AbstractListLayout getListLayout() {
		return new UserTablePanel();
	}

}
