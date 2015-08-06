package com.devcoo.agencyflight.fe.ui.panel.product;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.product.Product;
import com.devcoo.agencyflight.core.product.ProductService;
import com.devcoo.agencyflight.core.product.ProductType;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.layout.AbstractListLayout;
import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.util.Tools;
import com.vaadin.data.Item;
import com.vaadin.ui.Table.Align;

public class ProductTablePanel extends AbstractListLayout<ProductService, Product> {

	private static final long serialVersionUID = 1534966245011508976L;
	
	private static String ID = "id";
	private static String CODE = "code";
	private static String NAME = "name";
	private static String PRICE = "price";
	private static String PRODUCT_TYPE = "productType";
	private static String SUPPLIER = "supplier";

	public ProductTablePanel() {
		super("productServiceImp");
	}

	@Override
	protected void initGUI() {
		setCaption("Products");
		buildDefaultCRUDBar();
		table.setCaption("List Products");
	}

	@Override
	protected AbstractSearchLayout<ProductService, Product> buildSearchPanel() {
		return new ProductSearchPanel();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderRow(Item item, Product entity) {
		item.getItemProperty(ID).setValue(entity.getId());
		item.getItemProperty(CODE).setValue(entity.getCode());
		item.getItemProperty(NAME).setValue(entity.getName());
		item.getItemProperty(PRICE).setValue(entity.getPrice());
		item.getItemProperty(PRODUCT_TYPE).setValue(Tools.getEnumToString(entity.getProductType(), ProductType.values()));
		item.getItemProperty(SUPPLIER).setValue(entity.getSupplier().getName());
	}

	@Override
	protected List<Column> buildColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(ID, "Id", Integer.class, Align.LEFT, 50));
		columns.add(new Column(CODE, "Product Code", String.class, Align.LEFT, 150));
		columns.add(new Column(NAME, "Product Name", String.class, Align.LEFT));
		columns.add(new Column(PRODUCT_TYPE, "Product Type", String.class, Align.LEFT, 100));
		columns.add(new Column(PRICE, "Price", Double.class, Align.RIGHT, 90));
		columns.add(new Column(SUPPLIER, "Supplier", String.class, Align.LEFT, 150));
		return columns;
	}

}
