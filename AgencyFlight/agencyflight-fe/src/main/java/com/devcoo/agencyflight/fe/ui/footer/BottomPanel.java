package com.devcoo.agencyflight.fe.ui.footer;

import com.devcoo.agencyflight.core.vaadin.constant.VaadinTheme;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class BottomPanel extends VerticalLayout {

	private static final long serialVersionUID = 4837434762355321889L;
	
	private Image imgLogo;
	private Label lblDesignAndBuild;
	private Label lblCopyRight;
	private Label lblVersion;
	
	public BottomPanel() {
		super();
		addStyleName(VaadinTheme.LAYOUT_BACKGROUND_WHITE);
		setSpacing(false);
		initGUI();
	}
	
	private void initGUI() {
		imgLogo = new Image(null, new ThemeResource("images/logo.jpg"));
		imgLogo.setHeight(45, Unit.PIXELS);
		lblDesignAndBuild = VaadinFactory.getLabelHtml("Designed and built by ");
		lblCopyRight = VaadinFactory.getLabelHtml("Copyright &#x00a9; DevCoo Group. All rights reserved.");
		lblCopyRight.setSizeUndefined();
		lblVersion = VaadinFactory.getLabelHtml("Version 0.0.1");
		lblVersion.setSizeUndefined();
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		horizontalLayout.addComponent(lblDesignAndBuild);
		horizontalLayout.setComponentAlignment(lblDesignAndBuild, Alignment.MIDDLE_CENTER);
		horizontalLayout.addComponent(imgLogo);
		
		addComponent(horizontalLayout);
		addComponent(lblCopyRight);
		addComponent(lblVersion);
		addStyleName(VaadinTheme.LAYOUT_FOOTER);
		
		setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
		setComponentAlignment(lblCopyRight, Alignment.MIDDLE_CENTER);
		setComponentAlignment(lblVersion, Alignment.MIDDLE_CENTER);
	}

}
