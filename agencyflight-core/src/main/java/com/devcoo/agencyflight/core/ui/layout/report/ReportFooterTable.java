package com.devcoo.agencyflight.core.ui.layout.report;

import com.devcoo.agencyflight.core.ui.field.selelct.SimpleTable;
import com.vaadin.ui.themes.ValoTheme;

public class ReportFooterTable extends SimpleTable {

	public ReportFooterTable(String caption) {
		super(caption);
		setFooterVisible(false);
		setColumnHeaderMode(COLUMN_HEADER_MODE_HIDDEN);
		setSelectable(false);
		addStyleName(ValoTheme.TABLE_SMALL);
		setSelectable(false);
	}

}
