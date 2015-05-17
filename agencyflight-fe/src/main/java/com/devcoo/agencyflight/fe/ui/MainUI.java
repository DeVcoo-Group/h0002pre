package com.devcoo.agencyflight.fe.ui;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.devcoo.agencyflight.core.ui.AbstractUI;
import com.devcoo.agencyflight.fe.ui.header.TopPanel;
import com.devcoo.agencyflight.fe.ui.panel.dashboard.Dashboard;
import com.vaadin.annotations.Theme;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
@Theme("agencyflight")
public class MainUI extends AbstractUI {

	private static final long serialVersionUID = 5766722647521771460L;

	@Override
	protected VerticalLayout buildTopPanel() {
		return new TopPanel();
	}

	@Override
	protected VerticalLayout buildMainPanel() {
		return new VerticalLayout();
	}

	@Override
	protected boolean isLogIn() {
		Boolean isLogIn = (Boolean) UI.getCurrent().getSession().getAttribute("isLogin");
		if (isLogIn == null) {
			return false;
		} else {
			return isLogIn;
		}
	}

	@Override
	protected void setAfterLogInPanelName() {
		AFTER_LOG_IN_PANEL_NAME = Dashboard.NAME;
	}

}
