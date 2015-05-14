package com.devcoo.agencyflight.fe.ui.header;

import com.devcoo.agencyflight.fe.ui.menu.FEMenu;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;

public class TopPanel extends HorizontalLayout {

	private static final long serialVersionUID = -6431393804362388676L;
	
	private Image imgLogo;
	
	public TopPanel() {
		super();
		setSpacing(true);
		init();
	}
	
	private void init() {
		imgLogo = new Image(null, new ThemeResource("images/logo.jpg"));
		imgLogo.setHeight(45, Unit.PIXELS);
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setWidth(5, Unit.PIXELS);
		horizontalLayout.setHeight(5, Unit.PIXELS);
		addComponent(horizontalLayout);
		addComponent(imgLogo);
		addComponent(new FEMenu());
	}

}
