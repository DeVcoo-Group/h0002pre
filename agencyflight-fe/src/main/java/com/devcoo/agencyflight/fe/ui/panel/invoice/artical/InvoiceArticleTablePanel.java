package com.devcoo.agencyflight.fe.ui.panel.invoice.artical;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.devcoo.agencyflight.core.invoice.Invoice;
import com.devcoo.agencyflight.core.invoice.InvoiceService;
import com.devcoo.agencyflight.core.invoice.article.InvoiceArticle;
import com.devcoo.agencyflight.core.product.Product;
import com.devcoo.agencyflight.core.product.ProductService;
import com.devcoo.agencyflight.core.product.ProductType;
import com.devcoo.agencyflight.core.ui.field.selelct.Column;
import com.devcoo.agencyflight.core.ui.field.selelct.SimpleTable;
import com.devcoo.agencyflight.core.ui.layout.AbstractFormLayout;
import com.devcoo.agencyflight.core.ui.layout.ButtonBar;
import com.devcoo.agencyflight.core.util.NumberUtil;
import com.devcoo.agencyflight.core.util.Tools;
import com.devcoo.agencyflight.core.util.ValidationUtil;
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

public class InvoiceArticleTablePanel extends AbstractFormLayout<InvoiceService, Invoice> {
	
	private static final long serialVersionUID = -1541944952619789149L;
	
	private static final String ID = "id";
	private static final String PRODUCT = "product";
	private static final String PRODUCT_TYPE = "productType";
	private static final String UNIT = "unit";
	private static final String PRICE = "price";
	
	private ComboBox cboProduct;
	private TextField txtUnit;
	private TextField txtPrice;
	private SimpleTable tbArticles;
	private ButtonBar crudBar;
	private Window window;
	
	private List<Product> products;
	private ProductService productService;
	private Integer selectedItemId;

	public InvoiceArticleTablePanel() {
		super("invoiceServiceImp");
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
		InvoiceArticle article = new InvoiceArticle();
		article.setInvoice(entity);
		Product product = productService.find((Integer) cboProduct.getValue());
		article.setName(product.getName());
		article.setPrice(NumberUtil.getDouble(txtPrice));
		article.setUnit(NumberUtil.getInteger(txtUnit));
		article.setProduct(product);
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
			Iterator<InvoiceArticle> articles = entity.getArticles().iterator();
			while (articles.hasNext()) {
				InvoiceArticle article = articles.next();
				if (article.getId() == selectedItemId) {
					article.setDelete(true);
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
		productService = (ProductService) ctx.getBean("productServiceImp");
		products = productService.findAllNotDelete();
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
				if (validate()) {
					save();
					window.close();
				}
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
		
		cboProduct = VaadinFactory.getComboBox("Product", 200, true, products);
		txtUnit = VaadinFactory.getTextField("Unit");
		txtPrice = VaadinFactory.getTextField("Product Price");
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(cboProduct);
		formLayout.addComponent(txtUnit);
		formLayout.addComponent(txtPrice);
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		verticalLayout.setMargin(true);
		verticalLayout.addComponent(buttonBar);
		verticalLayout.addComponent(formLayout);
		
		window = new Window("Add new item");
		window.setWidth(380, Unit.PIXELS);
		window.setHeight(260, Unit.PIXELS);
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
	
	protected void buildTableDataSource(Iterator<InvoiceArticle> entities) {
		tbArticles.removeAllItems();
		if (entities != null) {
			while (entities.hasNext()) {
				InvoiceArticle row = entities.next();
				if (row.isDelete() == null || !row.isDelete()) {
					renderRow(tbArticles.addItem(row.getId()), row);
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void renderRow(Item item, InvoiceArticle entity) {
		item.getItemProperty(ID).setValue(entity.getId());
		item.getItemProperty(PRODUCT).setValue(entity.getName());
		item.getItemProperty(PRODUCT_TYPE).setValue(Tools.getEnumToString(entity.getProduct().getProductType(), ProductType.values()));
		item.getItemProperty(UNIT).setValue(entity.getUnit());
		item.getItemProperty(PRICE).setValue(NumberUtil.formatCurrency(entity.getPrice()));
	}
	
	protected List<Column> buildColumns() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(ID, "Id", Integer.class, Align.RIGHT, 100));
		columns.add(new Column(PRODUCT, "Product", String.class, Align.LEFT));
		columns.add(new Column(PRODUCT_TYPE, "Product type", String.class, Align.LEFT, 200));
		columns.add(new Column(UNIT, "Unit", Integer.class, Align.RIGHT, 200));
		columns.add(new Column(PRICE, "Price", String.class, Align.RIGHT, 200));
		return columns;
	}

	@Override
	protected void reset() {
		cboProduct.setValue(null);
		txtUnit.setValue("");
		txtPrice.setValue("");
		cboProduct.setComponentError(null);
		txtUnit.setComponentError(null);
		txtPrice.setComponentError(null);
	}

	@Override
	protected boolean validate() {
		boolean valid = true;
		
		if (!ValidationUtil.validateRequiredSelectField(cboProduct)) {
			valid = false;
		}
		if (!ValidationUtil.validateRequiredTextField(txtUnit)) {
			valid = false;
		} else if (!ValidationUtil.validateIntegerField(txtUnit)) {
			valid = false;
		}
		if (!ValidationUtil.validateRequiredTextField(txtPrice)) {
			valid = false;
		} else if (!ValidationUtil.validateDoubleField(txtPrice)) {
			valid = false;
		}
		
		return valid;
	}
	
}
