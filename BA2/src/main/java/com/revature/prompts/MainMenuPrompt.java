package com.revature.prompts;

import java.util.Scanner;
import com.revature.util.AuthUtil;

public class MainMenuPrompt implements Prompt {
	private Scanner scan = new Scanner(System.in);
	private AuthUtil authUtil = AuthUtil.instance;
	
	@Override
	public Prompt run() {
		System.out.println("Welcome to Banking Application " + authUtil.getCurrentUser() + ", please choose an option");
		System.out.println("Enter 1 to view account");
		System.out.println("Enter 2 to create account");
		System.out.println("Enter 3 to delete account");
		System.out.println("Enter 4 to deposit into account");
		System.out.println("Enter 5 to withdraw from account");
		System.out.println("Enter 6 to view transactions");
		System.out.println("Enter 7 to logout");
	
		
		// get user input
		String selection = scan.nextLine();

		switch (selection) {
		case "1":
			return new ViewAccountPrompt();
		case "2":
			return new CreateAccountPrompt();
		case "3":
			return new DeleteAccountPrompt();
		case "4":
			return new DepositPrompt();
		case "5":
			return new WithdrawPrompt();
		case "6":
			return new TransactionPrompt();
		case "7":
			return new LogoutPrompt();
		default:
			System.out.println("invalid selection, try again.");
			break;
		}
		return this;
	}

}
