package com.revature.driver;

import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class ManualTestDriver {
	public static void main(String[] args) {
		UserDao userDao = UserDao.currentImplementation;
		AccountDao accountDao = AccountDao.currentImplemntation;
		
		
		System.out.println("find all accounts");
		accountDao.findAll().forEach(account -> {
			System.out.println(account);
		});
		
		String g = "crlv";
		System.out.println("find all accounts of username");
		userDao.findAllAccounts(g).forEach(user -> {
			System.out.println(user);
		});
		
//		System.out.println("find user by first name and last name");
//		String f = "Gabrielle";
//		String l = "Griggs";
//		System.out.println("user: " + f +" " + l);
//////	
//		((Iterable<Object>) userDao.findByFirstNameAndLastName(f, l)).forEach(user -> {
//			System.out.println(user);
//		});
		
	}
}
