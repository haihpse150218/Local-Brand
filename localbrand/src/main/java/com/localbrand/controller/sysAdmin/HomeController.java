package com.localbrand.controller.sysAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.Brand;
import com.localbrand.entities.BrandAccount;
import com.localbrand.entities.SystemAccount;
import com.localbrand.service.implement.SystemAdminService;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns ="/sysAdmin/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
		String action = request.getAttribute("action").toString();
		System.out.println("actione ne 2:" + action);
		switch (action) {
		case "index":
			index(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		default:
			request.setAttribute("controller", "/error");
			request.setAttribute("action", "index");
		}
		}catch (Exception e) {
			System.out.println("Error at Home Controller");
			e.printStackTrace();
		}
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("sysAdmin");
		request.setAttribute("controller", "/login");
		request.setAttribute("action", "index");
		return;
	}

	private void index(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		SystemAccount sysAdmin = (SystemAccount) session.getAttribute("sysAdmin");
		SystemAdminService sas = new SystemAdminService();
		if (sysAdmin != null) {
			List<Brand> listb = sas.brandList();
			List<BrandAccount> listbAcc = sas.brandAdminList();
			
			System.out.println("brandList "+ listb);
			System.out.println("brandAccountList "+ listbAcc);
			
			request.setAttribute("brandList", listb);
			request.setAttribute("brandAccountList", listbAcc);
					
			request.setAttribute("controller", "/home");
			request.setAttribute("action", "index");
			
			
		}else {
			request.setAttribute("controller", "/login");
			request.setAttribute("action", "index");
		}
		
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
