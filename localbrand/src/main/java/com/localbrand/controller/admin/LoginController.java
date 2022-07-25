package com.localbrand.controller.admin;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.BrandAccount;
import com.localbrand.service.implement.HomeAdmin;
import com.localbrand.service.implement.LoginAdmin;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(name = "LoginControllerAdmin", urlPatterns = { "/admin/login" })
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
    

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		String encodePass = LoginAdmin.getInstance().encodePass(password);
        
		//wating database
		BrandAccount admin = LoginAdmin.getInstance().CheckAccount(username, encodePass);
		if(admin!=null) {
			session.setAttribute("admin", admin);	
			request.setAttribute("page", "/admin");
			request.setAttribute("controller", "/home");
			request.setAttribute("action", "index");	
			return;
		}
		else {
			
			request.setAttribute("mess", "Wrong Username or Password!!!");
			request.setAttribute("page", "/admin");
			request.setAttribute("controller", "/login");
			request.setAttribute("action", "index");	
		}
		
	}
	private void index(HttpServletRequest request, HttpServletResponse response) {		
		System.out.println("hien thi form");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 processRequest( request,  response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 processRequest(request, response);
	}

}
