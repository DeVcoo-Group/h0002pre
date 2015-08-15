package com.devcoo.agencyflight.fe.ui.panel.country;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.country.Country;
import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.vaadin.data.Item;
import com.vaadin.ui.Table.Align;

public class CountryTablePanel extends AbstractListLayout<CountryService, Country> {

	private static final long serialVersionUID = -2074082781841618630L;
	
	private static String ID = "id";
	private static String NAME = "name";
	private static String CODE2 = "code2";
	private static String CODE3 = "code3";

	public CountryTablePanel() {
		super("countryServiceImp");
	}

	@Override
	protected void initGUI() {
		setCaption("Countries");
		buildDefaultCRUDBar();
		table.setCaption("List Countr");
	}

	@Override
	protected CountrySearchPanel buildSearchPanel() {
		return new CountrySearchPanel();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderRow(Item item, Country entity) {
		item.getItemProperty(ID).setValue(entity.getId());
		item.getItemProperty(NAME).setValue(entity.getName());
		item.getItemProperty(CODE2).setValue(entity.getCode2());
		item.getItemProperty(CODE3).setValue(entity.getCode3());
	}

	@Override
	protected List<Column> buildColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(ID, "Id", Integer.class, Align.LEFT, 100));
		columns.add(new Column(NAME, "Country", String.class, Align.LEFT));
		columns.add(new Column(CODE2, "Country code 2", String.class, Align.LEFT, 200));
		columns.add(new Column(CODE3, "Country code 3", String.class, Align.LEFT, 200));
		return columns;
	}

}
