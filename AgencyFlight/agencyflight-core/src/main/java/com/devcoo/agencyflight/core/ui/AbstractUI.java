package com.devcoo.agencyflight.core.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import ru.xpoft.vaadin.DiscoveryNavigator;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractUI extends UI implements ViewChangeListener {

	private static final long serialVersionUID = 9170972564399365378L;
	
	@Autowired
    private transient ApplicationContext applicationContext;
	public static String AFTER_LOG_IN_PANEL_NAME = "";
	
	private VerticalLayout topPanel;
	private VerticalLayout mainPanel;
	private VerticalLayout content;

	@Override
	protected void init(VaadinRequest request) {
		content = new VerticalLayout();
		content.setSizeFull();
		setSizeFull();
		setContent(content);
		
		this.topPanel = buildTopPanel();
		if (this.topPanel != null) {
			content.addComponent(this.topPanel);
		}
		
		this.mainPanel = buildMainPanel();
		DiscoveryNavigator navigator;
		if (this.mainPanel != null) {
			this.mainPanel.setSizeFull();
			content.addComponent(this.mainPanel);
			content.setExpandRatio(this.mainPanel, 1);
			navigator = new DiscoveryNavigator(this, this.mainPanel);
		} else {
			navigator = new DiscoveryNavigator(this, this);
		}
		navigator.addViewChangeListener(this);
		setAfterLogInPanelName();
	}
	
	public boolean beforeViewChange(ViewChangeEvent event) {
		if (StringUtils.isEmpty(event.getViewName())) {
			topPanel.setVisible(false);
			if (isLogIn()) {
				Page.getCurrent().setUriFragment("!" + AFTER_LOG_IN_PANEL_NAME);
				return false;
			}
		} else {
			topPanel.setVisible(true);
			if (!isLogIn()) {
				Page.getCurrent().setUriFragment("!");
				return false;
			}
		}
		return true;
	}

	public void afterViewChange(ViewChangeEvent event) {
	}
	
	protected abstract VerticalLayout buildTopPanel();
	
	protected abstract VerticalLayout buildMainPanel();
	
	protected abstract boolean isLogIn();
	
	protected abstract void setAfterLogInPanelName();

}
