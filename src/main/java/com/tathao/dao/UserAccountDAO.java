package com.tathao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tathao.model.UserAccount;

public class UserAccountDAO {

	private static final String SQL_FIND_ACCOUNT = "select * from account "
												+ "where username = ? and password = ?";
	private Connection connection;
	
	public UserAccountDAO() throws ClassNotFoundException, SQLException {
		this.connection = DataProvider.getConnection();
	}
	
	public UserAccount findUser(String username, String password) throws SQLException {
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(SQL_FIND_ACCOUNT);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		
		ResultSet result = preparedStatement.executeQuery();
		while(result.next()) {
			int id = result.getInt(1);
			String uname = result.getString(2);
			String pass = result.getString(3);
			return new UserAccount(id, uname,pass);
		}
		preparedStatement.close();
		this.connection.close();
		return null;
	}
	
}
