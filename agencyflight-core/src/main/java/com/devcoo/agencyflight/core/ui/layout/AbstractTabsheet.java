package com.devcoo.agencyflight.core.ui.layout;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.std.StdEntity;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.themes.ValoTheme;

public abstract class AbstractTabsheet<T extends StdEntity> extends AbstractLayout implements SelectedTabChangeListener {

	private static final long serialVersionUID = -9158216851730501261L;
	
	private TabSheet tabsheet;
	private AbstractListLayout<T> listLayout;
	private List<AbstractFormLayout> formLayouts;
	private boolean needRefresh;
	
	public AbstractTabsheet() {
		setMargin(true);
		setSpacing(true);
		formLayouts = new ArrayList<AbstractFormLayout>();
		
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
			}
			addListLayout();
		}
		initSelectedTab(tabsheet.getSelectedTab());
	}
	
	public void addFormLayout(AbstractFormLayout formLayout) {
		if (!formLayouts.contains(formLayout)) {
			formLayout.setMainPanel(this);
			formLayouts.add(formLayout);
			tabsheet.addTab(formLayout, formLayout.getCaption(), formLayout.getIcon());
		}
		tabsheet.setSelectedTab(formLayout);
		initSelectedTab(formLayout);
	}
	
	private void removeFormLayouts() {
		for (AbstractFormLayout formLayout : formLayouts) {
			tabsheet.removeComponent(formLayout);
		}
		formLayouts.clear();
	}
	
	private void addListLayout() {
		tabsheet.addTab(listLayout, listLayout.getCaption(), listLayout.getIcon());
	}

	public boolean isNeedRefresh() {
		return needRefresh;
	}

	public void setNeedRefresh(boolean needRefresh) {
		this.needRefresh = needRefresh;
	}
	
	protected abstract void initSelectedTab(Component selectedTab);
	
	protected abstract AbstractListLayout<T> getListLayout();
	
	protected abstract void addNewEntity();
	
	protected abstract void editEntity();
	
	protected abstract void deleteEntity();

}
