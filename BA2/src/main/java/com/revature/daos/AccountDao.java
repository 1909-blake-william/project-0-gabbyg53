package com.revature.daos;

import java.util.List;

import com.revature.models.Account;

public interface AccountDao {

	AccountDao currentImplemntation = new AccountDaoSQL();

	int save(Account account);

	List<Account> findAll();

	Account findById(int id);

	List<Account> findByUsername(String name);
	
	float deposit(Account a, float f);
	
	float withdraw(Account a, float f);
	
	void removeAccountInfo(Account a, int isClosed);
}
