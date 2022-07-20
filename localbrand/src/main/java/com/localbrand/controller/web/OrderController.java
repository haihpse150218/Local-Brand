package com.localbrand.controller.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.Customer;
import com.localbrand.entities.Order;
import com.localbrand.service.IMemberOrderHistory;
import com.localbrand.service.implement.MemberOrderService;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			
			String action = request.getAttribute("action").toString();
			System.out.println("actione ne:" + action);
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "setStatusOrder":
				setStatusOrder(request, response);
				break;
			
			default:
				request.setAttribute("controller", "/error");
				request.setAttribute("action", "index");
			}
		} catch (Exception e) {
			System.out.println("Error at Order Controller");
			e.printStackTrace();
		}
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		Customer cus = (Customer) session.getAttribute("user");
		
		IMemberOrderHistory mosv = new MemberOrderService();
		
		List<Order> listOrder = mosv.getMemberListOrder(cus.getId());
		request.setAttribute("LIST_ORDER", listOrder);

		request.setAttribute("controller", "/order");
		request.setAttribute("action", "index");
	}
	
	private void setStatusOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		IMemberOrderHistory mosv = new MemberOrderService();
		
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		String orderstatus = request.getParameter("orderstatus");
		
		mosv.setOrderStatus(orderid, orderstatus);
		
		//quay ve trang index order
		index(request,response);
		request.setAttribute("controller", "/order");
		request.setAttribute("action", "index");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
