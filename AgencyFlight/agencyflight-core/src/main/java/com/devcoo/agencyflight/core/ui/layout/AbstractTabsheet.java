package com.devcoo.agencyflight.core.ui.layout;

import java.util.List;

import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.themes.ValoTheme;

public abstract class AbstractTabsheet extends AbstractLayout implements SelectedTabChangeListener {

	private static final long serialVersionUID = -9158216851730501261L;
	
	private TabSheet tabsheet;
	private AbstractListLayout listLayout;
	private List<AbstractFormLayout> formLayouts;
	private boolean needRefresh;
	
	public AbstractTabsheet() {
		setMargin(true);
		setSpacing(true);
		
		tabsheet = new TabSheet();
		tabsheet.setStyleName(ValoTheme.TABSHEET_FRAMED);
		this.listLayout = getListLayout();
		tabsheet.addTab(listLayout, "test", listLayout.getIcon()); //listLayout.getCaption()
		tabsheet.addSelectedTabChangeListener(this);
		addComponent(tabsheet);
	}
	
	public void selectedTabChange(SelectedTabChangeEvent event) {
		if (tabsheet.getSelectedTab() == listLayout) {
			tabsheet.removeAllComponents();
			if (isNeedRefresh()) {
				listLayout.refresh();
			}
		}
		initSelectedTab(tabsheet.getSelectedTab());
	}
	
	public void addFormLayout(AbstractFormLayout formLayout) {
		if (!formLayouts.contains(formLayout)) {
			tabsheet.addTab(formLayout);
		}
	}

	public boolean isNeedRefresh() {
		return needRefresh;
	}

	public void setNeedRefresh(boolean needRefresh) {
		this.needRefresh = needRefresh;
	}
	
	public abstract void initSelectedTab(Component selectedTab);
	
	public abstract AbstractListLayout getListLayout();

}
