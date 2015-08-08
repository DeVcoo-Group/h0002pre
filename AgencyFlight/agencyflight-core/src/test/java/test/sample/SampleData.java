package test.sample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.product.visa.period.PeriodService;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeService;
import com.devcoo.agencyflight.core.supplier.SupplierService;

public class SampleData {
	
	public static ApplicationContext ctx;
	
	public static void main(String[] args) {
		ctx = new ClassPathXmlApplicationContext("spring.xml");
		CountryService countryService = (CountryService) ctx.getBean("countryServiceImp");
		PeriodService periodService = (PeriodService) ctx.getBean("periodServiceImp");
		VisaTypeService visaTypeService = (VisaTypeService) ctx.getBean("visaTypeServiceImp");
		SupplierService supplierService = (SupplierService) ctx.getBean("supplierServiceImp");
		
		System.out.println("Start create Sample");
		SampleCountry.createSample(countryService);
		SamplePeriod.createSample(periodService);
		SampleVisaType.createSample(visaTypeService);
		SampleSupplier.createSample(supplierService);
		System.out.println("End create Sample");
	}

}
