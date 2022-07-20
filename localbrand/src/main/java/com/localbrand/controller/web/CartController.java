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
import com.localbrand.service.ICheckoutService;
import com.localbrand.service.IViewCartService;
import com.localbrand.service.implement.CheckoutService;
import com.localbrand.service.implement.ViewCartService;
import com.localbrand.service.models.Cart;

/**
 * Servlet implementation class CartController
 */
@WebServlet(urlPatterns="/web/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
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
			case "checkout":
				checkout(request, response);
				break;
			case "updateinfo":
				updateinfo(request, response);
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

	private void updateinfo(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		IViewCartService vcsv = new ViewCartService();

		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("user");

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		Customer cus = new Customer();
		cus.setName(name);
		cus.setEmail(email);
		cus.setPhone(phone);
		cus.setAddress(address);
		cus.setId(user.getId());

		vcsv.updateReceiveInfo(cus);

		// quay ve trang index view cart
		index(request, response);
		request.setAttribute("controller", "/viewcart");
		request.setAttribute("action", "index");

	}

	private void checkout(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		ICheckoutService cosv = new CheckoutService();

		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("user");

		Cart cart = (Cart) session.getAttribute("cart");
		cosv.checkout(cart, user.getId());

		// chuyen toi trang order
		request.setAttribute("controller", "/order");
		request.setAttribute("action", "index");

	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		IViewCartService vcsv = new ViewCartService();

		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");

		double memberDiscount = vcsv.getDiscount(user.getId());
		request.setAttribute("memberDiscount", memberDiscount);

		List<Cart> list = vcsv.getListCartProductByBrand(cart);
		request.setAttribute("listCartByBrand", list);

		double total = vcsv.getTotalInCart(cart) * vcsv.getDiscount(user.getId());
		request.setAttribute("totalPrice", total);

		// index cua view cart
		request.setAttribute("controller", "/viewcart");
		request.setAttribute("action", "index");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
	}

}
