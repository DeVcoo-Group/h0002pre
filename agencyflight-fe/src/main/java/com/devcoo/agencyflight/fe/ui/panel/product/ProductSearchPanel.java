package com.devcoo.agencyflight.fe.ui.panel.product;

import java.util.Iterator;

import com.devcoo.agencyflight.core.product.Product;
import com.devcoo.agencyflight.core.product.ProductService;
import com.devcoo.agencyflight.core.product.ProductSpecification;
import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class ProductSearchPanel extends AbstractSearchLayout<ProductService, Product> {

	private static final long serialVersionUID = -8424091792187952568L;
	
	private TextField txtCode;
	private TextField txtName;

	public ProductSearchPanel() {
		super("productServiceImp");
	}

	@Override
	protected Component initGUI() {
		initControls();
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(txtCode);
		formLayout.addComponent(txtName);
		
		return formLayout;
	}
	
	private void initControls() {
		txtCode = VaadinFactory.getTextField("Product Code");
		txtName = VaadinFactory.getTextField("Product Name");
	}

	@Override
	public Iterator<Product> getRestrictions() {
		return service.findAll(new ProductSpecification()).iterator();
	}

	@Override
	public void reset() {
		txtCode.setValue("");
		txtName.setValue("");
	}

}
