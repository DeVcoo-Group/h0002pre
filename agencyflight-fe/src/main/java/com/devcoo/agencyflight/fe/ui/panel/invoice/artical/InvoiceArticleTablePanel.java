package com.devcoo.agencyflight.fe.ui.panel.invoice.artical;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.invoice.InvoiceService;
import com.devcoo.agencyflight.core.invoice.article.InvoiceArticle;
import com.devcoo.agencyflight.core.product.ProductType;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.field.selelct.SimpleTable;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.ui.layout.ButtonBar;
import com.devcoo.agencyflight.core.util.NumberUtil;
import com.devcoo.agencyflight.core.util.Tools;
import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class InvoiceArticleTablePanel extends AbstractFormLayout<InvoiceService, Invoice> {
	
	private static final long serialVersionUID = -1541944952619789149L;
	
	private static final String ID = "id";
	private static final String PRODUCT = "product";
	private static final String PRODUCT_TYPE = "productType";
	private static final String UNIT = "unit";
	private static final String PRICE = "price";
	private static final String TOTAL_PRICE = "total.price";
	
	private SimpleTable tbArticles;
	private ButtonBar crudBar;
	private InvoiceArticleFormPanel window;
	
	private Integer selectedItemId;

	public InvoiceArticleTablePanel() {
		super("invoiceServiceImp");
		setMargin(false);
	}

	@Override
	protected void buildDefaultCRUDBar() {
		crudBar = new ButtonBar();
		Button btnAdd = VaadinFactory.getButtonPrimary("Add");
		crudBar.addButton(btnAdd);
		btnAdd.setIcon(FontAwesome.PLUS);
		btnAdd.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -1927041419615320662L;
			@Override
			public void buttonClick(ClickEvent event) {
				window.reset();
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
	protected Component initGUI() {
		initControls();
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		verticalLayout.addComponent(crudBar);
		verticalLayout.addComponent(tbArticles);

		return verticalLayout;
	}
	
	@Override
	public void save() { }
	
	public void add(InvoiceArticle article) {
		article.setInvoice(entity);
		entity.getArticles().add(article);
		buildTableDataSource(entity.getArticlesNotDelete());
	}
	
	private void remove() {
		if (selectedItemId == null) {
			String msg = "To remove, please select one item.";
			Notification notification = VaadinFactory.getNotification("Information", msg);
			notification.show(Page.getCurrent());
		} else {
			InvoiceArticle article = entity.getArticles().remove(selectedItemId.intValue());
			article.setDelete(true);
			buildTableDataSource(entity.getArticlesNotDelete());
		}
	}

	private void initControls() {
		window = new InvoiceArticleFormPanel("Add new item", this, ctx);
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
	
	@Override
	public void assignValues(Integer entityId) {
		if (entityId != null) {
			entity = service.find(entityId);
			buildTableDataSource(entity.getArticlesNotDelete());
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void buildTableDataSource(List<InvoiceArticle> entities) {
		tbArticles.removeAllItems();
		if (entities != null) {
			for (int i = 0; i < entities.size(); i++) {
				Item item = tbArticles.addItem(i);
				item.getItemProperty(ID).setValue(i);
				renderRow(item, entities.get(i));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void renderRow(Item item, InvoiceArticle entity) {
		item.getItemProperty(PRODUCT).setValue(entity.getName());
		item.getItemProperty(PRODUCT_TYPE).setValue(Tools.getEnumToString(entity.getProduct().getProductType(), ProductType.values()));
		item.getItemProperty(UNIT).setValue(entity.getUnit());
		item.getItemProperty(PRICE).setValue(NumberUtil.formatCurrency(entity.getPrice()));
		item.getItemProperty(TOTAL_PRICE).setValue(NumberUtil.formatCurrency(entity.getUnit() * entity.getPrice()));
	}
	
	protected List<Column> buildColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(ID, "No.", Integer.class, Align.RIGHT, 100));
		columns.add(new Column(PRODUCT, "Product", String.class, Align.LEFT));
		columns.add(new Column(PRODUCT_TYPE, "Product type", String.class, Align.LEFT, 200));
		columns.add(new Column(UNIT, "Unit", Integer.class, Align.RIGHT, 200));
		columns.add(new Column(PRICE, "Price", String.class, Align.RIGHT, 200));
		columns.add(new Column(TOTAL_PRICE, "Total Price", String.class, Align.RIGHT, 200));
		return columns;
	}

	@Override
	protected void reset() { }

	@Override
	protected boolean validate() { return true; }
	
	public Invoice getEntity() {
		return entity;
	}
	
}
