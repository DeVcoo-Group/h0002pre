package com.devcoo.agencyflight.core.ui.layout;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.std.StdEntity;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractListLayout<T extends StdEntity> extends VerticalLayout {

	private static final long serialVersionUID = -3764552665962371644L;
	
	private Resource icon;
	private AbstractSearchLayout searchLayout;
	private AbstractTabsheet<T> tabsheet;
	
	public AbstractListLayout() {
		setMargin(true);
		setSpacing(true);
		
		searchLayout = buildSearchPanel();
		searchLayout.addSearchClickListener(new SearchClickListener());
		addComponent(searchLayout);
		// TODO create crudlayout
		// TODO create searchpanel
		addComponent(initGUI());
	}

	protected void buildDefaultCRUDBar() {
		ButtonBar crudBar = ButtonBar.buildDefaultCrudBar();
		crudBar.addNewButtonClickListener(new NewButtonListener());
		crudBar.addEditButtonClickListener(new EditButtonListener());
		crudBar.addDeleteButtonClickListener(new DeleteButtonListener());
		addComponent(crudBar, 0);
		// TODO
	}
	
	public abstract Component initGUI();
	
	public abstract AbstractSearchLayout buildSearchPanel();
	
	public abstract void buildTableContainerDataSource(List<T> entities); // TODO
	
//	public abstract List<ColumnDefinitions> biuldColumnDefinitions(); // TODO

	private class SearchClickListener implements ClickListener {
		private static final long serialVersionUID = 5002744533707652856L;

		public void buttonClick(ClickEvent event) {
			searchLayout.getRestrictions(); // TODO fetch data
			buildTableContainerDataSource(new ArrayList<T>()); // TODO
		}
	}
	
	private class NewButtonListener implements ClickListener {
		private static final long serialVersionUID = 409666159411013956L;

		public void buttonClick(ClickEvent event) {
			tabsheet.addNewEntity();
		}
	}
	
	private class EditButtonListener implements ClickListener {
		private static final long serialVersionUID = -6345612035764585791L;

		public void buttonClick(ClickEvent event) {
			tabsheet.EditEntity();
		}
	}
	
	private class DeleteButtonListener implements ClickListener {
		private static final long serialVersionUID = 2239593442237027172L;

		public void buttonClick(ClickEvent event) {
			
		}
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
	
	public void refresh() {
		// TODO
	}
	
	public void setMainPanel(AbstractTabsheet<T> tabsheet) {
		this.tabsheet = tabsheet;
	}
	
}
