package com.tathao.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.tathao.utils.MySQLConnector;

public class DataProvider {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MySQLConnector.getMySqlConnect();
	}
	
	
}
