package com.devcoo.agencyflight.fe.ui.panel.invoice.artical;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.devcoo.agencyflight.core.invoice.InvoiceVisa;
import com.devcoo.agencyflight.core.invoice.InvoiceVisaArticle;
import com.devcoo.agencyflight.core.invoice.InvoiceVisaService;
import com.devcoo.agencyflight.core.product.visa.Visa;
import com.devcoo.agencyflight.core.product.visa.VisaService;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.field.selelct.SimpleTable;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.ui.layout.ButtonBar;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.data.Item;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class InvoiceVisaArticleTablePanel extends AbstractFormLayout<InvoiceVisaService, InvoiceVisa> {
	
	private static final long serialVersionUID = -1541944952619789149L;
	
	private static final String ID = "id";
	private static final String PRODUCT = "product";
	private static final String PRICE = "price";
	
	private ComboBox cbProduct;
	private TextField txtPrice;
	private List<InvoiceVisaArticle> articles;
	private List<Visa> visas;
	private VisaService visaService;
	private InvoiceVisa invoice;
	private SimpleTable tbArticles;
	private ButtonBar crudBar;

	public InvoiceVisaArticleTablePanel() {
		super("invoiceVisaServiceImp");
		setMargin(false);
	}

	@Override
	protected void buildDefaultCRUDBar() {
		crudBar = new ButtonBar();
		Button btnAdd = crudBar.addButton("Add");
		btnAdd.setIcon(FontAwesome.PLUS);
		btnAdd.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -1927041419615320662L;
			@Override
			public void buttonClick(ClickEvent event) {
				save();
			}
		});
		Button btnRemove = crudBar.addButton("Remove");
		btnRemove.setIcon(FontAwesome.MINUS);
		btnRemove.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 8331807943549453774L;
			@Override
			public void buttonClick(ClickEvent event) {
				remove();
			}
		});
	}
	
	@Override
	protected void save() {
		InvoiceVisaArticle article = new InvoiceVisaArticle();
		article.setInvoice(invoice);
		article.setName("Product name");
		article.setPrice(0.0);
		article.setVisa(visaService.find(1));
		invoice.getArticles().add(article);
		invoice = service.saveAndFlush(invoice);
		buildTableDataSource(invoice.getArticles().iterator());
	}
	
	private void remove() {
		
	}

	@Override
	protected Component initGUI() {
		initControls();
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		horizontalLayout.addComponent(cbProduct);
		horizontalLayout.addComponent(txtPrice);
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		verticalLayout.setMargin(true);
		verticalLayout.addComponent(crudBar);
		verticalLayout.addComponent(horizontalLayout);
		verticalLayout.addComponent(tbArticles);
		
		Panel panel = new Panel("Invoice items");
		panel.setContent(verticalLayout);
		return panel;
	}
	
	private void initControls() {
		visaService = (VisaService) ctx.getBean("visaServiceImp");
		visas = visaService.findAllActive();
		cbProduct = VaadinFactory.getComboBox("Product", 200, true, visas);
		txtPrice = VaadinFactory.getTextField("Product Price");
		tbArticles = new SimpleTable("Visa items list");
		tbArticles.addColumns(buildColumns());
	}

	@Override
	public void assignValues(Integer entityId) {
		if (entityId != null) {
			invoice = service.find(entityId);
			articles = invoice.getArticles();
			buildTableDataSource(articles.iterator());
		}
	}
	
	public void assignValues(InvoiceVisa invoice) {
		
	}
	
	protected void buildTableDataSource(Iterator<InvoiceVisaArticle> entities) {
		tbArticles.removeAllItems();
		if (entities != null) {
			while (entities.hasNext()) {
				InvoiceVisaArticle row = entities.next();
				renderRow(tbArticles.addItem(row.getId()), row);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void renderRow(Item item, InvoiceVisaArticle entity) {
		item.getItemProperty(ID).setValue(entity.getId());
		item.getItemProperty(PRODUCT).setValue(entity.getName());
		item.getItemProperty(PRICE).setValue(entity.getPrice());
	}
	
	protected List<Column> buildColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(ID, "Id", Integer.class, Align.RIGHT, 100));
		columns.add(new Column(PRODUCT, "Product", String.class, Align.LEFT));
		columns.add(new Column(PRICE, "Price", Double.class, Align.RIGHT, 150));
		return columns;
	}

	@Override
	protected void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<InvoiceVisaArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<InvoiceVisaArticle> articles) {
		this.articles = articles;
	}

	@Override
	public InvoiceVisa getEntity() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
