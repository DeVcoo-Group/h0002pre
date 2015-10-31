package com.devcoo.agencyflight.core.ui.layout;

import com.devcoo.agencyflight.core.std.ApplicationContext;
import com.devcoo.agencyflight.core.std.StdEntity;
import com.devcoo.agencyflight.core.std.StdService;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

public abstract class AbstractServicePanel<Service extends StdService<Entity>, Entity extends StdEntity> extends Panel {
	
	private static final long serialVersionUID = -8191116963359448559L;
	// For service
	protected Service service;
	protected Entity entity;

	@SuppressWarnings("unchecked")
	public AbstractServicePanel(String serviceName) {
		service = (Service) ApplicationContext.getContext().getBean(serviceName);
	}
}
