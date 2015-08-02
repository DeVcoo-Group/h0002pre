package com.devcoo.agencyflight.core.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;

import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateFactory;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public abstract class ReportWriter {
	private HashMap<String, Object> data;
	private final URL TEMPLATE_URL = ReportWriter.class.getClassLoader()
			.getResource(getTemplateName());

	public abstract HashMap<String, Object> generateData();

	public abstract String getTemplateName();

	public void generateTestReport() {
		DocumentTemplateFactory documentTemplateFactory = new DocumentTemplateFactory();
		try {
			data = generateData();
			DocumentTemplate template = documentTemplateFactory
					.getTemplate(new File(TEMPLATE_URL.toURI()));
			File temporaryFile = File.createTempFile("document", ".odt");
			//File temporaryFile = new File("document.odt");

			template.createDocument(data, new FileOutputStream(temporaryFile));
			OpenOfficeConnection connection = new SocketOpenOfficeConnection(8333);
			connection.connect();
//			DocumentFormat customPdfFormat = new DocumentFormat("Portable Document Format", "application/pdf", "pdf");
//			customPdfFormat.setExportFilter(DocumentFamily.TEXT,"writer_pdf_Export");
//			customPdfFormat.setImportOption("Text (encoded)", "utf8");
//			customPdfFormat.setExportOption(DocumentFamily.TEXT,"Text (encoded)", "utf8");
 
			DocumentConverter converter = new OpenOfficeDocumentConverter(
					connection);
			File outputFile = new File("document.html");
			
			converter.convert(temporaryFile, outputFile);
			//temporaryFile.deleteOnExit();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public File generateReport() {
		DocumentTemplateFactory documentTemplateFactory = new DocumentTemplateFactory();
		
		try {
			data = generateData();
			DocumentTemplate template = documentTemplateFactory
					.getTemplate(new File(TEMPLATE_URL.toURI()));
			File temporaryFile = File.createTempFile("document", ".odt");
			
			template.createDocument(data, new FileOutputStream(temporaryFile));
			OpenOfficeConnection connection = new SocketOpenOfficeConnection(8333);
			connection.connect();
			DocumentConverter converter = new OpenOfficeDocumentConverter(
					connection);
			File outputFile = File.createTempFile("document",".html");
			//FileOutputStream fos = new FileOutputStream(outputFile);
//			converter.convert(
//					new FileInputStream(temporaryFile), 
//					new DocumentFormat("OpenDocument Text", DocumentFamily.TEXT, "application/vnd.oasis.opendocument.text ", "odt"),
//					new FileOutputStream(outputFile), 
//					new DocumentFormat("HTML", DocumentFamily.TEXT, "text/html", "html"));
			converter.convert(temporaryFile, outputFile);
			temporaryFile.deleteOnExit();
			connection.disconnect();
			//byte[] baos= null;
			//fos.write(baos);
			//return baos;
			return outputFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
