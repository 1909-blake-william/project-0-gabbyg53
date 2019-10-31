package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDaoSQL implements UserDao {
	private Logger log = Logger.getRootLogger();
	
	User extractUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("bank_user_id");
		String role = rs.getString("user_type_id");
		String fname = rs.getString("f_name");
		String lname = rs.getString("l_name");
		String uname = rs.getString("username");
		String pw = rs.getString("u_password");
		String acctType = rs.getString("account_type");
		//int acctId = rs.getInt("b_account_id");
		
		return new User(id, role, fname, lname, uname, pw, acctType);
	}
	
	@Override
	public int save(User u) {
		log.debug("attempting to find user by credentials from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO Bank_User (bank_user_id, user_type_id, f_name, l_name, username, u_password, account_type) "
					+ "VALUES (BANK_USER_ID_SEQ.nextval,?,?,?,?,?,?)";
			
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, u.getRole());
			ps.setString(2, u.getFirstName());
			ps.setString(3, u.getLastName());
			ps.setString(4, u.getUsername());
			ps.setString(5, u.getPassword());
			ps.setString(6, u.getAccountType());
			//ps.setInt(7, u.getAccountId());
			
			return ps.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<User> findAll() {
		log.debug("attempting to find all users from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Bank_User";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				users.add(extractUser(rs));
			}
			return users;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findById(int id) {
		log.debug("attempting to find user by id from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Bank_User "
					//+ "LEFT JOIN Bank_Account ON (Bank_User.bank_user_id = Bank_Account.user_id) "
					//+ "WHERE Bank_User.bank_user_id = ? ";
					+ "WHERE bank_user_id = ?";
		
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		log.debug("attempting to find user by credentials from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Bank_User " + "WHERE username = ? AND u_password = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByUsername(String username) {
		log.debug("attempting to find user by its username from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM  Bank_User "
					+ "WHERE username = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByFirstNameAndLastName(String fname, String lname) {
		log.debug("attempting to find user by government name from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Bank_User " + "WHERE f_name = ? AND l_name = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, fname);
			ps.setString(2, lname);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> findByFirstName(String fname) {
		log.debug("attempting to find users by their first names from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Bank_User "
					+ "WHERE f_name = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, fname);

			ResultSet rs = ps.executeQuery();
			List<User> u = new ArrayList<>();
			while (rs.next()) {
				u.add(extractUser(rs));
			}
			return u;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> findByLastName(String lname) {
		log.debug("attempting to find users by their last names from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Bank_User "
					+ "WHERE l_name = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, lname);

			ResultSet rs = ps.executeQuery();
			List<User> u = new ArrayList<>();
			while (rs.next()) {
				u.add(extractUser(rs));
			}

			return u;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findRole(String role) {
		log.debug("attempting to find user by their role from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Bank_User "
					+ "WHERE user_type_id = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, role);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findAccountType(String acctType) {
		log.debug("attempting to find user by their account type from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Bank_User "
					+ "WHERE account_type = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, acctType);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> findAllAccounts (String uname) {
		log.debug("attempting to find all accounts of a certain user from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM Bank_Account "
					+ "LEFT JOIN Bank_User ON (Bank_Account.user_id = Bank_User.bank_user_id) "
					+ "WHERE Bank_User.username = ?";		
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, uname);

			ResultSet rs = ps.executeQuery();
			List<User> user = new ArrayList<>();
			while (rs.next()) {
				user.add(extractUser(rs));
			}
			return user;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
//	@Override
//	public List<User> findAllAccountType(String accTypes) {
//		log.debug("attempting to find all accounts of a certain user from DB");
//		try (Connection c = ConnectionUtil.getConnection()) {
//
//			String sql = "SELECT * FROM Bank_User "
//					+ "LEFT JOIN Bank_Account ON (Bank_User.b_account_id = Bank_Account.bank_account_id) "
//					+ "WHERE Bank_User.b_account_id = ?";
//			
//			PreparedStatement ps = c.prepareStatement(sql);
//
//			ResultSet rs = ps.executeQuery();
//			List<User> user = new ArrayList<>();
//			while (rs.next()) {
//				user.add(extractUser(rs));
//			}
//			return user;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	@Override
	public List<User> findAllTransactions(String uname) {
		log.debug("attempting to find all account transactions of a certain user from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

		//	String sql = "SELECT * FROM Account_Transaction "
			//			+ "WHERE user_trans_id = ?";
			
			String sql = "SELECT * FROM Account_Transaction "
			+ "LEFT JOIN Bank_User ON (Account_Transaction.user_trans_id = Bank_User.bank_user_id) "
			+ "WHERE Bank_User.username = ?";	
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, uname);
			
			ResultSet rs = ps.executeQuery();
			List<User> user = new ArrayList<>();
			while (rs.next()) {
				user.add(extractUser(rs));
			}
			return user;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
