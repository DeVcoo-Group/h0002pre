package com.devcoo.agencyflight.core.ui.layout.customer;

import java.util.Date;

import com.devcoo.agencyflight.core.country.Country;
import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.ui.field.selelct.ComboBox;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class BaseCustomerFormPanel extends FormLayout {

	private static final long serialVersionUID = 6372093066737534361L;
	
	private CountryService service;
	
	private TextField txtCode;
	private TextField txtFirstName;
	private TextField txtLastName;
	private DateField dfBirthDate;
	private TextField txtBirthPlace;
	private ComboBox<Country> cboNationality;
	
	public BaseCustomerFormPanel(CountryService service) {
		super();
		this.service = service;
		initGUI();
	}
	
	protected void initGUI() {
		initControls();
		addComponent(VaadinFactory.getLabel("Customer detail"));
		addComponent(txtCode);
		addComponent(txtFirstName);
		addComponent(txtLastName);
		addComponent(dfBirthDate);
		addComponent(txtBirthPlace);
		addComponent(cboNationality);
	}
	
	private void initControls() {
		txtCode = VaadinFactory.getTextField("Customer Code", 200,false);
		txtCode.setEnabled(false);
		txtFirstName = VaadinFactory.getTextField("First name", 200, true);
		txtLastName = VaadinFactory.getTextField("Last name", 200, true);
		dfBirthDate = new DateField("Birth date");
		dfBirthDate.setValue(new Date());
		dfBirthDate.setWidth(200, Unit.PIXELS);
		txtBirthPlace = VaadinFactory.getTextField("Birth place", 200);
		cboNationality = VaadinFactory.getComboBox("Nationality", service.findAllNotDelete());
	}
	
	public void assignValues(Customer customer) {
		if (customer != null) {
			txtCode.setValue(customer.getCode());
			txtFirstName.setValue(customer.getFirstName());
			txtLastName.setValue(customer.getLastName());
			dfBirthDate.setValue(customer.getBirthDate());
			txtBirthPlace.setValue(customer.getBirthPlace());
			cboNationality.setEntity(customer.getNationality());
		}
	}
	
	public Customer getCustomerInfo(Customer customer) {
		if (customer == null) return null;
		customer.setCode(txtCode.getValue());
		customer.setFirstName(txtFirstName.getValue());
		customer.setLastName(txtLastName.getValue());
		customer.setBirthDate(dfBirthDate.getValue());
		customer.setBirthPlace(txtBirthPlace.getValue());
		customer.setNationality(cboNationality.getEntity());
		return customer;
	}
	
	public boolean validate() {
		boolean valid = true;
		if (!ValidationUtil.validateRequiredTextField(txtFirstName)) {
			valid = false;
		}
		if (!ValidationUtil.validateRequiredTextField(txtLastName)) {
			valid = false;
		}
		
		return valid;
	}
	
	public void reset() {
		txtCode.setValue("");
		txtFirstName.setValue("");
		txtLastName.setValue("");
		dfBirthDate.setValue(new Date());
		txtBirthPlace.setValue("");
		cboNationality.setEntity(null);
	}

}
