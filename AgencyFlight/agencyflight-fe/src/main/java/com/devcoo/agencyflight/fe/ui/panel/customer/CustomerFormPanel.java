package com.devcoo.agencyflight.fe.ui.panel.customer;

import java.util.Date;

import com.devcoo.agencyflight.core.customer.Customer;
import com.devcoo.agencyflight.core.customer.CustomerService;
import com.devcoo.agencyflight.core.customer.Nationality;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class CustomerFormPanel extends AbstractFormLayout<CustomerService, Customer> {

	private static final long serialVersionUID = -7906468708276408546L;
	
	private TextField txtCode;
	private TextField txtFirstName;
	private TextField txtLastName;
	private DateField dfBirthDate;
	private TextField txtBirthPlace;
	private ComboBox cboNationality;
	
	private Customer customer;

	public CustomerFormPanel() {
		super("customerServiceImp");
	}

	@Override
	protected void save() {
		customer.setCode(txtCode.getValue());
		customer.setFirstName(txtFirstName.getValue());
		customer.setLastName(txtLastName.getValue());
		customer.setBirthDate(dfBirthDate.getValue());
		customer.setBirthPlace(txtBirthPlace.getValue());
		customer.setNationality((Integer) cboNationality.getValue());
		service.save(customer);
	}

	@Override
	protected Component initGUI() {
		initControls();
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(txtCode);
		formLayout.addComponent(txtFirstName);
		formLayout.addComponent(txtLastName);
		formLayout.addComponent(dfBirthDate);
		formLayout.addComponent(txtBirthPlace);
		formLayout.addComponent(cboNationality);
		
		return formLayout;
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

	@Override
	protected void assignValues(Integer entityId) {
		reset();
		if (entityId == null) {
			customer = new Customer();
		} else {
			customer = service.find(entityId);
			txtCode.setValue(customer.getCode());
			txtFirstName.setValue(customer.getFirstName());
			txtLastName.setValue(customer.getLastName());
			dfBirthDate.setValue(customer.getBirthDate());
			txtBirthPlace.setValue(customer.getBirthPlace());
			cboNationality.setValue("");
		}
	}

	@Override
	protected void reset() {
		txtCode.setValue("");
		txtFirstName.setValue("");
		txtLastName.setValue("");
		dfBirthDate.setValue(new Date());
		txtBirthPlace.setValue("");
		cboNationality.setValue(null);
	}

	@Override
	protected boolean validate() {
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

	@Override
	public Customer getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
