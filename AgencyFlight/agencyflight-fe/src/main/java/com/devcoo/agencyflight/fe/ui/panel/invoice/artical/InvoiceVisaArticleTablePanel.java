package com.devcoo.agencyflight.fe.ui.panel.invoice.artical;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.devcoo.agencyflight.core.invoice.visa.InvoiceVisa;
import com.devcoo.agencyflight.core.invoice.visa.InvoiceVisaArticle;
import com.devcoo.agencyflight.core.invoice.visa.InvoiceVisaService;
import com.devcoo.agencyflight.core.product.visa.Visa;
import com.devcoo.agencyflight.core.product.visa.VisaService;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.field.selelct.SimpleTable;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.ui.layout.ButtonBar;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class InvoiceVisaArticleTablePanel extends AbstractFormLayout<InvoiceVisaService, InvoiceVisa> {
	
	private static final long serialVersionUID = -1541944952619789149L;
	
	private static final String ID = "id";
	private static final String PRODUCT = "product";
	private static final String PRICE = "price";
	
	private ComboBox cbProduct;
	private TextField txtPrice;
	private SimpleTable tbArticles;
	private ButtonBar crudBar;
	private Window window;
	
	private List<Visa> visas;
	private VisaService visaService;
	private Integer selectedItemId;

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
				UI.getCurrent().addWindow(window);
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
		article.setInvoice(entity);
		article.setName("Product name");
		article.setPrice(0.0);
		article.setVisa(visaService.find(1));
		article.setRemove(false);
		article.setDelete(false);
		entity.getArticles().add(article);
		entity = service.saveAndFlush(entity);
		buildTableDataSource(entity.getArticles().iterator());
	}
	
	private void remove() {
		if (selectedItemId == null) {
			String msg = "To remove, please select one item.";
			Notification notification = VaadinFactory.getNotification("Information", msg);
			notification.show(Page.getCurrent());
		} else {
			Iterator<InvoiceVisaArticle> articles = entity.getArticles().iterator();
			while (articles.hasNext()) {
				InvoiceVisaArticle article = articles.next();
				if (article.getId() == selectedItemId) {
					article.setRemove(true);
					break;
				}
			}
			entity = service.saveAndFlush(entity);
			buildTableDataSource(entity.getArticles().iterator());
		}
	}

	@Override
	protected Component initGUI() {
		initControls();
		buildPopup();
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		verticalLayout.addComponent(crudBar);
		verticalLayout.addComponent(tbArticles);

		return verticalLayout;
	}
	
	private void initControls() {
		visaService = (VisaService) ctx.getBean("visaServiceImp");
		visas = visaService.findAllNotDelete();
		tbArticles = new SimpleTable("Visa items list");
		tbArticles.addColumns(buildColumns());
		tbArticles.addItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = 1420186978126567856L;
			@Override
			public void itemClick(ItemClickEvent event) {
				selectedItemId = (Integer) event.getItemId();
			}
		});
	}
	
	private void buildPopup() {
		ButtonBar buttonBar = new ButtonBar();
		Button btnSave = buttonBar.addButton("Save");
		btnSave.setIcon(FontAwesome.SAVE);
		btnSave.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -123690668575982206L;

			@Override
			public void buttonClick(ClickEvent event) {
				save();
			}
		});
		Button btnCancel = buttonBar.addButton("Cancel");
		btnCancel.setIcon(FontAwesome.TIMES);
		btnCancel.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1161076330966336365L;

			@Override
			public void buttonClick(ClickEvent event) {
				window.close();
			}
		});
		
		cbProduct = VaadinFactory.getComboBox("Product", 200, true, visas);
		txtPrice = VaadinFactory.getTextField("Product Price");
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(cbProduct);
		formLayout.addComponent(txtPrice);
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		verticalLayout.setMargin(true);
		verticalLayout.addComponent(buttonBar);
		verticalLayout.addComponent(formLayout);
		
		window = new Window("Add new item");
		window.setWidth(380, Unit.PIXELS);
		window.setHeight(230, Unit.PIXELS);
		window.setModal(true);
		window.setContent(verticalLayout);
	}

	@Override
	public void assignValues(Integer entityId) {
		if (entityId != null) {
			entity = service.find(entityId);
			buildTableDataSource(entity.getArticles().iterator());
		}
	}
	
	protected void buildTableDataSource(Iterator<InvoiceVisaArticle> entities) {
		tbArticles.removeAllItems();
		if (entities != null) {
			while (entities.hasNext()) {
				InvoiceVisaArticle row = entities.next();
				if (row.isRemove() == null || !row.isRemove()) {
					renderRow(tbArticles.addItem(row.getId()), row);
				}
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
	
}
