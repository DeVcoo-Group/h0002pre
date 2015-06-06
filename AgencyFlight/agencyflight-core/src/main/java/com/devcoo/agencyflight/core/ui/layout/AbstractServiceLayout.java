package com.devcoo.agencyflight.core.ui.layout;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.devcoo.agencyflight.core.context.WebContext;
import com.devcoo.agencyflight.core.std.StdEntity;
import com.devcoo.agencyflight.core.std.StdService;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractServiceLayout<Service extends StdService<Entity>, Entity extends StdEntity> extends VerticalLayout {

	private static final long serialVersionUID = -4089210128098105643L;
	//For service
	protected ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	protected Service service;
	protected WebContext context;
	protected Entity entity;
	
	@SuppressWarnings("unchecked")
	public AbstractServiceLayout(String serviceName) {
		entity = getEntity();
		service = (Service)ctx.getBean(serviceName);
		context = (WebContext) UI.getCurrent().getSession().getAttribute(WebContext.WEB_CONTEXT);
		if(context == null) {
			context = new WebContext();
		}
	}
	public abstract Entity getEntity();
	
}
