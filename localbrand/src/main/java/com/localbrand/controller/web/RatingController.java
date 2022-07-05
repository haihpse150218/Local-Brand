
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
@WebServlet(urlPatterns = "/web/rating")
public class RatingController  extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getAttribute("action").toString();
		switch (action) {
		case "index":
			System.out.println("action: "+action);
			index(request, response);
			break;
		default:
			request.setAttribute("controller", "error");
			request.setAttribute("action", "index");
		}
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}

	
	private void index(HttpServletRequest request, HttpServletResponse response) {
		
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

