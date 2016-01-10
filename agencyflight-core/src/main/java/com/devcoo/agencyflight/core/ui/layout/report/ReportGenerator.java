package com.devcoo.agencyflight.core.ui.layout.report;

/**
 * Created by Alvaro on 4/17/2015.
 * Modified by MidMike on 12/13/201015
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.WriterExporterOutput;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.devcoo.agencyflight.core.vaadin.factory.VaadinFactory;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;

/**
 * This class makes the work, loads the template, compile, fill and export the report.
 */
public abstract class ReportGenerator<Bean extends JRBean> {
    private Log log= LogFactory.getLog(this.getClass());
    private final static String BASE_REPORT_PATH = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"/WEB-INF";	
    public final static int PDF = 1;
    public final static int HTML = 2;
    public final static int EXCEL = 3;
    public final static int CSV = 4;
    
    private HashMap<String, Object> fillParameters=new HashMap<String, Object>();
    
    public ReportGenerator() {
    }

    private void executeReport(String templatePath, OutputStream outputStream, int exportType) throws JRException {

        JasperDesign jasperDesign=loadTemplate(templatePath);
        setTempDirectory(templatePath);
        JasperReport jasperReport=compileReport(jasperDesign);
        JasperPrint jasperPrint=fillReport(jasperReport);
        exportReportToType(jasperPrint, outputStream,exportType);
    }

    /**
     * Load the template (defined by templatePath) and return a JasperDesign object representing the template
     * @return JasperDesign
     */
    private JasperDesign loadTemplate(String templatePath){
        JasperDesign jasperDesign=null;
        File templateFile=new File(templatePath);
        System.out.println("ABSOLUTE PATH: "+templateFile.getAbsolutePath());
        if(templateFile.exists()){
            try {
                jasperDesign= JRXmlLoader.load(templateFile);
            } catch (JRException e) {
                log.error("Error in loading the template... "+e.getMessage());
            }
        }
        else
            log.error("Error, the file dont exists");
        return(jasperDesign);
    }

    /**
     * Compile the report and generates a binary version of it
     * @param jasperDesign The report design
     * @return JasperReport
     */
    private JasperReport compileReport(JasperDesign jasperDesign){
    	getFillParameters().remove("subreportParameter");
        JasperReport jasperReport = null;
        try {
        	JRBand[] jrBands = (JRBand []) jasperDesign.getDetailSection().getBands();
        	for(JRBand jrBand : jrBands) {
        		JRDesignBand jrDesignBand = (JRDesignBand) jrBand;
	        	JRElement[] jrElements = jrDesignBand.getElements();
	        	for (JRElement jrElement : jrElements) {
				    if (jrElement instanceof JRDesignSubreport) {
				    	//JasperReport jasperReportSub = null;
					    JRDesignSubreport jrDesignSubreportBeforeCompile = (JRDesignSubreport) jrElement;
					    //jrDesignBand.removeElement((JRDesignElement) jrElement);
					    JRExpression jrExpression = jrDesignSubreportBeforeCompile.getExpression();
					    //JRExpression dataSourceExpression = jrDesignSubreportBeforeCompile.getDataSourceExpression();				
					    String subReportSourceFile = jrExpression.getText();
					    String subReportFileName = subReportSourceFile.substring(19, subReportSourceFile.length());
					    String subReportDestFile = getPathTemplate() + subReportFileName.substring(0, subReportFileName.length()-8)+".jasper";
					    subReportSourceFile = getPathTemplate() + subReportFileName.substring(0, subReportFileName.length()-8)+".jrxml";
					    File templateFile = new File(subReportSourceFile);
					    JasperDesign jrDesignSubReport = JRXmlLoader.load(templateFile);
					    JasperCompileManager.compileReportToFile(jrDesignSubReport, subReportDestFile);
//					    JRDesignSubreport jrDesignSubreportAfterCompile = new JRDesignSubreport(jrDesignSubReport);
//					    jrDesignSubreportAfterCompile.setDataSourceExpression(dataSourceExpression);
//					    jrDesignSubreportAfterCompile.setExpression(jrExpression);;
//					    jrDesignBand.addElement(jrDesignSubreportAfterCompile);
				    }
	        	}
        	}
        	getFillParameters().put("SUBREPORT_DIR", getPathTemplate());
            jasperReport= JasperCompileManager.compileReport(jasperDesign);
        } catch (JRException e) {
            log.error("Error in compiling the report.... "+e.getMessage());
        }
        return(jasperReport);
    }

    /**
     * Fill the report and generates a binary version of it
     * @param jasperReport The Compiled report design
     * @return JasperPrint
     */
    private JasperPrint fillReport(JasperReport jasperReport){
        JasperPrint jasperPrint=null;
        JRDataSource datasource;
        List<Bean> data = getData();
        if(data.isEmpty()) {
        	datasource = new JREmptyDataSource();
        } else {
        	datasource = new JRBeanCollectionDataSource(data, true);
        }
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport,getFillParameters(),datasource);
            
        } catch (JRException e) {
            log.error("Error in filling the report..... "+e.getMessage());
        }
        return jasperPrint;
    }

    /**
     * Prepare a JRExporter for the filled report (to PDF)
     * @param jasperPrint The jasperPrint
     * @return The HTML text
     */
    private void exportReportToType(JasperPrint jasperPrint, OutputStream outputStream, int exportType) throws JRException {
        switch (exportType) {
		case PDF:
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			break;
		case CSV:
			///Still Got Error
			JRCsvExporter exporter1 = new JRCsvExporter(); 
			exporter1.setExporterOutput((WriterExporterOutput) new SimpleOutputStreamExporterOutput(outputStream));
			exporter1.setExporterInput(new SimpleExporterInput(jasperPrint));
	        exporter1.exportReport();
			break;
		case EXCEL:
			JRXlsxExporter exporter2 = new JRXlsxExporter();
			exporter2.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			exporter2.setExporterInput(new SimpleExporterInput(jasperPrint));
	        exporter2.exportReport();
			break;
		default:
			exportHTML(jasperPrint,outputStream);
			break;
		}
    }
    
    private void exportHTML(JasperPrint jasperPrint, OutputStream outputStream) {
    	JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false);
		exporter.setParameter(JRHtmlExporterParameter.OUTPUT_STREAM, outputStream);
		exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, jasperPrint);
//		SimpleHtmlExporterConfiguration shtmlconfiguration = new SimpleHtmlExporterConfiguration();
//		shtmlconfiguration.
//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
        try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Set the temp directory for report generation
     */
    private void setTempDirectory(String templatePath){
        File templateFile=new File(templatePath);
        if(templateFile.exists()){
            log.info("Setting parentDirectory: "+templateFile.getParent());
            System.setProperty("jasper.reports.compile.temp", templateFile.getParent());
        }
    }
    
	private StreamResource createResource(String reportFileName, final int exportType) {
		StreamResource sr = new StreamResource(new StreamResource.StreamSource() {
            @Override
            public InputStream getStream () {
                ByteArrayOutputStream pdfBuffer = new ByteArrayOutputStream();
                try {
                    executeReport(getPathTemplate() + getReportFileName(), pdfBuffer,exportType);
                } catch (JRException e) {
                    e.printStackTrace();
                }
                // Return a stream from the buffer.
                return new ByteArrayInputStream(
                        pdfBuffer.toByteArray());
            }
        }, reportFileName);
		sr.setCacheTime(-1);
        return sr;
    }
	
	public Button generateReportButton(String buttonText, String reportOutFileName, final int exportType) {
		Button btnPrint = VaadinFactory.getButton(buttonText);
        StreamResource myResource = createResource(reportOutFileName, exportType);
        BrowserWindowOpener opener = new BrowserWindowOpener(myResource);
        opener.extend(btnPrint);
        return btnPrint;
	}

	public abstract List<Bean> getData();

	public HashMap<String, Object> getFillParameters() {
		return fillParameters;
	}

	public void setFillParameters(HashMap<String, Object> fillParameters) {
		this.fillParameters = fillParameters;
	}
	
	private String getPathTemplate() {
		return BASE_REPORT_PATH + getSubPathTemplate() + "/";
	}
	
	public abstract String getSubPathTemplate();
	public abstract String getReportFileName();
    
}
