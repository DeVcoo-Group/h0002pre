package com.devcoo.agencyflight.core.ui.layout;

import com.devcoo.agencyflight.core.std.ApplicationContext;
import com.devcoo.agencyflight.core.std.StdEntity;
import com.devcoo.agencyflight.core.std.StdService;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractServiceLayout<Service extends StdService<Entity>, Entity extends StdEntity> extends VerticalLayout {

	private static final long serialVersionUID = -4089210128098105643L;
	protected Service service;
	protected Entity entity;

	@SuppressWarnings("unchecked")
	public AbstractServiceLayout(String serviceName) {
		service = (Service) ApplicationContext.getContext().getBean(serviceName);
	}

}
