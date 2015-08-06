package com.devcoo.agencyflight.fe.ui.panel.product.visa.period;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.product.visa.period.Period;
import com.devcoo.agencyflight.core.product.visa.period.PeriodService;
import com.devcoo.agencyflight.core.ui.layout.AbstractTabsheet;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(PeriodHolderPanel.NAME)
public class PeriodHolderPanel extends AbstractTabsheet<PeriodService, Period> {

	private static final long serialVersionUID = -195444344766540100L;
	
	public static final String NAME = "fe.period";
	
	private PeriodFormPanel formPanel = new PeriodFormPanel();

	@Override
	protected PeriodTablePanel getListLayout() {
		return new PeriodTablePanel();
	}

	@Override
	protected void addNewEntity() {
		formPanel.setCaption("New Period");
		formPanel.assignValues(null);
		addFormLayout(formPanel);
	}

	@Override
	protected void editEntity(Integer entityId) {
		formPanel.setCaption("Edit Period");
		formPanel.assignValues(entityId);
		addFormLayout(formPanel);
	}

}
