package com.localbrand.controller.sysAdmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.controller.admin.Common;
import com.localbrand.entities.BrandAccount;
import com.localbrand.entities.SystemAccount;
import com.localbrand.service.implement.LoginAdmin;
import com.localbrand.service.implement.SystemAdminService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(urlPatterns="/sysAdmin/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getAttribute("action").toString();
		System.out.println("actione ne:"+action);
		switch (action) {
		case "index":
			index(request, response);
			request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
			break;
		case "login":
			login(request, response);
			request.getRequestDispatcher("/admin/home").include(request, response);
			break;
		default:
			request.setAttribute("controller", "/error");
			request.setAttribute("action", "index");			
		}
		
		
		return;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		//String encodePass = LoginAdmin.getInstance().encodePass(password);
        
		//wating database
		SystemAccount sysAdmin = SystemAdminService.getInstance().CheckAccount(username, password);
		if(sysAdmin!=null) {
			session.setAttribute("sysAdmin", sysAdmin);	
			request.setAttribute("page", "/sysAdmin");
			request.setAttribute("controller", "/home");
			request.setAttribute("action", "index");	
			return;
		}
		else {
			
			request.setAttribute("mess", "Wrong Username or Password!!!");
			request.setAttribute("page", "/sysAdmin");
			request.setAttribute("controller", "/login");
			request.setAttribute("action", "index");	
		}
    }
    private void index(HttpServletRequest request, HttpServletResponse response) {		
		System.out.println("hien thi form");
	}
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
