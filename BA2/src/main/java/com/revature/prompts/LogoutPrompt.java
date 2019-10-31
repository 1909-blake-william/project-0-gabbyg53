package com.revature.prompts;

import org.apache.log4j.Logger;
import com.revature.models.User;
import com.revature.util.AuthUtil;

public class LogoutPrompt implements Prompt {

	private Logger log = Logger.getRootLogger();
	private AuthUtil authUtil = AuthUtil.instance;
	
	@Override
	public Prompt run() {
		log.debug("attempting to logout");
		
		User u = authUtil.logout();
		if (u != null) {
			log.info("failed to logout");
			System.out.println("Invalid Credentials");
			return new MainMenuPrompt();
		}
		else {
			log.info("successfully logged out");
			return new LoginPrompt();
		}
	}

}
