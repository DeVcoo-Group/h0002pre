package com.devcoo.agencyflight.core.std;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.devcoo.agencyflight.core.context.WebContext;
import com.devcoo.agencyflight.core.user.User;
import com.vaadin.ui.UI;
public abstract class StdServiceImp<SampleRepository extends StdDao<T>, T extends StdEntity> implements StdService<T>{
	@Autowired
	@Resource
	protected SampleRepository dao;
	protected WebContext context;
	
	public void save(T entity) {
		entity.setModifyDate(new Date());
		entity.setCreateDate(new Date());
		entity.setActive(true);
		entity.setLastModifier(getLogUser());
		dao.save(entity);
	}
	
	public void update(T entity) {
		entity.setModifyDate(new Date());
		entity.setLastModifier(getLogUser());
		dao.save(entity);
	}
	
	public void delete(T entity) {
		entity.setActive(false);
		entity.setLastModifier(getLogUser());
		dao.save(entity);
	}
	public User getLogUser() {
		context = (WebContext) UI.getCurrent().getSession().getAttribute(WebContext.WEB_CONTEXT);
		if(context == null) {
			context = new WebContext();
		}
		if(context.getLog_user() == null) {
			context.setLog_user(new User());
		}
		return context.getLog_user();
	}
}
