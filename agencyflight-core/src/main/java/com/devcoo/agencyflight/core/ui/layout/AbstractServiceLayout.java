package com.devcoo.agencyflight.core.ui.layout;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.devcoo.agencyflight.core.context.WebContext;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractServiceLayout<Service> extends VerticalLayout {
	//For service
	protected ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	protected Service service;
	protected WebContext context;
	
	public AbstractServiceLayout(String serviceName) {
		service = (Service)ctx.getBean(serviceName);
		context = (WebContext) UI.getCurrent().getSession().getAttribute(WebContext.WEB_CONTEXT);
		if(context == null) {
			context = new WebContext();
		}
	}
	
}
