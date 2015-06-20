package com.devcoo.agencyflight.fe.ui.panel.supplier;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.supplier.Supplier;
import com.devcoo.agencyflight.core.supplier.SupplierService;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.vaadin.data.Item;
import com.vaadin.ui.Table.Align;

public class SupplierTablePanel extends AbstractListLayout<SupplierService, Supplier> {

	private static final long serialVersionUID = -8404816217718857984L;
	
	private static final String ID = "id";
	private static final String NAME = "name";

	public SupplierTablePanel() {
		super("supplierServiceImp");
	}

	@Override
	protected void initGUI() {
		setCaption("Suppliers");
		buildDefaultCRUDBar();
		table.setCaption("List Suppliers");
		refresh();
	}

	@Override
	public SupplierSearchPanel buildSearchPanel() {
		return new SupplierSearchPanel();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void renderRow(Item item, Supplier entity) {
		item.getItemProperty(ID).setValue(entity.getId());
		item.getItemProperty(NAME).setValue(entity.getName());
	}

	@Override
	protected List<Column> buildColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(ID, "Id", Integer.class, Align.RIGHT, 100));
		columns.add(new Column(NAME, "Customer Code", String.class, Align.LEFT, 150));
		return columns;
	}

	@Override
	public Supplier getEntity() {
		// TODO Auto-generated method stub
		return new Supplier();
	}

}
