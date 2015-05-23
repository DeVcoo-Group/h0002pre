package com.devcoo.agencyflight.core.ui.field.selelct;

import com.vaadin.ui.Table;

public class SimpleTable extends Table {
	
	private static final long serialVersionUID = 5884533621024236413L;
	
	private static int defaultPageLength = 10;

	public SimpleTable(String caption) {
		super();
		setCaption(caption);
		setPageLength(defaultPageLength);
		setSizeFull();
	}

}
