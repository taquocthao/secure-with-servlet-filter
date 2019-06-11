package com.tathao.utils;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.tathao.model.Role;

public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

	private String user;
	private List<Role> roles;
	private HttpServletRequest realRequest;
	
	public UserRoleRequestWrapper(HttpServletRequest request, String user, List<Role> roles) {
		super(request);
		this.user = user;
		this.roles = roles;	
		this.realRequest = request;
	}
	
	@Override
	public boolean isUserInRole(String role) {
		if(role == null) {
			return this.realRequest.isUserInRole(role);
		}
		// convert List<Role> to List<String> (Role -> String)
		List<String> listRolesConverted = new ArrayList<String>();
		for(Role r : this.roles) {
			listRolesConverted.add(r.getRoleName());
		}
		return listRolesConverted.contains(role);
	}
	
	@Override
	public Principal getUserPrincipal() {
		
		if(this.user == null)
			return realRequest.getUserPrincipal();
		return new Principal() {
			@Override
			public String getName() {
				return user;
			}
		};
	}
	
}
