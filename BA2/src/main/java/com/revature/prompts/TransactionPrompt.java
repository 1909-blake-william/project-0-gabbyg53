package com.revature.prompts;

import java.util.List;

//import com.revature.daos.AccountDao;
import com.revature.daos.TransactionDao;
import com.revature.daos.UserDao;
//import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.util.AuthUtil;

public class TransactionPrompt implements Prompt {

	//private AccountDao accountDao = AccountDao.currentImplemntation;
	private UserDao userDao = UserDao.currentImplementation;
	private AuthUtil authUtil = AuthUtil.instance;
	private TransactionDao transDao = TransactionDao.currentImplementatin;
	
	@Override
	public Prompt run() {
	
		if (authUtil.getCurrentUser().getRole().equals("admin")) {
			System.out.println("View all transactions from all users' accounts");
			List<Transaction> allTrans = transDao.findAll();
			for(Transaction t: allTrans) {
				System.out.println(t);
			}
			
			return new MainMenuPrompt();
		}
		else {
			System.out.println("View all transactions from current user's accounts");
//			List<User> userAccTrans = userDao.findAllTransactions(authUtil.getCurrentUser().getUsername());
//			for (User u: userAccTrans) {
//				System.out.println(u);
//			}
			List<Transaction> userAccTrans = transDao.findByUserId(authUtil.getCurrentUser().getId());
			for (Transaction t: userAccTrans) {
				System.out.println(t);
			}
			return new MainMenuPrompt();
		}
	}
}
