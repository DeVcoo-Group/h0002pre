package testing.jod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import com.devcoo.agencyflight.core.report.ReportWriter;


public class test1 extends ReportWriter{
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"dd/MM/yyyy - HH:mm");
	private HashMap<String, Object> data = new HashMap<String, Object>();
	public static void main(String arg[]){
		test1 d = new test1();
		d.generateTestReport();
	}

	@Override
	public HashMap<String, Object> generateData() {
		// TODO Auto-generated method stub
		addSimpleFields();
		addPicture();
		addOptionalSection(true);
		addSimpleTable();
		addSimpleTableFromLists();
		addDynamicColumnCountTable(5, 4);
		addPageHeader();
		addPageFooter();
		return data;
	}

	@Override
	public String getTemplateName() {
		// TODO Auto-generated method stub
		return "template.odt";
	}
	
	public void addSimpleFields() {
		data.put("title", "\u1783");
		data.put("simple_field", "_A Simple Value For A Simple Field_");
	}

	public void addPicture() {
		try {
			// The name (in the template) of the replaced image must be
			// "jooscript.name(picture_of_lena)" (without quotes)
//			data.put(
//					"picture_of_lena",
//					new RenderedImageSource(ImageIO.read(new File(PICTURE_URL
//							.toURI()))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param display
	 *            true to display the optional section, false to hide it
	 */
	public void addOptionalSection(boolean display) {
		data.put("displayoptional", display);
	}

	public void addSimpleTable() {
		final List<TableItem> items = new ArrayList<TableItem>();
		TableItem i = new TableItem("42", "forty-two", 42);
		TableItem i2 = new TableItem();
		i2.setFoo("test for table");
		i2.setBar("");
		TableItem i3 = new TableItem("foo", "bar", 100);

		items.add(i);
		items.add(i2);
		items.add(i3);

		data.put("tableitems", items);
	}

	public void addSimpleTableFromLists() {
		final List<List<String>> rows = new ArrayList<List<String>>();
		List<String> i = new ArrayList<String>();
		i.add("ធ្វើតេសសាកល្បង");
		i.add("1000");
		i.add("d");
		List<String> i2 = new ArrayList<String>();
		i2.add("bar");
		i2.add("300");
		i2.add("d");
		List<String> i3 = new ArrayList<String>();
		i3.add("test");
		i3.add("42");
		i3.add("d");
		
		rows.add(i);
		rows.add(i2);
		rows.add(i3);
		
		data.put("listofrows", rows);
	}

	/**
	 * @param nbCols
	 *            the number of columns for the table
	 * @param nbRows
	 *            the number of rows for the table
	 */
	public void addDynamicColumnCountTable(int nbCols, int nbRows) {
		// Header row
		final List<String> headerRow = new ArrayList<String>();
		for (int c = 0; c < nbCols; c++) {
			headerRow.add("My Col Header " + c);
		}
		// Other rows
		final List<List<String>> rows = new ArrayList<List<String>>();
		for (int r = 0; r < nbRows; r++) {
			List<String> row = new ArrayList<String>();
			for (int c = 0; c < nbCols; c++) {
				row.add("row " + r + ", col " + c);
			}
			rows.add(row);
		}

		data.put("tableheaders", headerRow);
		data.put("tablerows", rows);
	}

	public void addPageHeader() {
		data.put("pageheader", "_Here is the header_");
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(System.currentTimeMillis());
		data.put("date", DATE_FORMAT.format(c.getTime()));
	}

	public void addPageFooter() {
		data.put("pagefooter", "_Here is the footer_");
	}
}
