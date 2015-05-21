package com.devcoo.agencyflight.fe.ui.panel.login;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.ui.login.AbstractLoginView;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.user.UserService;
import com.devcoo.agencyflight.fe.ui.MainUI;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@Component
@Scope("prototype")
@VaadinView(LoginPanel.NAME)
public class LoginPanel extends AbstractLoginView {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	UserService userManager = (UserService) ctx.getBean("userServiceImp");
	
	private static final long serialVersionUID = 6901879027592109979L;
	public static final String NAME = "";

	@Override
	protected void signIn(String user, String password) {
		if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(password)) {

			User log_user= userManager.getAuthoriseUser(user,password);
			if (log_user != null) {
				UI.getCurrent().getSession().setAttribute("isLogin", true);
				UI.getCurrent().getSession().setAttribute("log_user", log_user);
				Page.getCurrent().setUriFragment("!" + MainUI.AFTER_LOG_IN_PANEL_NAME);
			}
		}
		
		String msg = "<span>Welcome " + user + " with password: " + password + " to the agency flight</span>";
		Notification notification = new Notification("Welcome to Agency flight");
        notification.setDescription(msg);
        notification.setHtmlContentAllowed(true);
        notification.setDelayMsec(3000);
        notification.setStyleName("tray dark small closable login-help");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.show(Page.getCurrent());
	}

}