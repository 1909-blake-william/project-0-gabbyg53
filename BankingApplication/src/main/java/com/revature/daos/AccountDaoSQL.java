package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.Account;
//import com.revature.models.Transaction;
import com.revature.util.ConnectionUtil;
import org.apache.log4j.Logger;

public class AccountDaoSQL implements AccountDao {
	private Logger log = Logger.getRootLogger();
	
	Account extractAccount(ResultSet rs) throws SQLException {
		int id = rs.getInt("bank_account_id");
		String accountType = rs.getString("account_type");
		int uId = rs.getInt("user_id");
		float balance = rs.getFloat("balance");
		int open = rs.getInt("is_open");
		return new Account(id, accountType, uId, balance, open);
	}
	
	@Override
	public int save(Account account) {
		log.debug("attempting to find user by credentials from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO Bank_Account (bank_account_id, account_type, user_id, balance, is_open) "
					+ "VALUES (BANK_ACCOUNT_ID_SEQ.nextval, ?, ?, ?, ?)";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, account.getAccountType());
			ps.setInt(2,account.getUserId());
			ps.setFloat(3, account.getBalance());
			ps.setInt(4, account.getIsOpen());
			
			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public List<Account> findAll() {
		log.debug("attempting to find all accounts from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Bank_Account ";
					//+ "LEFT JOIN Bank_User ON (Bank_Account.user_id = Bank_User.bank_user_id)";
			
			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Account> accounts = new ArrayList<>();
			while (rs.next()) {
				accounts.add(extractAccount(rs));
			}

			return accounts;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account findById(int id) {
		log.debug("attempting to find account by id from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Bank_Account " + "WHERE bank_account_id = ? ";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractAccount(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Account> findByUsername(String uname) {
		log.debug("attempting to find account by its username from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM  Bank_Account "
					+ "LEFT JOIN Bank_User ON (Bank_Account.user_id = bank_user.bank_user_id) "
					+ "WHERE username = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, uname);

			ResultSet rs = ps.executeQuery();
			List<Account> a = new ArrayList<>();
			while (rs.next()) {
				a.add(extractAccount(rs));
			}
			return a;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public float deposit(Account a, float f) {
	//public float deposit(Account a, Transaction t) {
		log.debug("attempting to deposit into account in DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "UPDATE Bank_Account SET balance = ? "
						+ "WHERE bank_account_id = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			
			float newBalance = a.getBalance() + f;
			
			ps.setObject(1, newBalance);
			ps.setObject(2, a.getId());
			
			ps.executeUpdate();
			
			a.setBalance(newBalance);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return a.getBalance();
	}

	@Override
	public float withdraw(Account a, float f) {
		log.debug("attempting to withdraw into account in DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "UPDATE Bank_Account SET balance = ? "
						+ "WHERE bank_account_id = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			
			
			float newBalance = a.getBalance() - f;
			
			ps.setObject(1, newBalance);
			ps.setObject(2, a.getId());
			
			ps.executeUpdate();
			
			a.setBalance(newBalance);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return a.getBalance();
	}

	@Override
	public void removeAccountInfo(Account a, int isClosed) {
		log.debug("attempting to find account by id and disactive it from DB");
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "UPDATE Bank_Account "
						+ "SET balance = ?, is_open = ? "
						+ "WHERE bank_account_id = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			
			float inactiveBalance = 0.00f;
			
			ps.setObject(1, inactiveBalance);
			ps.setObject(2, isClosed);
			ps.setObject(3, a.getId());
			
			ps.executeUpdate();
			
			a.setBalance(inactiveBalance);
			a.setIsOpen(isClosed);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
