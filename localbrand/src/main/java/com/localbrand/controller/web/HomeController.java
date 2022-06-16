package com.localbrand.controller.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.BrandCategory;
import com.localbrand.entities.BrandCategoryPK;
import com.localbrand.entities.Customer;
import com.localbrand.service.implement.LoginService;
import com.localbrand.service.implement.RegisterService;
import com.localbrand.sessionbeans.BrandCategoryFacade;
import com.localbrand.sessionbeans.CustomerFacade;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns = "/web/home")
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getAttribute("action").toString();
		switch (action) {
		case "index":
			index(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "register":
			register(request, response);
			break;
		default:
			request.setAttribute("controller", "error");
			request.setAttribute("action", "index");
		}
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 
		 // PARAMETERS
		 String username = request.getParameter("txtusername").toString();
		 String password = request.getParameter("txtpassword").toString();
		 
		 Customer loginCustomer = null;
		 LoginService loginService = new LoginService();
		 
		 try {
			 loginCustomer = loginService.loginByUsername(username, password);
			 
			 session.setAttribute("user", loginCustomer);
			 
			 System.out.println("LOGIN SUCCESS");
		 } catch (Exception e) {
			 request.setAttribute("LOGIN_ERROR", e.getMessage());
		 }
		 
		// set lai aciton de no van o lai trang cu
		 String uri = (String)session.getAttribute("uri");
		 request.setAttribute("controller", uri);
		 request.setAttribute("action", "index");
		 //session.removeAttribute("uri");
		 //request.getRequestDispatcher(uri + "/index.do").forward(request, response);
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		// PARAMETERS
		String NewUsername 	= request.getParameter("username").toString().trim();
		String NewName 		= request.getParameter("name").toString().trim();
		String NewEmail 	= request.getParameter("email").toString().trim();
		String NewPhone 	= request.getParameter("phone").toString().trim();
		String NewPassword 	= request.getParameter("password").toString().trim();
		
		// SERVICE
		RegisterService registerService = new RegisterService();
		try {
			Customer NewUser = new Customer();
			NewUser.setUsername(NewUsername);
			NewUser.setName(NewName);
			NewUser.setEmail(NewEmail);
			NewUser.setPhone(NewPhone);
			NewUser.setPassword(NewPassword);
			
			registerService.createUser(NewUser);
			
			session.setAttribute("user", NewUser);
			
			System.out.println("REGISTER SUCCESS");
		} catch (Exception e) {
			request.setAttribute("REGISTER_ERROR", e.getMessage());
		}
		
		String uri = (String)session.getAttribute("uri");
		request.setAttribute("controller", uri);
		request.setAttribute("action", "index");
	}

	private void index(HttpServletRequest request, HttpServletResponse response) {
		//set uri
		HttpSession session = request.getSession();
		String uri = request.getServletPath();
        String controller = uri.substring(uri.lastIndexOf("/"));
        session.setAttribute("uri", controller);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
