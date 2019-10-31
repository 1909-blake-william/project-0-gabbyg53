package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.revature.models.Transaction;
import com.revature.util.ConnectionUtil;

public class TransactionDaoSQL implements TransactionDao {
	private Logger log = Logger.getRootLogger();

	Transaction extractTransaction(ResultSet rs) throws SQLException {
		int id = rs.getInt("transaction_id");
		int accId = rs.getInt("account_id");
		int uId = rs.getInt("user_trans_id");
		float amount = rs.getFloat("amount");
		String action = rs.getString("action");
		//timestamp trans_date
		return new Transaction(id, accId, uId, amount, action);
	}
	
	@Override
	public int save(Transaction trans) {
		log.debug("attempting to find transaction by credentials from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO Account_Transaction (transaction_id, account_id, user_trans_id, amount, action) "
					+ "VALUES (ACCOUNT_TRANSACTION_ID_SEQ.nextval, ?, ?, ?, ?)";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, trans.getAccountId());
			ps.setInt(2, trans.getUserId());
			ps.setFloat(3, trans.getAmount());
			ps.setString(4, trans.getAction());
			
			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Transaction> findAll() {
		log.debug("attempting to find all transactions from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Account_Transaction";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Transaction> trans = new ArrayList<>();
			while (rs.next()) {
				trans.add(extractTransaction(rs));
			}
			return trans;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Transaction findById(int id) {
		log.debug("attempting to find transaction by id from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Account_Transaction " + "WHERE transaction_id = ? ";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractTransaction(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Transaction> findByAccountId(int acctId) {
		log.debug("attempting to find transaction by account id from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

		String sql = "SELECT * FROM Account_Transaction "
				+ "LEFT JOIN Bank_Account ON (Account_Transaction.account_id = Bank_Account.account_id) "
				+ "WHERE account_id = ?";
		
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, acctId);

			ResultSet rs = ps.executeQuery();
			List<Transaction> t = new ArrayList<>();
			while (rs.next()) {
				t.add(extractTransaction(rs));
			}
			return t;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Transaction> findByUserId(int userId) {
		log.debug("attempting to find transaction by user id from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Account_Transaction "
				+ "LEFT JOIN Bank_User ON (Account_Transaction.user_trans_id = Bank_User.bank_user_id) "
				+ "WHERE user_trans_id = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			List<Transaction> t = new ArrayList<>();
			while (rs.next()) {
				t.add(extractTransaction(rs));
			}
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
