package com.devcoo.agencyflight.core.ui.layout.report;

import com.devcoo.agencyflight.core.ui.field.selelct.SimpleTable;
import com.vaadin.ui.themes.ValoTheme;

public class ReportDetailTable extends SimpleTable {

	public ReportDetailTable(String caption) {
		super(caption);
		setFooterVisible(false);
		addStyleName(ValoTheme.TABLE_SMALL);
		setSelectable(false);
	}

}
