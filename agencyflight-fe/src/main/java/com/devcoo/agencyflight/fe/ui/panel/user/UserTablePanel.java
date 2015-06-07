package com.devcoo.agencyflight.fe.ui.panel.user;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.field.selelct.SimpleTable;
import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.user.UserService;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table.Align;

public class UserTablePanel extends AbstractListLayout<UserService,User> {
	private static String serviceName = "userServiceImp";
	public UserTablePanel() {
		super(serviceName);
	}

	private static final long serialVersionUID = -9173680326980740480L;
	
	private static final String ID = "Id";
	private static final String USER_NAME = "name";
	private static final String USER_ROlE = "role";
	
	@Override
	public Component initGUI() {
		setCaption("Users");
		buildDefaultCRUDBar();
		table = new SimpleTable("List Users");
		table.addColumns(buildColumns());
		refresh();
		return table;
	}

	@Override
	public AbstractSearchLayout<UserService,User> buildSearchPanel() {
		return new UserSearchPanel();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void renderRow(Item item, User entity) {
		item.getItemProperty(ID).setValue(String.valueOf(entity.getId()));
		item.getItemProperty(USER_NAME).setValue(entity.getName());
		item.getItemProperty(USER_ROlE).setValue("");
	}
	
	@Override
	protected List<Column> buildColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(ID, "Id", String.class, Align.LEFT, 100));
		columns.add(new Column(USER_NAME, "User name", String.class, Align.LEFT, 150));
		columns.add(new Column(USER_ROlE, "User role", String.class, Align.LEFT, 150));
		return columns;
	}

	@Override
	public User getEntity() {
		return new User();
	}

}
