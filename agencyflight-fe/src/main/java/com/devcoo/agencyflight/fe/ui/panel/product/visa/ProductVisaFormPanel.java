package com.devcoo.agencyflight.fe.ui.panel.product.visa;

import com.devcoo.agencyflight.core.country.Country;
import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.product.visa.Visa;
import com.devcoo.agencyflight.core.product.visa.period.Period;
import com.devcoo.agencyflight.core.product.visa.period.PeriodService;
import com.devcoo.agencyflight.core.product.visa.type.VisaType;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeService;
import com.devcoo.agencyflight.core.ui.field.selelct.ComboBox;
import com.devcoo.agencyflight.core.util.NumberUtil;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ProductVisaFormPanel extends VerticalLayout {

	private static final long serialVersionUID = -2169845158248603492L;
	private PeriodService periodService;
	private VisaTypeService visaTypeService;
	private CountryService countryService;
	
	private ComboBox<Period> cboPeriod;
	private ComboBox<VisaType> cboVisaType;
	private ComboBox<Country> cboNationality;
	private TextField txtFindFee;
	private Visa entity;

	public ProductVisaFormPanel(PeriodService ps, VisaTypeService vts, CountryService cs) {
		periodService = ps;
		visaTypeService = vts;
		countryService = cs;
		setSpacing(true);
		addComponent(initGUI());
	}

	protected Component initGUI() {
		initControls();
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(VaadinFactory.getLabel("Visa Type Detail"));
		formLayout.addComponent(cboPeriod);
		formLayout.addComponent(cboVisaType);
		formLayout.addComponent(cboNationality);
		formLayout.addComponent(txtFindFee);
		return formLayout;
	}
	
	private void initControls() {
		cboPeriod = VaadinFactory.getComboBox("Visa period", periodService.findAllNotDelete());
		cboVisaType = VaadinFactory.getComboBox("Visa type", visaTypeService.findAllNotDelete());
		cboNationality = VaadinFactory.getComboBox("Nationality", countryService.findAllNotDelete());
		txtFindFee = VaadinFactory.getTextField("Find fee");
	}

	public void assignValues(Visa entity) {
		reset();
		this.entity = entity;
		if (this.entity == null) {
			this.entity = new Visa();
			this.entity.setDelete(false);
		} else {
			cboPeriod.setEntity(entity.getPeriod());
			cboVisaType.setEntity(entity.getVisaType());
			cboNationality.setEntity(entity.getNationality());
			txtFindFee.setValue(NumberUtil.formatCurrency(entity.getFindFee()));
		}
	}

	protected void reset() {
		cboPeriod.setEntity(null);
		cboVisaType.setEntity(null);
		cboNationality.setEntity(null);
		txtFindFee.setValue("");
		cboVisaType.setComponentError(null);
	}

	public boolean validate() {
		boolean valid = true;
		if (!ValidationUtil.validateRequiredSelectField(cboVisaType)) {
			valid = false;
		}
		if (!ValidationUtil.validateDoubleField(txtFindFee)) {
			valid = false;
		}
		return valid;
	}
	
	public Visa getEntity() {
		entity.setPeriod(cboPeriod.getEntity());
		entity.setVisaType(cboVisaType.getEntity());
		entity.setNationality(cboNationality.getEntity());
		entity.setFindFee(NumberUtil.getDouble(txtFindFee));
		return entity;
	}

}
