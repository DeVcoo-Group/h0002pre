package com.devcoo.agencyflight.core.startup;

import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.product.visa.period.PeriodService;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeService;
import com.devcoo.agencyflight.core.std.ApplicationContext;


public class Register {

	private static VisaTypeService visaTypeService;
	private static CountryService countryService;
	private static PeriodService periodService;
	
	public static void run() {
		
		countryService = (CountryService) ApplicationContext.getContext().getBean("countryServiceImp");
		periodService = (PeriodService) ApplicationContext.getContext().getBean("periodServiceImp");
		visaTypeService = (VisaTypeService) ApplicationContext.getContext().getBean("visaTypeServiceImp");
		ApplicationContext.setCoutries(countryService.findAllNotDelete());
		ApplicationContext.setPeriods(periodService.findAllNotDelete());
		ApplicationContext.setVisaTypies(visaTypeService.findAllNotDelete());
	}

}
