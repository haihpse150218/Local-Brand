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
import com.localbrand.sessionbeans.BrandCategoryFacade;

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
		default:
			request.setAttribute("controller", "error");
			request.setAttribute("action", "index");
		}
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		 HttpSession session = request.getSession();
		 
		 
		// viet code o day

		 
		 
		 
		// set lai aciton de no van o lai trang cu
		String uri = (String) session.getAttribute("uri");
		request.setAttribute("controller", uri);
		request.setAttribute("action", "index");
		session.removeAttribute("uri");

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
		doGet(request, response);
	}

}
