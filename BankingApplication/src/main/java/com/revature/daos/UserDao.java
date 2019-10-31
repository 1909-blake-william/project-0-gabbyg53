package com.revature.daos;

import java.util.List;
import com.revature.models.User;


public interface UserDao {
	UserDao currentImplementation = new UserDaoSQL();
	
	int save(User u);

	List<User> findAll();

	User findById(int id);

	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);
	
	User findByFirstNameAndLastName(String fname, String lname);
	
	List<User> findByFirstName(String fname);
	
	List<User> findByLastName(String lname);
	
	User findRole(String role);
	
	User findAccountType(String acctType);
	
	List<User> findAllAccounts (String uname);
	
	//List<User> findAllAccountType(String accTypes);
	
	List<User> findAllTransactions(String uname);
	
}
