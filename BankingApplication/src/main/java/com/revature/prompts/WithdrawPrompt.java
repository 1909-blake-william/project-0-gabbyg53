package com.revature.prompts;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.daos.AccountDao;
import com.revature.daos.TransactionDao;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.util.AuthUtil;

public class WithdrawPrompt implements Prompt {

	private Logger log = Logger.getRootLogger();
	private AccountDao accountDao = AccountDao.currentImplemntation;
	private AuthUtil authUtil = AuthUtil.instance;
	private TransactionDao transDao = TransactionDao.currentImplementatin;
	private Scanner scan = new Scanner(System.in);
	
	@Override
	public Prompt run() {
		log.debug("attempting to withdraw from account");
		//	if (authUtil.getCurrentUser().getRole().equals("customer")) {
		System.out.println("Displaying all accounts for user");
		List<Account> allUserAccounts = accountDao.findByUsername(authUtil.getCurrentUser().getUsername());
		for (Account l: allUserAccounts) {
			System.out.println(l);
		}
		System.out.println("Use the listed IDs to select which account you would like withdraw from?");
		int aId = scan.nextInt();
		Account a = accountDao.findById(aId);
		
		System.out.println("Enter the amount to withdraw in format 00.00");

		float withdrawAmount = scan.nextFloat();
	
		a.setBalance(a.getBalance() - withdrawAmount);
		accountDao.withdraw(a, withdrawAmount);
			
		System.out.println("Displaying all accounts updated for user");
		Account updatedAcct = accountDao.findById(a.getId());
		System.out.println(updatedAcct);
		
		Transaction t = new Transaction(1, aId, authUtil.getCurrentUser().getId(), withdrawAmount, "withdraw");
		transDao.save(t);
		
		//}
		//log.debug("admin doesn't have account to deposit into");
		return new MainMenuPrompt();
	}

}
