package com.devcoo.agencyflight.core.std;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.devcoo.agencyflight.core.country.Country;
import com.devcoo.agencyflight.core.product.visa.period.Period;
import com.devcoo.agencyflight.core.product.visa.type.VisaType;
import com.devcoo.agencyflight.core.startup.Register;
import com.devcoo.agencyflight.core.store.Store;
import com.devcoo.agencyflight.core.user.User;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;

@Component
public class ApplicationContext implements ApplicationContextAware {
    private static org.springframework.context.ApplicationContext context;
    public static final String WEB_CONTEXT = "WebContext";
	private static User log_user;
	private static List<Period> periods = new ArrayList<Period>();
	private static List<Country> coutries = new ArrayList<Country>();
	private static List<VisaType> visaTypies = new ArrayList<VisaType>();
	private static Store store = new Store();
	
    public static void setContext(
			org.springframework.context.ApplicationContext context) {
		ApplicationContext.context = context;
	}

	public static org.springframework.context.ApplicationContext getContext() {
        return context;
    }

	@Override
	public void setApplicationContext(
			org.springframework.context.ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
		Register.run();
		
	}
	
	public static User getLog_user() {
		return log_user;
	}
	
	public static void setLog_user(User log_user1) {
		log_user = log_user1;
	}
	
	public static void logOut() {
		UI.getCurrent().getSession().setAttribute("isLogin", false);
		((ApplicationContext) UI.getCurrent().getSession().getAttribute(ApplicationContext.WEB_CONTEXT)).setLog_user(null);
		Page.getCurrent().setUriFragment("!");
	}

	public static List<Period> getPeriods() {
		return periods;
	}

	public static void setPeriods(List<Period> periods) {
		ApplicationContext.periods = periods;
	}

	public static List<Country> getCoutries() {
		return coutries;
	}

	public static void setCoutries(List<Country> coutries) {
		ApplicationContext.coutries = coutries;
	}

	public static List<VisaType> getVisaTypies() {
		return visaTypies;
	}

	public static void setVisaTypies(List<VisaType> visaTypies) {
		ApplicationContext.visaTypies = visaTypies;
	}

	public static Store getStore() {
		return store;
	}

	public static void setStore(Store store) {
		ApplicationContext.store = store;
	}
}