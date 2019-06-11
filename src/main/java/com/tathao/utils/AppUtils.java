package com.tathao.utils;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.tathao.model.Role;
import com.tathao.model.UserAccount;

public class AppUtils {

	public static void storeLoginedUser(HttpSession session, UserAccount loginedUser ) {
		session.setAttribute("loginedUser", loginedUser);
	}
	
	public static void storedUserRole(HttpSession session, List<Role> roles) {
		session.setAttribute("userRoles", roles);
	}
	
	public static UserAccount getLoginedUser(HttpSession session) {
		UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
		return loginedUser;
	}
	
	public static List<Role> getUserRoles(HttpSession session) {
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>) session.getAttribute("userRoles");
		return roles;
	}
}
