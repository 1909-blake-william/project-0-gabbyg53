package com.revature.prompts;

import java.util.List;
import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.models.Account;
import com.revature.util.AuthUtil;

public class DeleteAccountPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private AccountDao accountDao = AccountDao.currentImplemntation;
	private AuthUtil authUtil = AuthUtil.instance;
	
	@Override
	public Prompt run() {
		
		if (authUtil.getCurrentUser().getRole().equals("admin")) {
			System.out.println("Admin attempting to delete an inactive account");
			System.out.println();
			System.out.println("List of all users' accounts");
			
			List<Account> allAccounts = accountDao.findAll();
			for(Account a: allAccounts) {
				System.out.println(a);
			}
			
			System.out.println("Use the listed IDs to select which account you would like to disactivate?");
			int aId = scan.nextInt();
			Account a = accountDao.findById(aId);
			
			System.out.println("Accounts with '0' for isOpen field is inactive");
			//a.setIsOpen(0);
			
			accountDao.removeAccountInfo(a,0);
			
			System.out.println(a + " is now inactive indefinitly");
			
			return new MainMenuPrompt();
		}
		
		else {
			System.out.println("A customer cannot delete their own account, see Administator for assistance");
			return new MainMenuPrompt();
		}
	}

}
