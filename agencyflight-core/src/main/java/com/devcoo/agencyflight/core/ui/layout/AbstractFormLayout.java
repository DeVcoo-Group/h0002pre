package com.devcoo.agencyflight.core.ui.layout;

import com.devcoo.agencyflight.core.std.StdEntity;
import com.devcoo.agencyflight.core.std.StdService;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

public abstract class AbstractFormLayout<Service extends StdService<T>,T extends StdEntity> extends AbstractServiceLayout<Service,T> implements ClickListener {
	
	private static final long serialVersionUID = -7445312774424876403L;
	
	private Resource icon;
	private AbstractTabsheet<Service,T> tabsheet;
		
	public AbstractFormLayout(String serviceName) {
		super(serviceName);
		setMargin(true);
		setSpacing(true);
		
		buildDefaultCRUDBar();
		addComponent(initGUI());
	}
	
	protected void buildDefaultCRUDBar() {
		ButtonBar crudBar = new ButtonBar();
		crudBar.addSaveButton("Save");
		crudBar.addSaveButtonClickListener(this);
		addComponent(crudBar, 0);
	}
	
	public Resource getIcon() {
		return icon;
	}

	public void setIcon(Resource icon) {
		this.icon = icon;
	}
	
	public void setIcon(String icon) {
		this.icon = new ThemeResource(icon);
	}
	
	public void buttonClick(ClickEvent event) {
		if (validate()) {
			save();
			tabsheet.setNeedRefresh(Boolean.TRUE);
			Notification info = VaadinFactory.getNotification("Information", "Save successfully.", Type.HUMANIZED_MESSAGE);
			info.show(Page.getCurrent());
		}
	}
	
	public void setMainPanel(AbstractTabsheet<Service,T> tabsheet) {
		this.tabsheet = tabsheet;
	}
	
	public AbstractTabsheet<Service, T> getMainPanel() {
		return this.tabsheet;
	}
	
	protected abstract void save();
	
	protected abstract Component initGUI();
	
	protected abstract void assignValues(Integer entityId);
	
	protected abstract void reset();
	
	protected abstract boolean validate();
}
