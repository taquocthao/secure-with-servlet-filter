package com.tathao.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tathao.config.SecurityConfig;
import com.tathao.model.Role;
import com.tathao.model.UserAccount;
import com.tathao.utils.AppUtils;
import com.tathao.utils.SecurityUtils;
import com.tathao.utils.UserRoleRequestWrapper;

public class SecurityFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		System.out.println("Servlet Filter:: dofilter for request " + req.getContextPath());
		
		String servletPath = req.getServletPath();
		
		UserAccount loginedUser = AppUtils.getLoginedUser(req.getSession());
		
		if(servletPath.equals("/login")) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletRequest wrapRequest = req;
		
		if(loginedUser != null) {
			String username = loginedUser.getUsername();
			List<Role> roles = AppUtils.getUserRoles(req.getSession());
			
			wrapRequest = new UserRoleRequestWrapper(req, username, roles);
		}
		
//		page must be signed in
		if(SecurityUtils.isSecurityPage(req)) {
			
			// if the user not logged in
			if(loginedUser == null) {
				resp.sendRedirect(wrapRequest.getContextPath() + "/login");
				return;
			}
			
			boolean hasPermission = SecurityUtils.hasPermission(req);
			
			if(!hasPermission) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			
		}
		chain.doFilter(wrapRequest, resp);
	}

	@Override
	public void destroy() {
		
	}
	
}
