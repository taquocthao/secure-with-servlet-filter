package com.tathao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {

	public static Connection getMySqlConnect() throws ClassNotFoundException, SQLException {
		String hostname = "localhost";
		String databaseName = "secure_with_filter";
		String username = "root";
		String password = "";
		return getMySqlConnect(hostname, databaseName, username, password);
	}
	
	private static Connection getMySqlConnect(String hostname, String databaseName ,
			String username, String password) throws SQLException, ClassNotFoundException {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connectURL = "jdbc:mysql://" + hostname + ":3306/" + databaseName  + 
					"?useUnicode=yes&characterEncoding=UTF-8";
			Connection connect = DriverManager.getConnection(connectURL, username, password);
			
			return connect;
	}
	
}
