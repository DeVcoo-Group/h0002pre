package com.devcoo.agencyflight.fe.ui.panel.product;

import java.text.DecimalFormat;

import com.devcoo.agencyflight.core.product.Product;
import com.devcoo.agencyflight.core.product.ProductService;
import com.devcoo.agencyflight.core.product.ProductType;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ProductFormPanel extends AbstractFormLayout<ProductService, Product> {

	private static final long serialVersionUID = 2368702953447696338L;
	
	private TextField txtCode;
	private TextField txtName;
	private TextField txtPrice;
	private TextField txtServiceCharge;
	private ComboBox cboProductType;
	private TextArea taRequirement;
	private TextField txtPeriod;
	
	private Product product;

	public ProductFormPanel() {
		super("productServiceImp");
	}

	@Override
	protected Component initGUI() {
		initControls();
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(VaadinFactory.getLabel("Product Detail"));
		formLayout.addComponent(txtCode);
		formLayout.addComponent(txtName);
		formLayout.addComponent(txtPrice);
		formLayout.addComponent(txtServiceCharge);
		formLayout.addComponent(cboProductType);
		formLayout.addComponent(taRequirement);
		formLayout.addComponent(txtPeriod);
		
		return formLayout;
	}
	
	private void initControls() {
		txtCode = VaadinFactory.getTextField("Product Code", 200, true);
		txtName = VaadinFactory.getTextField("Product Name", 600, true);
		txtPrice = VaadinFactory.getTextField("Price", 200, true);
		txtServiceCharge = VaadinFactory.getTextField("Service Charge", 200);
		cboProductType = VaadinFactory.getComboBoxFromEnum("Product Type", 200, true, ProductType.values());
		taRequirement = VaadinFactory.getTextArea("Requirement", "400px", "200px", true);
		txtPeriod = VaadinFactory.getTextField("Product Period", 200, true);
		
	}

	@Override
	protected void assignValues(Integer entityId) {
		reset();
		if (entityId == null) {
			product = new Product();
		} else {
			product = service.find(entityId);
			txtCode.setValue(product.getCode());
			txtName.setValue(product.getName());
			
			DecimalFormat df = new DecimalFormat("#0.00");
			txtPrice.setValue(df.format(product.getPrice()));
			txtServiceCharge.setValue(df.format(product.getServiceCharge()));
		}
	}

	@Override
	protected void reset() {
		txtCode.setValue("");
		txtName.setValue("");
		txtPrice.setValue("");
		txtServiceCharge.setValue("");
		
		txtCode.setComponentError(null);
		txtName.setComponentError(null);
		txtPrice.setComponentError(null);
	}

	@Override
	protected boolean validate() {
		boolean valid = true;
		
		if (!ValidationUtil.validateRequiredTextField(txtCode)) {
			valid = false;
		}
		if (!ValidationUtil.validateRequiredTextField(txtName)) {
			valid = false;
		}
		if (!ValidationUtil.validateRequiredTextField(txtPrice)) {
			valid = false;
		}
		if (!ValidationUtil.validateRequiredSelectField(cboProductType)) {
			valid = false;
		}
		
		return valid;
	}
	
	@Override
	protected void save() {
		product.setCode(txtCode.getValue());
		product.setName(txtName.getValue());
		product.setPrice(Double.valueOf(txtPrice.getValue()));
		product.setServiceCharge(Double.valueOf(txtServiceCharge.getValue()));
		service.save(product);
	}

	@Override
	public Product getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
