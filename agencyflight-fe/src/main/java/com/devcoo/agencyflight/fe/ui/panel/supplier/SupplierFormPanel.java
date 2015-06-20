package com.devcoo.agencyflight.fe.ui.panel.supplier;

import com.devcoo.agencyflight.core.supplier.Supplier;
import com.devcoo.agencyflight.core.supplier.SupplierService;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.util.ValidationUtil;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class SupplierFormPanel extends AbstractFormLayout<SupplierService, Supplier> {

	private static final long serialVersionUID = -7906468708276408546L;
	
	private TextField txtName;

	public SupplierFormPanel() {
		super("supplierServiceImp");
	}

	@Override
	protected void save() {
		entity.setName(txtName.getValue());
		service.save(entity);
	}

	@Override
	protected Component initGUI() {
		initControls();
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(txtName);
		
		return formLayout;
	}
	
	private void initControls() {
		txtName = VaadinFactory.getTextField("Supplier Name", 200, true);
	}

	@Override
	protected void assignValues(Integer entityId) {
		reset();
		if (entityId == null) {
			entity = new Supplier();
		} else {
			entity = service.find(entityId);
			txtName.setValue(entity.getName());
		}
	}

	@Override
	protected void reset() {
		txtName.setValue("");
	}

	@Override
	protected boolean validate() {
		boolean valid = true;
		
		if (!ValidationUtil.validateRequiredTextField(txtName)) {
			valid = false;
		}
		
		return valid;
	}

	@Override
	public Supplier getEntity() {
		// TODO Auto-generated method stub
		return new Supplier();
	}

}
