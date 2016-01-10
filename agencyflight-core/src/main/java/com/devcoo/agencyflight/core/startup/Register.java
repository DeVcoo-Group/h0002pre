package com.devcoo.agencyflight.core.startup;

import java.util.List;

import com.devcoo.agencyflight.core.country.CountryService;
import com.devcoo.agencyflight.core.product.visa.period.PeriodService;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeService;
import com.devcoo.agencyflight.core.std.ApplicationContext;
import com.devcoo.agencyflight.core.store.Store;
import com.devcoo.agencyflight.core.store.StoreService;


public class Register {

	private static VisaTypeService visaTypeService;
	private static CountryService countryService;
	private static PeriodService periodService;
	private static StoreService storeService;
	
	public static void run() {
		
		countryService = (CountryService) ApplicationContext.getContext().getBean("countryServiceImp");
		periodService = (PeriodService) ApplicationContext.getContext().getBean("periodServiceImp");
		visaTypeService = (VisaTypeService) ApplicationContext.getContext().getBean("visaTypeServiceImp");
		storeService = (StoreService) ApplicationContext.getContext().getBean("storeServiceImp");
		ApplicationContext.setCoutries(countryService.findAllNotDelete());
		ApplicationContext.setPeriods(periodService.findAllNotDelete());
		ApplicationContext.setVisaTypies(visaTypeService.findAllNotDelete());
		List<Store> listStore = storeService.findAllNotDelete();
		if(!listStore.isEmpty())
			ApplicationContext.setStore(listStore.get(0));
	}

}
