package com.devcoo.agencyflight.fe.ui.panel.dashboard;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
@VaadinView(Dashboard.NAME)
public class Dashboard extends VerticalLayout implements View {

	private static final long serialVersionUID = 6139998643434014631L;
	public static final String NAME = "fe.dashboard";

	public void enter(ViewChangeEvent event) {
		addComponent(new Label("Dashboard"));
	}

}
