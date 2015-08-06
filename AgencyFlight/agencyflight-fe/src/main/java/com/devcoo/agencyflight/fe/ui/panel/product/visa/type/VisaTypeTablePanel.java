package com.devcoo.agencyflight.fe.ui.panel.product.visa.type;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.product.visa.type.VisaType;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeService;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.vaadin.data.Item;
import com.vaadin.ui.Table.Align;

public class VisaTypeTablePanel extends AbstractListLayout<VisaTypeService, VisaType> {

	private static final long serialVersionUID = -4495861563802009819L;
	
	private static String ID = "id";
	private static String CODE = "code";
	private static String DESCRIPTION = "description";

	public VisaTypeTablePanel() {
		super("visaTypeServiceImp");
	}

	@Override
	protected void initGUI() {
		setCaption("Visa Types");
		buildDefaultCRUDBar();
		table.setCaption("List Visa Types");
	}

	@Override
	protected VisaTypeSearchPanel buildSearchPanel() {
		return new VisaTypeSearchPanel();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderRow(Item item, VisaType entity) {
		item.getItemProperty(ID).setValue(entity.getId());
		item.getItemProperty(CODE).setValue(entity.getCode());
		item.getItemProperty(DESCRIPTION).setValue(entity.getDescription());
	}

	@Override
	protected List<Column> buildColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(ID, "Id", Integer.class, Align.LEFT, 50));
		columns.add(new Column(CODE, "Visa type Code", String.class, Align.LEFT, 150));
		columns.add(new Column(DESCRIPTION, "Visa type description", String.class, Align.LEFT));
		return columns;
	}

}
