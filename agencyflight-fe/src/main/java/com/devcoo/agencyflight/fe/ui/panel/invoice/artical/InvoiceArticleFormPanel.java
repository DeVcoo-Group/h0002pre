package com.devcoo.agencyflight.fe.ui.panel.invoice.artical;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;

import com.devcoo.agencyflight.core.country.Country;
import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.invoice.article.InvoiceArticle;
import com.devcoo.agencyflight.core.product.Product;
import com.devcoo.agencyflight.core.product.ProductService;
import com.devcoo.agencyflight.core.product.ProductSpecification;
import com.devcoo.agencyflight.core.product.ProductType;
import com.devcoo.agencyflight.core.product.visa.period.Period;
import com.devcoo.agencyflight.core.product.visa.period.PeriodService;
import com.devcoo.agencyflight.core.product.visa.type.VisaType;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeService;
import com.devcoo.agencyflight.core.ui.field.selelct.ComboBox;
import com.devcoo.agencyflight.core.ui.layout.ButtonBar;
import com.devcoo.agencyflight.core.util.NumberUtil;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class InvoiceArticleFormPanel extends Window implements ValueChangeListener {

	private static final long serialVersionUID = -6912643560745712617L;
	private ProductSpecification productSpecification = new ProductSpecification();
	private InvoiceArticleTablePanel mainPanel;
	
	private ProductService productService;
	private VisaTypeService visaTypeService;
	private CountryService countryService;
	private PeriodService periodService;
	
	private ComboBox<ProductType> cboProductType;
	private ComboBox<VisaType> cboVisaType;
	private ComboBox<Country> cboNationality;
	private ComboBox<Period> cboPeriod;
	private ComboBox<Product> cboProduct;
	
	private TextField txtUnit;
	private TextField txtPrice;

    public InvoiceArticleFormPanel(String caption, InvoiceArticleTablePanel mainPanel, ApplicationContext ctx) {
        setCaption(caption);
        setSizeUndefined();
        setModal(true);
        this.mainPanel = mainPanel;
        productService = (ProductService) ctx.getBean("productServiceImp");
		visaTypeService = (VisaTypeService) ctx.getBean("visaTypeServiceImp");
		countryService = (CountryService) ctx.getBean("countryServiceImp");
		periodService = (PeriodService) ctx.getBean("periodServiceImp");
        initGUI();
    }

    private void initGUI() {
    	ButtonBar buttonBar = new ButtonBar();
		Button btnSave = VaadinFactory.getButtonPrimary("Save");
		buttonBar.addButton(btnSave);
		btnSave.setIcon(FontAwesome.SAVE);
		btnSave.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -123690668575982206L;
			@Override
			public void buttonClick(ClickEvent event) {
				if (validate()) {
					mainPanel.save();
					close();
				}
			}
		});
		Button btnCancel = buttonBar.addButton("Cancel");
		btnCancel.setIcon(FontAwesome.TIMES);
		btnCancel.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1161076330966336365L;
			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});
		
		final FormLayout leftFormLayout = new FormLayout();
		
		cboProductType = VaadinFactory.getComboBox("Product type", Arrays.asList(ProductType.values()));
		cboProductType.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 3249910111408470250L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				leftFormLayout.removeAllComponents();
				leftFormLayout.addComponent(cboProductType);
				cboVisaType.setEntity(null);
				cboNationality.setEntity(null);
				cboPeriod.setEntity(null);
				if (cboProductType.getEntity() != null) {
					if (cboProductType.getEntity() == ProductType.PASSPORT_VISA) {
						leftFormLayout.addComponent(cboVisaType);
						leftFormLayout.addComponent(cboNationality);
						leftFormLayout.addComponent(cboPeriod);
					}
				}
				setProductSpecification();
			}
		});
		cboProduct = VaadinFactory.getComboBox("Product", productService.findAllNotDelete(), true);
		cboVisaType = VaadinFactory.getComboBox("Visa type", visaTypeService.findAllNotDelete());
		cboVisaType.addValueChangeListener(this);
		cboNationality = VaadinFactory.getComboBox("Nationality", countryService.findAllNotDelete());
		cboNationality.addValueChangeListener(this);
		cboPeriod = VaadinFactory.getComboBox("Period", periodService.findAllNotDelete());
		cboPeriod.addValueChangeListener(this);
		txtUnit = VaadinFactory.getTextField("Unit", true);
		txtPrice = VaadinFactory.getTextField("Product Price", true);
		
		leftFormLayout.addComponent(cboProductType);
		
		FormLayout rightFormLayout = new FormLayout();
		rightFormLayout.addComponent(cboProduct);
		rightFormLayout.addComponent(txtUnit);
		rightFormLayout.addComponent(txtPrice);
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		horizontalLayout.addComponent(leftFormLayout);
		horizontalLayout.addComponent(rightFormLayout);
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		verticalLayout.setMargin(true);
		verticalLayout.addComponent(buttonBar);
		verticalLayout.addComponent(horizontalLayout);
		
		setContent(verticalLayout);
	}
    
    private void setProductSpecification() {
		productSpecification.reset();
		productSpecification.setProductType(cboProductType.getEntity());
		productSpecification.setVisaType(cboVisaType.getEntity());
		productSpecification.setPeriod(cboPeriod.getEntity());
		productSpecification.setNationality(cboNationality.getEntity());
		cboProduct.setValues(productService.findAll(productSpecification));
	}
    
	private boolean validate() {
		boolean valid = true;
		
		if (!ValidationUtil.validateRequiredSelectField(cboProduct)) {
			valid = false;
		}
		if (!ValidationUtil.validateRequiredTextField(txtUnit)) {
			valid = false;
		} else if (!ValidationUtil.validateIntegerField(txtUnit)) {
			valid = false;
		}
		if (!ValidationUtil.validateRequiredTextField(txtPrice)) {
			valid = false;
		} else if (!ValidationUtil.validateDoubleField(txtPrice)) {
			valid = false;
		}
		
		return valid;
	}
	
	public InvoiceArticle getArticle() {
		InvoiceArticle article = new InvoiceArticle();
		Product product = cboProduct.getEntity();
		article.setName(product.getName());
		article.setPrice(NumberUtil.getDouble(txtPrice));
		article.setUnit(NumberUtil.getInteger(txtUnit));
		article.setProduct(product);
		article.setDelete(false);
		return article;
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		setProductSpecification();
	}
	
	public void reset() {
		cboProductType.setEntity(null);
		cboProduct.setEntity(null);
		txtUnit.setValue("");
		txtPrice.setValue("");
		cboProduct.setComponentError(null);
		txtUnit.setComponentError(null);
		txtPrice.setComponentError(null);
	}
	
}
