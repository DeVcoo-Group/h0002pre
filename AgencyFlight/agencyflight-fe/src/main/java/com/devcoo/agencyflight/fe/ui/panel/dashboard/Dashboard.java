package com.devcoo.agencyflight.fe.ui.panel.dashboard;

import java.util.Date;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.ui.layout.AbstractLayout;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(Dashboard.NAME)
public class Dashboard extends AbstractLayout {

	private static final long serialVersionUID = 6139998643434014631L;
	public static final String NAME = "fe.dashboard";
	
	public Dashboard() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		InlineDateField dateField = new InlineDateField();
		dateField.setValue(new Date());
		dateField.setEnabled(false);
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		horizontalLayout.addComponent(buildInfoLayout());
		horizontalLayout.addComponent(dateField);
		addComponent(horizontalLayout);
		setComponentAlignment(horizontalLayout, Alignment.TOP_CENTER);
		
		horizontalLayout = buildButtonLayout();
		addComponent(horizontalLayout);
		setComponentAlignment(horizontalLayout, Alignment.TOP_CENTER);
		
		horizontalLayout = buildButtonLayout();
		addComponent(horizontalLayout);
		setComponentAlignment(horizontalLayout, Alignment.TOP_CENTER);
	}
	
	private Panel buildInfoLayout() {
		Panel infoPanel = new Panel();
		infoPanel.setCaption("Information Panel");
		
		GridLayout infoGridLayout = new GridLayout(2, 8);
		infoGridLayout.setWidth(500, Unit.PIXELS);
		infoGridLayout.setMargin(true);
		
		int iCol = 0;
		int iRow = 0;
		infoGridLayout.addComponent(new Label("Total old applicant:"), iCol, iRow++);
		infoGridLayout.addComponent(new Label("Total new applicant:"), iCol, iRow++);
		infoGridLayout.addComponent(new Label("Total applicant:"), iCol, iRow++);
		infoGridLayout.addComponent(new Label("Total ticket sold:"), iCol, iRow++);
		infoGridLayout.addComponent(new Label("Total visitor:"), iCol, iRow++);
		infoGridLayout.addComponent(new Label("Sample data:"), iCol, iRow++);
		infoGridLayout.addComponent(new Label("Sample information:"), iCol, iRow++);
		infoGridLayout.addComponent(new Label("Sample system demo:"), iCol, iRow++);
		
		iCol  = 1;
		iRow = 0;
		Label lbl1 = getLabel("10");
		Label lbl2 = getLabel("30");
		Label lbl3 = getLabel("24");
		Label lbl4 = getLabel("56");
		Label lbl5 = getLabel("0");
		Label lbl6 = getLabel("0");
		Label lbl7 = getLabel("0");
		Label lbl8 = getLabel("0");
		infoGridLayout.addComponent(lbl1, iCol, iRow++);
		infoGridLayout.addComponent(lbl2, iCol, iRow++);
		infoGridLayout.addComponent(lbl3, iCol, iRow++);
		infoGridLayout.addComponent(lbl4, iCol, iRow++);
		infoGridLayout.addComponent(lbl5, iCol, iRow++);
		infoGridLayout.addComponent(lbl6, iCol, iRow++);
		infoGridLayout.addComponent(lbl7, iCol, iRow++);
		infoGridLayout.addComponent(lbl8, iCol, iRow++);
		
		infoGridLayout.setComponentAlignment(lbl1, Alignment.MIDDLE_RIGHT);
		infoGridLayout.setComponentAlignment(lbl2, Alignment.MIDDLE_RIGHT);
		infoGridLayout.setComponentAlignment(lbl3, Alignment.MIDDLE_RIGHT);
		infoGridLayout.setComponentAlignment(lbl4, Alignment.MIDDLE_RIGHT);
		infoGridLayout.setComponentAlignment(lbl5, Alignment.MIDDLE_RIGHT);
		infoGridLayout.setComponentAlignment(lbl6, Alignment.MIDDLE_RIGHT);
		infoGridLayout.setComponentAlignment(lbl7, Alignment.MIDDLE_RIGHT);
		infoGridLayout.setComponentAlignment(lbl8, Alignment.MIDDLE_RIGHT);
		
		infoPanel.setContent(infoGridLayout);
		
		return infoPanel;
	}
	
	private HorizontalLayout buildButtonLayout() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setMargin(true);
		horizontalLayout.setSpacing(true);
		
		horizontalLayout.addComponent(getButton("images/sample/user.png"));
		horizontalLayout.addComponent(getButton("images/sample/user.png"));
		horizontalLayout.addComponent(getButton("images/sample/logout.png"));
		horizontalLayout.addComponent(getButton("images/sample/logout.png"));
		
		return horizontalLayout;
	}
	
	private Label getLabel(String caption) {
		Label lbl = VaadinFactory.getLabel(caption);
		lbl.setWidthUndefined();
		return lbl;
	}
	
	private Button getButton(String image) {
		Button btn = VaadinFactory.getButton(null, image);
		btn.setWidth(160, Unit.PIXELS);
		btn.setHeight(160, Unit.PIXELS);
		return btn;
	}

}
