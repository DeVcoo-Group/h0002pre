package com.devcoo.agencyflight.fe.ui.panel.user;

import java.util.List;

import com.devcoo.agencyflight.core.ui.field.selelct.SimpleTable;
import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.user.User;
import com.vaadin.ui.Component;

public class UserTablePanel extends AbstractListLayout<User> {

	private static final long serialVersionUID = -9173680326980740480L;
	
	private SimpleTable table;

	@Override
	public Component initGUI() {
		setCaption("Users");
		buildDefaultCRUDBar();
		table = new SimpleTable("List Users");
		return table;
	}

	@Override
	public AbstractSearchLayout buildSearchPanel() {
		return new UserSearchPanel();
	}

	@Override
	public void buildTableContainerDataSource(List<User> entities) {
		// TODO Auto-generated method stub
		
	}

}
