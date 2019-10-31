package com.revature.prompts;

import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.models.Account;
import com.revature.util.AuthUtil;

public class CreateAccountPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private AccountDao accountDao = AccountDao.currentImplemntation;
	private AuthUtil authUtil = AuthUtil.instance;
	
	@Override
	public Prompt run() {
		System.out.println("Enter whether account will be 'savings' or 'checkings'");
		String acctType = scan.nextLine();
		
		System.out.println("Beginning balance is 0.00$");
		float startAmount = 0.00f;
		
		System.out.println("Is this account active? (0 for inactive, 1 for active)");
		int open = scan.nextInt();
		
		Account a = new Account(1, acctType, authUtil.getCurrentUser().getId(), startAmount,open);
		
		accountDao.save(a);
		System.out.println("New account:  " + a + " created");
		return new MainMenuPrompt();
	}

}
