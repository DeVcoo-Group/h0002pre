package com.devcoo.agencyflight.core.ui.layout;

import java.util.Iterator;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.devcoo.agencyflight.core.std.StdEntity;
import com.devcoo.agencyflight.core.std.StdService;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.field.selelct.SimpleTable;
import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

public abstract class AbstractListLayout<Service extends StdService<T>,T extends StdEntity> extends AbstractServiceLayout<Service, T> implements ItemClickListener {

	private static final long serialVersionUID = -3764552665962371644L;
	
	private Resource icon;
	private AbstractSearchLayout<Service,T> searchLayout;
	private AbstractTabsheet<Service,T> tabsheet;
	private Item selectedItem;
	private Integer selectedItemId;
	protected SimpleTable table;
	
	public AbstractListLayout(String serviceName) {
		super(serviceName);
		setMargin(true);
		setSpacing(true);
		
		searchLayout = buildSearchPanel();
		searchLayout.addSearchClickListener(new SearchClickListener());
		
		table = new SimpleTable("List");
		table.addColumns(buildColumns());
		table.addItemClickListener(this);
		initGUI();
		
		addComponent(searchLayout);
		addComponent(table);
	}

	protected void buildDefaultCRUDBar() {
		ButtonBar crudBar = ButtonBar.buildDefaultCrudBar();
		crudBar.addNewButtonClickListener(new NewButtonListener());
		crudBar.addEditButtonClickListener(new EditButtonListener());
		crudBar.addDeleteButtonClickListener(new DeleteButtonListener());
		addComponent(crudBar, 0);
	}
	
	protected abstract void initGUI();
	
	protected abstract AbstractSearchLayout<Service,T> buildSearchPanel();
	
	protected void buildTableDataSource(Iterator<T> entities) {
		table.removeAllItems();
		if (entities!=null) {
			while (entities.hasNext()) {
				T row = entities.next();
				renderRow(table.addItem(row.getId()), row);
			}
		}
	}
	
	protected abstract void renderRow(Item item, T entity);
	
	protected abstract List<Column> buildColumns();

	private class SearchClickListener implements ClickListener {
		private static final long serialVersionUID = 5002744533707652856L;

		public void buttonClick(ClickEvent event) {
			search();
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
			if (selectedItemId == null) {
				Notification info = new Notification("Information", "To edit, please select one item.", Type.HUMANIZED_MESSAGE);
				info.setDelayMsec(2000);
				info.show(Page.getCurrent());
			} else {
				tabsheet.editEntity(getSelectedItemId());
			}
		}
	}
	
	private class DeleteButtonListener implements ClickListener {
		private static final long serialVersionUID = 2239593442237027172L;

		public void buttonClick(ClickEvent event) {
			if (selectedItemId == null) {
				Notification info = new Notification("Information", "To delete, please select one item.", Type.HUMANIZED_MESSAGE);
				info.setDelayMsec(2000);
				info.show(Page.getCurrent());
			} else {
				ConfirmDialog.show(UI.getCurrent(), 
						"Are you sure to delete this record?",
						 new ConfirmDialog.Listener() {
							private static final long serialVersionUID = -5275174088133774427L;
							public void onClose(ConfirmDialog cfd) {
								if(cfd.isConfirmed()) {
									entity = service.find(getSelectedItemId());
									service.delete(entity);
									refresh();
								}
							}
						}		
				);
				
			}
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
		searchLayout.reset();
		selectedItem = null;
		selectedItemId = null;
		Iterator<T> entities = service.findAllActive().iterator();
		if (entities!=null) {
			buildTableDataSource(entities);
		}
	}
	
	private void search() {
		Iterator<T> entities = searchLayout.getRestrictions();
		if (entities!=null && entities.hasNext()) {
			buildTableDataSource(entities);
		} else {
			Notification info = new Notification("Information", "Sorry, no item found.", Type.HUMANIZED_MESSAGE);
			info.setDelayMsec(2000);
			info.show(Page.getCurrent());
		}
	}
	
	public void setMainPanel(AbstractTabsheet<Service,T> tabsheet) {
		this.tabsheet = tabsheet;
	}
	
	public Item getSelectedItem() {
		return selectedItem;
	}
	
	public Integer getSelectedItemId() {
		return selectedItemId;
	}

	public void itemClick(ItemClickEvent event) {
		selectedItem = event.getItem();
		selectedItemId = (Integer) event.getItemId();
	}
	
}
