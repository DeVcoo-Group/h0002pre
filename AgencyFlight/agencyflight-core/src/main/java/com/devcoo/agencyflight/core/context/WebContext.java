package com.devcoo.agencyflight.core.context;

import com.devcoo.agencyflight.core.user.User;

public class WebContext {
	public static final String WEB_CONTEXT = "WebContext";
	private User log_user;
	public User getLog_user() {
		return log_user;
	}
	public void setLog_user(User log_user) {
		this.log_user = log_user;
	}
	
}
