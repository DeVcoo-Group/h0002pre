package com.devcoo.agencyflight.core.ui.layout;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.std.StdEntity;
import com.devcoo.agencyflight.core.std.StdService;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.themes.ValoTheme;

public abstract class AbstractTabsheet<Service extends StdService<T>,T extends StdEntity> extends AbstractLayout implements SelectedTabChangeListener {

	private static final long serialVersionUID = -9158216851730501261L;
	
	private TabSheet tabsheet;
	private AbstractListLayout<Service,T> listLayout;
	private List<AbstractFormLayout<Service,T>> formLayouts;
	private boolean needRefresh;
	
	public AbstractTabsheet() {
		setMargin(true);
		setSpacing(true);
		formLayouts = getListAbstractFormLayout();
		
		tabsheet = new TabSheet();
		tabsheet.setStyleName(ValoTheme.TABSHEET_FRAMED);
		this.listLayout = getListLayout();
		if (listLayout != null) {
			listLayout.setMainPanel(this);
		}
		addListLayout();
		tabsheet.addSelectedTabChangeListener(this);
		addComponent(tabsheet);
	}
	
	public void selectedTabChange(SelectedTabChangeEvent event) {
		if (tabsheet.getSelectedTab() == listLayout) {
			removeFormLayouts();
			if (isNeedRefresh()) {
				listLayout.refresh();
				setNeedRefresh(Boolean.FALSE);
			}
			addListLayout();
		}
		initSelectedTab(tabsheet.getSelectedTab());
	}
	
	public void addFormLayout(AbstractFormLayout<Service,T> formLayout) {
		if (!formLayouts.contains(formLayout)) {
			formLayout.setMainPanel(this);
			formLayouts.add(formLayout);
			tabsheet.addTab(formLayout, formLayout.getCaption(), formLayout.getIcon());
		}
		tabsheet.setSelectedTab(formLayout);
		initSelectedTab(formLayout);
	}
	
	public void selectListLayout() {
		tabsheet.setSelectedTab(0);
	}
	
	private void removeFormLayouts() {
		for (AbstractFormLayout<Service, T> formLayout : formLayouts) {
			tabsheet.removeComponent(formLayout);
		}
		formLayouts.clear();
	}
	
	protected void addListLayout() {
		tabsheet.addTab(listLayout, listLayout.getCaption(), listLayout.getIcon());
	}

	public boolean isNeedRefresh() {
		return needRefresh;
	}

	public void setNeedRefresh(boolean needRefresh) {
		this.needRefresh = needRefresh;
	}
	
	protected abstract void initSelectedTab(Component selectedTab);
	
	protected abstract AbstractListLayout<Service,T> getListLayout();
	
	protected abstract void addNewEntity();
	
	protected abstract void editEntity(Integer entityId);
	
	protected abstract ArrayList<AbstractFormLayout<Service,T>> getListAbstractFormLayout();

}
