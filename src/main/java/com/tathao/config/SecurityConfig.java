package com.tathao.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {
	
	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_USER = "USER";
	
	private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();
	
	// static block , it call when class has been called
	static {
		init();
	}
	
	private static void init() {
		
		List<String> urlPatternUser = new ArrayList<String>();	
		urlPatternUser.add("/userInfo");
		urlPatternUser.add("/userTask");
		mapConfig.put(ROLE_USER, urlPatternUser);
		
		List<String> urlPatternAdmin = new ArrayList<String>();
		urlPatternAdmin.add("/userInfo");
		urlPatternAdmin.add("/adminTask");
		mapConfig.put(ROLE_ADMIN, urlPatternAdmin);
		
	}
	
	public static Set<String> getAllAppRoles(){
		return mapConfig.keySet();
	}
	
	public static List<String> getUrlPatternsForRole(String role){
		return mapConfig.get(role);
	}
	
	
}
