package com.devcoo.agencyflight.fe.ui.panel.product.visa.type;

import java.util.Iterator;

import com.devcoo.agencyflight.core.product.visa.type.VisaType;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeService;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeSpecification;
import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class VisaTypeSearchPanel extends AbstractSearchLayout<VisaTypeService, VisaType> {

	private static final long serialVersionUID = -7930085821118876966L;
	private VisaTypeSpecification specification = new VisaTypeSpecification();
	private TextField txtCode;

	public VisaTypeSearchPanel() {
		super("visaTypeServiceImp");
	}

	@Override
	protected Component initGUI() {
		initControls();
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(txtCode);
		return formLayout;
	}
	
	private void initControls() {
		txtCode = VaadinFactory.getTextField("Visa type Code");
	}

	@Override
	public Iterator<VisaType> getRestrictions() {
		specification.setCode(txtCode.getValue());
		return service.findAll(specification).iterator();
	}

	@Override
	public void reset() {
		txtCode.setValue("");
	}

}
