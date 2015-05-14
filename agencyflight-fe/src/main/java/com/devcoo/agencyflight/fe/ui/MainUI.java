package com.devcoo.agencyflight.fe.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.DiscoveryNavigator;

import com.devcoo.agencyflight.fe.ui.header.TopPanel;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
@Theme("agencyflight")
public class MainUI extends UI {

	private static final long serialVersionUID = 5766722647521771460L;
	
	@Autowired
    private transient ApplicationContext applicationContext;

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout content = new VerticalLayout();
		VerticalLayout main = new VerticalLayout();
		setContent(content);
		
		content.addComponent(new TopPanel());
		content.addComponent(main);
		
		DiscoveryNavigator navigator = new DiscoveryNavigator(this, main);
	}

}
