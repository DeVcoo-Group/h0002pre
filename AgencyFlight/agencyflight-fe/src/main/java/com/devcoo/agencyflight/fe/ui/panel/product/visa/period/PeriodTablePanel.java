package com.devcoo.agencyflight.fe.ui.panel.product.visa.period;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.product.visa.period.Period;
import com.devcoo.agencyflight.core.product.visa.period.PeriodService;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.vaadin.data.Item;
import com.vaadin.ui.Table.Align;

public class PeriodTablePanel extends AbstractListLayout<PeriodService, Period> {

	private static final long serialVersionUID = -5810581181211619990L;
	
	private static String ID = "id";
	private static String DAYS = "days";

	public PeriodTablePanel() {
		super("periodServiceImp");
	}

	@Override
	protected void initGUI() {
		setCaption("Periods");
		buildDefaultCRUDBar();
		table.setCaption("List Periods");
	}

	@Override
	protected PeriodSearchPanel buildSearchPanel() {
		return new PeriodSearchPanel();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderRow(Item item, Period entity) {
		item.getItemProperty(ID).setValue(entity.getId());
		item.getItemProperty(DAYS).setValue(entity.getDay());
	}

	@Override
	protected List<Column> buildColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(ID, "Id", Integer.class, Align.LEFT, 50));
		columns.add(new Column(DAYS, "Days", String.class, Align.LEFT));
		return columns;
	}

}
