package com.devcoo.agencyflight.fe.ui.panel.product.visa;

import com.devcoo.agencyflight.core.country.Country;
import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.product.visa.Visa;
import com.devcoo.agencyflight.core.product.visa.period.Period;
import com.devcoo.agencyflight.core.product.visa.period.PeriodService;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeService;
import com.devcoo.agencyflight.core.util.NumberUtil;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ProductVisaFormPanel extends VerticalLayout {

	private static final long serialVersionUID = -2169845158248603492L;
	private PeriodService periodService;
	private VisaTypeService visaTypeService;
	private CountryService countryService;
	
	private ComboBox cboPeriod;
	private ComboBox cboVisaType;
	private ComboBox cboNationality;
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
		cboPeriod = VaadinFactory.getComboBox("Visa period", 200, false, periodService.findAllNotDelete());
		cboVisaType = VaadinFactory.getComboBox("Visa type", 200, true, visaTypeService.findAllNotDelete());
		cboNationality = VaadinFactory.getComboBox("Nationality", 200, false, countryService.findAllNotDelete());
		txtFindFee = VaadinFactory.getTextField("Find fee");
	}

	public void assignValues(Visa entity) {
		reset();
		this.entity = entity;
		if (this.entity == null) {
			this.entity = new Visa();
			this.entity.setDelete(false);
		} else {
			cboPeriod.setValue(entity.getPeriod() != null ? entity.getPeriod().getId() : null);
			cboVisaType.setValue(entity.getVisaType().getId());
			cboNationality.setValue(entity.getNationality() != null ? entity.getNationality().getId() : null);
			txtFindFee.setValue(NumberUtil.formatCurrency(entity.getFindFee()));
		}
	}

	protected void reset() {
		cboPeriod.setValue(null);
		cboVisaType.setValue(null);
		cboNationality.setValue(null);
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
		Period period = null;
		Country nationality = null;
		if (cboPeriod.getValue() != null) {
			period = periodService.find((Integer)cboPeriod.getValue());
		}
		if (cboNationality.getValue() != null) {
			nationality = countryService.find((Integer) cboNationality.getValue());
		}
		entity.setPeriod(period);
		entity.setVisaType(visaTypeService.find((Integer) cboVisaType.getValue()));
		entity.setNationality(nationality);
		entity.setFindFee(NumberUtil.getDouble(txtFindFee));
		return entity;
	}

}
