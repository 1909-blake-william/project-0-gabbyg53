package com.revature.prompts;

import java.util.List;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.util.AuthUtil;

public class ViewAccountPrompt implements Prompt {

	private AccountDao accountDao = AccountDao.currentImplemntation;
	private UserDao userDao = UserDao.currentImplementation;
	private AuthUtil authUtil = AuthUtil.instance;
	
	@Override
	public Prompt run() {
		
		if (authUtil.getCurrentUser().getRole().equals("admin")) {
			System.out.println("List of all User accounts");
			List<Account> allAccounts = accountDao.findAll();
			for(Account a: allAccounts) {
				System.out.println(a);
			}
			
			System.out.println("List of all Users");
			List<User> allUsers = userDao.findAll();
			for(User u: allUsers) {
				System.out.println(u);
			}
			
			
			return new MainMenuPrompt();
		}
		else	{
			List<User> allUserAccounts = userDao.findAllAccounts(authUtil.getCurrentUser().getUsername());
			for (User u: allUserAccounts) {
				System.out.println(u);
			}
			return new MainMenuPrompt();
		}
	}

}
