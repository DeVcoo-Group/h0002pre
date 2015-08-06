package com.devcoo.agencyflight.fe.ui.panel.product;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.product.Product;
import com.devcoo.agencyflight.core.product.ProductService;
import com.devcoo.agencyflight.core.ui.layout.AbstractTabsheet;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(ProductHolderPanel.NAME)
public class ProductHolderPanel extends AbstractTabsheet<ProductService, Product> {

	private static final long serialVersionUID = 3624545660010948422L;
	public static final String NAME = "fe.product";
	
	private ProductFormPanel formPanel = new ProductFormPanel();

	@Override
	protected ProductTablePanel getListLayout() {
		return new ProductTablePanel();
	}

	@Override
	protected void addNewEntity() {
		formPanel.setCaption("New Product");
		formPanel.assignValues(null);
		addFormLayout(formPanel);
	}

	@Override
	protected void editEntity(Integer entityId) {
		formPanel.setCaption("Edit Product");
		formPanel.assignValues(entityId);
		addFormLayout(formPanel);
	}

}
