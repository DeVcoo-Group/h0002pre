package com.devcoo.agencyflight.fe.ui.panel.product;

import java.util.Arrays;

import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.product.Product;
import com.devcoo.agencyflight.core.product.ProductService;
import com.devcoo.agencyflight.core.product.ProductType;
import com.devcoo.agencyflight.core.product.visa.period.PeriodService;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeService;
import com.devcoo.agencyflight.core.std.ApplicationContext;
import com.devcoo.agencyflight.core.supplier.Supplier;
import com.devcoo.agencyflight.core.supplier.SupplierService;
import com.devcoo.agencyflight.core.ui.field.selelct.ComboBox;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.util.CodeGenerator;
import com.devcoo.agencyflight.core.util.NumberUtil;
import com.devcoo.agencyflight.core.util.Tools;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.devcoo.agencyflight.fe.ui.panel.product.visa.ProductVisaFormPanel;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
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
	private ComboBox<ProductType> cboProductType;
	private ComboBox<Supplier> cboSupplier;
	private VerticalLayout productTypePanel;
	private ProductVisaFormPanel visaFormPanel;

	public ProductFormPanel() {
		super("productServiceImp");
	}

	@Override
	protected Component initGUI() {
		supplierService = (SupplierService) ApplicationContext.getContext().getBean("supplierServiceImp");
		periodService = (PeriodService) ApplicationContext.getContext().getBean("periodServiceImp");
		visaTypeService = (VisaTypeService) ApplicationContext.getContext().getBean("visaTypeServiceImp");
		countryService = (CountryService) ApplicationContext.getContext().getBean("countryServiceImp");
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
		cboProductType = VaadinFactory.getComboBox("Product Type", Arrays.asList(ProductType.values()));
		cboProductType.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 5905436660773736609L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				productTypePanel.removeAllComponents();
				if (cboProductType.getEntity() != null) {
					if (cboProductType.getEntity() == ProductType.PASSPORT_VISA) {
						visaFormPanel.assignValues(entity.getVisa());
						productTypePanel.addComponent(visaFormPanel);
					}
				}
			}
		});
		cboSupplier = VaadinFactory.getComboBox("Supplier", supplierService.findAllNotDelete());
	}

	@Override
	protected void assignValues(Integer entityId) {
		reset();
		if (entityId == null) {
			entity = new Product();
		} else {
			entity = service.find(entityId);
			txtCode.setValue(entity.getCode());
			txtName.setValue(entity.getName());
			txtPrice.setValue(NumberUtil.formatCurrency(entity.getPrice()));
			cboProductType.setEntity((ProductType) Tools.getEnum(entity.getProductType(), ProductType.values()));
			cboSupplier.setEntity(entity.getSupplier());
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
		cboProductType.setEntity(null);
		cboSupplier.setEntity(null);
		
		txtCode.setComponentError(null);
		txtName.setComponentError(null);
		txtPrice.setComponentError(null);
		cboProductType.setComponentError(null);
	}

	@Override
	protected boolean validate() {
		boolean valid = true;
		if (!ValidationUtil.validateRequiredTextField(txtName)) {
			valid = false;
		}
		if (!ValidationUtil.validateRequiredTextField(txtPrice)) {
			valid = false;
		} else if (!ValidationUtil.validateDoubleField(txtPrice)) {
			valid = false;
		}
		if (!ValidationUtil.validateRequiredSelectField(cboProductType)) {
			valid = false;
		} else {
			if (cboProductType.getValue() == ProductType.PASSPORT_VISA) {
				if (!visaFormPanel.validate()) {
					valid = false;
				}
			}
		}
		
		return valid;
	}
	
	@Override
	protected void save() {
		entity.setName(txtName.getValue());
		entity.setPrice(NumberUtil.getDouble(txtPrice));
		entity.setProductType((Integer) cboProductType.getValue());
		entity.setSupplier(cboSupplier.getEntity());
		entity = service.save(entity);
		if ((Integer) cboProductType.getValue() == ProductType.PASSPORT_VISA.getId()) {
			entity.setCode(CodeGenerator.getGenerateCode(CodeGenerator.TYPE_VISA_PASSPORT, entity));
			entity.setVisa(visaFormPanel.getEntity());
		}
		service.save(entity);
	}

}
