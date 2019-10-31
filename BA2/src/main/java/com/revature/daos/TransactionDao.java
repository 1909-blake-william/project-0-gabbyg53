package com.revature.daos;

import java.util.List;

import com.revature.models.Transaction;

public interface TransactionDao {
	TransactionDao currentImplementatin = new TransactionDaoSQL();
	
	int save(Transaction trans);

	List<Transaction> findAll();

	Transaction findById(int id);
	
	List<Transaction> findByAccountId(int acctId);
	
	List<Transaction> findByUserId (int userId);
}
