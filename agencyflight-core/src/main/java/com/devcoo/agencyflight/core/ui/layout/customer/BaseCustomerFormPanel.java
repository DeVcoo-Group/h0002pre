package com.devcoo.agencyflight.core.ui.layout.customer;

import java.util.Date;

import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.customer.Nationality;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class BaseCustomerFormPanel extends FormLayout {

	private static final long serialVersionUID = 6372093066737534361L;
	
	private TextField txtCode;
	private TextField txtFirstName;
	private TextField txtLastName;
	private DateField dfBirthDate;
	private TextField txtBirthPlace;
	private ComboBox cboNationality;
	
	public BaseCustomerFormPanel() {
		super();
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
		txtCode = VaadinFactory.getTextField("Customer Code", 200, true);
		txtFirstName = VaadinFactory.getTextField("First name", 200, true);
		txtLastName = VaadinFactory.getTextField("Last name", 200, true);
		dfBirthDate = new DateField("Birth date");
		dfBirthDate.setValue(new Date());
		dfBirthDate.setWidth(200, Unit.PIXELS);
		txtBirthPlace = VaadinFactory.getTextField("Birth place", 200);
		cboNationality = new ComboBox("Nationality");
		cboNationality.setWidth(200, Unit.PIXELS);
		for (Nationality nationality : Nationality.values()) {
			cboNationality.addItem(nationality.getId());
			cboNationality.setItemCaption(nationality.getId(), nationality.getCode());
		}
	}
	
	public void assignValues(Customer customer) {
		if (customer != null) {
			txtCode.setValue(customer.getCode());
			txtFirstName.setValue(customer.getFirstName());
			txtLastName.setValue(customer.getLastName());
			dfBirthDate.setValue(customer.getBirthDate());
			txtBirthPlace.setValue(customer.getBirthPlace());
			cboNationality.setValue("");
		}
	}
	
	public Customer getCustomerInfo(Customer customer) {
		if (customer == null) return null;
		customer.setCode(txtCode.getValue());
		customer.setFirstName(txtFirstName.getValue());
		customer.setLastName(txtLastName.getValue());
		customer.setBirthDate(dfBirthDate.getValue());
		customer.setBirthPlace(txtBirthPlace.getValue());
		customer.setNationality((Integer) cboNationality.getValue());
		return customer;
	}
	
	public boolean validate() {
		boolean valid = true;
		
		if (!ValidationUtil.validateRequiredTextField(txtCode)) {
			valid = false;
		}
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
		cboNationality.setValue(null);
	}

}
