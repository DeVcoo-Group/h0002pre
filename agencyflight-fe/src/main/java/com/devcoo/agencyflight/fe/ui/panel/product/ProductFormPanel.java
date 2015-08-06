package com.devcoo.agencyflight.fe.ui.panel.product;

import java.text.DecimalFormat;

import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.product.Product;
import com.devcoo.agencyflight.core.product.ProductService;
import com.devcoo.agencyflight.core.product.ProductType;
import com.devcoo.agencyflight.core.product.visa.period.PeriodService;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeService;
import com.devcoo.agencyflight.core.supplier.Supplier;
import com.devcoo.agencyflight.core.supplier.SupplierService;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.devcoo.agencyflight.fe.ui.panel.product.visa.ProductVisaFormPanel;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ProductFormPanel extends AbstractFormLayout<ProductService, Product> {

	private static final long serialVersionUID = 2368702953447696338L;
	private SupplierService supplierService;
	private PeriodService periodService;
	private VisaTypeService visaTypeService;
	private CountryService countryService;
	
	private TextField txtCode;
	private TextField txtName;
	private TextField txtPrice;
	private ComboBox cboProductType;
	private ComboBox cboSupplier;
	private VerticalLayout productTypePanel;
	private ProductVisaFormPanel visaFormPanel;

	public ProductFormPanel() {
		super("productServiceImp");
	}

	@Override
	protected Component initGUI() {
		supplierService = (SupplierService) ctx.getBean("supplierServiceImp");
		periodService = (PeriodService) ctx.getBean("periodServiceImp");
		visaTypeService = (VisaTypeService) ctx.getBean("visaTypeServiceImp");
		countryService = (CountryService) ctx.getBean("countryServiceImp");
		initControls();
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(VaadinFactory.getLabel("Product Detail"));
		formLayout.addComponent(txtCode);
		formLayout.addComponent(txtName);
		formLayout.addComponent(txtPrice);
		formLayout.addComponent(cboSupplier);
		formLayout.addComponent(cboProductType);
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		horizontalLayout.addComponent(formLayout);
		horizontalLayout.addComponent(productTypePanel);
		return horizontalLayout;
	}
	
	private void initControls() {
		visaFormPanel = new ProductVisaFormPanel(periodService, visaTypeService, countryService);
		productTypePanel = new VerticalLayout();
		txtCode = VaadinFactory.getTextField("Product Code", 200, true);
		txtName = VaadinFactory.getTextField("Product Name", 200, true);
		txtPrice = VaadinFactory.getTextField("Price", 200, true);
		cboProductType = VaadinFactory.getComboBox("Product Type", 200, true, ProductType.values());
		cboProductType.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 5905436660773736609L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				productTypePanel.removeAllComponents();
				if (cboProductType.getValue() != null) {
					if ((Integer) cboProductType.getValue() == ProductType.PASSPORT_VISA.getId()) {
						visaFormPanel.assignValues(entity.getVisa());
						productTypePanel.addComponent(visaFormPanel);
					}
				}
			}
		});
		cboSupplier = VaadinFactory.getComboBox("Supplier", 200, false, supplierService.findAllNotDelete());
	}

	@Override
	protected void assignValues(Integer entityId) {
		reset();
		if (entityId == null) {
			entity = new Product();
		} else {
			entity = service.find(entityId);
			DecimalFormat df = new DecimalFormat("#0.00");
			
			txtCode.setValue(entity.getCode());
			txtName.setValue(entity.getName());
			txtPrice.setValue(df.format(entity.getPrice()));
			cboProductType.setValue(entity.getProductType());
			cboSupplier.setValue(entity.getSupplier().getId());
			if (entity.getProductType() == ProductType.PASSPORT_VISA.getId()) {
				visaFormPanel.assignValues(entity.getVisa());
			}
		}
	}

	@Override
	protected void reset() {
		txtCode.setValue("");
		txtName.setValue("");
		txtPrice.setValue("");
		cboProductType.setValue(null);
		cboSupplier.setValue(null);
		
		txtCode.setComponentError(null);
		txtName.setComponentError(null);
		txtPrice.setComponentError(null);
		cboProductType.setComponentError(null);
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
		} else {
			if ((Integer) cboProductType.getValue() == ProductType.PASSPORT_VISA.getId()) {
				if (!visaFormPanel.validate()) {
					valid = false;
				}
			}
		}
		
		return valid;
	}
	
	@Override
	protected void save() {
		Supplier supplier = null;
		if (cboSupplier.getValue() != null) {
			supplier = supplierService.find((Integer) cboSupplier.getValue());
		}
		entity.setCode(txtCode.getValue());
		entity.setName(txtName.getValue());
		entity.setPrice(Double.valueOf(txtPrice.getValue()));
		entity.setProductType((Integer) cboProductType.getValue());
		entity.setSupplier(supplier);
		if ((Integer) cboProductType.getValue() == ProductType.PASSPORT_VISA.getId()) {
			entity.setVisa(visaFormPanel.getEntity());
		}
		service.save(entity);
	}

}
