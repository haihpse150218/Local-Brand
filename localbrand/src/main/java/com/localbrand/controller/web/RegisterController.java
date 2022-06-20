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
@WebServlet(urlPatterns = "/web/register")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getAttribute("action").toString();
		switch (action) {
		case "index":
			System.out.println("73"+action);
			index(request, response);
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

	
	private void index(HttpServletRequest request, HttpServletResponse response) {
		
		
	}


	private void register(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		// PARAMETERS
		String newUsername 	= request.getParameter("txtUserName").toString().trim();
		String newName 		= request.getParameter("txtName").toString().trim();
		String newEmail 	= request.getParameter("txtEmail").toString().trim();
		String newPhone 	= request.getParameter("txtPhone").toString().trim();
		String newPassword 	= request.getParameter("txtPassword").toString().trim();
		
		// SERVICE
		RegisterService registerService = new RegisterService();
		try {
			Customer newUser = new Customer();
			newUser.setUsername(newUsername);
			newUser.setName(newName);
			newUser.setEmail(newEmail);
			newUser.setPhone(newPhone);
			newUser.setPassword(newPassword);
			
			registerService.createUser(newUser);
			
			session.setAttribute("user", newUser);
			
			System.out.println("REGISTER SUCCESS" + newUser.getName());
		} catch (Exception e) {
			request.setAttribute("REGISTER_ERROR", e.getMessage());
		}
		
		String uri = (String)session.getAttribute("uri");
		request.setAttribute("controller", uri);
		request.setAttribute("action", "index");
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
