package com.tathao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tathao.model.Role;

public class RoleDAO {

	private static final String SQL_GET_USER_ROLES = "select r.id, r.role_name "
										+ "from role r, account a, user_role u "
										+ "where u.role_id = r.id and u.account_id = ?";
	
	private Connection connection;
	
	public RoleDAO() throws ClassNotFoundException, SQLException {
		this.connection = DataProvider.getConnection();
	}
	
	public List<Role> findUserRoleById(int accountId) throws SQLException{
		
		PreparedStatement preStatement = this.connection.prepareStatement(SQL_GET_USER_ROLES);
		preStatement.setInt(1, accountId);
		ResultSet result = preStatement.executeQuery();
		List<Role> listRole = new ArrayList<Role>();
		while(result.next()) {
			int roleId = result.getInt(1);
			String roleName = result.getString(2);
			listRole.add(new Role(roleId, roleName));
		}
		return listRole;
	}
	
}
