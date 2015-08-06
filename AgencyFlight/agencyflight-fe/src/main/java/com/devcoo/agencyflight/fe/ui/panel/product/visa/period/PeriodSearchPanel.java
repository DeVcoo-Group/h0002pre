package com.devcoo.agencyflight.fe.ui.panel.product.visa.period;

import java.util.Iterator;

import com.devcoo.agencyflight.core.product.visa.period.Period;
import com.devcoo.agencyflight.core.product.visa.period.PeriodService;
import com.devcoo.agencyflight.core.product.visa.period.PeriodSpecification;
import com.devcoo.agencyflight.core.ui.layout.AbstractSearchLayout;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class PeriodSearchPanel extends AbstractSearchLayout<PeriodService, Period> {

	private static final long serialVersionUID = 8049676992736010465L;
	private PeriodSpecification specification = new PeriodSpecification();
	private TextField txtDays;

	public PeriodSearchPanel() {
		super("periodServiceImp");
	}

	@Override
	protected Component initGUI() {
		txtDays = VaadinFactory.getTextField("Days");
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(txtDays);
		return formLayout;
	}

	@Override
	public Iterator<Period> getRestrictions() {
		specification.setDay(txtDays.getValue());
		return service.findAll(specification).iterator();
	}

	@Override
	public void reset() {
		txtDays.setValue("");
	}

}
