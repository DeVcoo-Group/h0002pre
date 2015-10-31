package com.devcoo.agencyflight.fe.ui.panel.login;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ru.xpoft.vaadin.VaadinView;

import com.devcoo.agencyflight.core.std.ApplicationContext;
import com.devcoo.agencyflight.core.ui.login.AbstractLoginView;
import com.devcoo.agencyflight.core.user.User;
import com.devcoo.agencyflight.core.user.UserService;
import com.devcoo.agencyflight.fe.ui.MainUI;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;

@Component
@Scope("prototype")
@VaadinView(LoginPanel.NAME)
public class LoginPanel extends AbstractLoginView {
	UserService userService= (UserService)ApplicationContext.getContext().getBean("userServiceImp");
	
	private static final long serialVersionUID = 6901879027592109979L;
	public static final String NAME = "";

	@Override
	protected void signIn(String user, String password) {
		if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(password)) {
			List<User> list_log_user= userService.findUser(user,password);
			if (!list_log_user.isEmpty()) {
				UI.getCurrent().getSession().setAttribute("isLogin", true);
				ApplicationContext.setLog_user(list_log_user.get(0));
				Page.getCurrent().setUriFragment("!" + MainUI.AFTER_LOG_IN_PANEL_NAME);
			}
		}
	}

}
