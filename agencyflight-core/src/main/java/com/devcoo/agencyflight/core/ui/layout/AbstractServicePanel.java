package com.devcoo.agencyflight.core.ui.layout;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.devcoo.agencyflight.core.context.WebContext;
import com.devcoo.agencyflight.core.std.StdEntity;
import com.devcoo.agencyflight.core.std.StdService;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

public abstract class AbstractServicePanel<Service extends StdService<Entity>, Entity extends StdEntity> extends Panel {
	
	private static final long serialVersionUID = -8191116963359448559L;
	// For service
	protected ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	protected Service service;
	protected WebContext context;
	protected Entity entity;

	@SuppressWarnings("unchecked")
	public AbstractServicePanel(String serviceName) {
		service = (Service) ctx.getBean(serviceName);
		context = (WebContext) UI.getCurrent().getSession().getAttribute(WebContext.WEB_CONTEXT);
		if (context == null) {
			context = new WebContext();
		}
	}
}
