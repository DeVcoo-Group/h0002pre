package com.devcoo.agencyflight.core.ui.field.selelct;

import java.util.List;

import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;

public class SimpleTable extends Table {
	
	private static final long serialVersionUID = 5884533621024236413L;
	
	private static int defaultPageLength = 10;

	public SimpleTable(String caption) {
		super();
		setCaption(caption);
		addStyleName(ValoTheme.TABLE_COMPACT);
		setSelectable(true);
		setEditable(false);
		setNullSelectionAllowed(false);
		setPageLength(defaultPageLength);
		setSizeFull();
	}
	
	public void addColumns(List<Column> columns) {
		if (columns != null) {
			for (Column column : columns) {
				addColumn(column);
			}
		}
	}
	
	private void addColumn(Column column) {
		addContainerProperty(column.getPropertyId(), column.getClazz(), null);
		setColumnHeader(column.getPropertyId(), column.getColumnHeader());
		setColumnAlignment(column.getPropertyId(), column.getAlign());
		setColumnWidth(column.getPropertyId(), column.getWidth());
	}

}
