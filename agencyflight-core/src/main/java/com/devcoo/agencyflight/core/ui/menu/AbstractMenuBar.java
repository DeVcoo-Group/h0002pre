package com.devcoo.agencyflight.core.ui.menu;

import com.vaadin.server.Page;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;

public abstract class AbstractMenuBar extends MenuBar {

	private static final long serialVersionUID = -8218648290671101496L;
	
	public AbstractMenuBar() {
		setStyleName(ValoTheme.MENUBAR_BORDERLESS);
		buildMenu();
	}
	
	protected abstract void buildMenu();
	
	public Command getCommand(String viewName) {
		return new DefaultCommand(viewName);
	}
	
	private class DefaultCommand implements Command {
		
		private static final long serialVersionUID = -4738395107718942614L;
		private String viewName;
		
		public DefaultCommand(String viewName) {
			this.viewName = viewName;
		}

		public void menuSelected(MenuItem selectedItem) {
			Page.getCurrent().setUriFragment("!" + viewName);
		}
		
	}

}
