package com.devcoo.agencyflight.fe.ui.panel.product;

import java.util.ArrayList;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.product.Product;
import com.devcoo.agencyflight.core.product.ProductService;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.ui.layout.AbstractTabsheet;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.user.UserService;

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

	@Override
	protected ArrayList<AbstractFormLayout<ProductService, Product>> getListAbstractFormLayout() {
		return new ArrayList<AbstractFormLayout<ProductService, Product>>();
	}

}
