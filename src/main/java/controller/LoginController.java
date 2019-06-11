package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tathao.dao.RoleDAO;
import com.tathao.dao.UserAccountDAO;
import com.tathao.model.Role;
import com.tathao.model.UserAccount;
import com.tathao.utils.AppUtils;


public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccountDAO managerAccount;
	private RoleDAO managerRole;
	
    public LoginController() {
        super();
        try { 	
			managerAccount = new UserAccountDAO();
			managerRole = new RoleDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/loginPage.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " - " + password);
		if(username.isEmpty() || password.isEmpty()) {
			request.setAttribute("messageError", "please enter username and password");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/loginPage.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		try {
			
			UserAccount account = managerAccount.findUser(username, password);
			if(account != null) {
				
				List<Role> listRoles = managerRole.findUserRoleById(account.getId());
//				stored user and role in session
				AppUtils.storeLoginedUser(request.getSession(), account);
				AppUtils.storedUserRole(request.getSession(), listRoles);
				
				System.out.println(listRoles);
				
				response.sendRedirect(request.getContextPath() + "/userInfo");
				
			} else {
				request.setAttribute("messageError", "invailid username or password");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/loginPage.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

}
