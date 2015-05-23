package com.devcoo.agencyflight.fe.ui.header;

import com.devcoo.agencyflight.fe.ui.constant.VaadinTheme;
import com.devcoo.agencyflight.fe.ui.menu.FEMenu;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

public class TopPanel extends VerticalLayout {

	private static final long serialVersionUID = -6431393804362388676L;
	
	private Image imgLogo;
	
	public TopPanel() {
		super();
		addStyleName(VaadinTheme.LAYOUT_BACKGROUND_WHITE);
		setSpacing(false);
		initGUI();
	}
	
	private void initGUI() {
		imgLogo = new Image(null, new ThemeResource("images/logo.jpg"));
		imgLogo.setHeight(45, Unit.PIXELS);
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setMargin(true);
		horizontalLayout.addComponent(imgLogo);
		
		addComponent(horizontalLayout);
		addComponent(new FEMenu());
	}

}
