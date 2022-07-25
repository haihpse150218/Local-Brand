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
import com.localbrand.service.implement.HomeService;
import com.localbrand.service.implement.LoginService;
import com.localbrand.service.implement.ViewCartService;
import com.localbrand.service.models.Cart;

/**
 * Servlet implementation class CartController
 */
@WebServlet(urlPatterns = "/web/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("brandId");
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
			case "viewcheckout":
				viewcheckout(request, response);
				break;
			case "updatequantity":
				updateQuantity(request, response);
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

	private void updateQuantity(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		HomeService service = new HomeService();

		// lay product id va quantity
		int productid = Integer.parseInt(request.getParameter("productid"));
		int updquantity = Integer.parseInt(request.getParameter("updquantity"));
		System.out.println("update quantity lay duoc : " + updquantity);

		cart = service.UpdateQuantityCart(cart, productid, updquantity);
		System.out.println(
				"product vua add co quantity trong cart hien tai la : " + cart.getMap().get(productid).getQuantity());
		session.setAttribute("cart", cart);

		// ve lai trang cart
		CartController cactr = new CartController();
		cactr.index(request, response);

		// set cartQuantity cho front end
		int cartQuantity = service.CartQuantityCount(cart);
		System.out.println("cart quantity la : " + cartQuantity);
		session.setAttribute("cartQuantity", cartQuantity);

		// set default la vao trang index cua uri lay duoc
		request.setAttribute("action", "index");

	}

	public void viewcheckout(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		IViewCartService vcsv = new ViewCartService();
		HomeService hosv = new HomeService();
		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");

		double total = 0;
		double totalDis = 0;
		double totalCart = 0;
		double feeShip = 0;

		if (cart == null) {
			System.out.println("cart null !");
		} else {
			if (user != null)
				total = vcsv.getTotalInCart(cart) * (1 - user.getRankId().getDiscount());
			else
				total = vcsv.getTotalInCart(cart);
			totalCart = vcsv.getTotalInCart(cart);
			// tinh phi ship = so brand trong cart * 30k
			List<Cart> list = vcsv.getListCartProductByBrand(cart);
			feeShip = 30000 * list.size();
		}

		request.setAttribute("totalPrice", total + feeShip);
		request.setAttribute("totalCart", totalCart);
		request.setAttribute("feeShip", feeShip);
		session.setAttribute("cartQuantity", hosv.CartQuantityCount(cart));

		// set uri action de luc login biet cho nao de quay lai
		session.setAttribute("uriaction", "viewcheckout");

		request.setAttribute("action", "index");
		request.setAttribute("controller", "/checkout");

	}

	private void updateinfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		IViewCartService vcsv = new ViewCartService();
		LoginService loginService = new LoginService();

		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("user");

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		if (user.getAddress() == null) {
			String city = request.getParameter("city");
			String district = request.getParameter("district");
			if (city != null && district != null)
				address += ", " + district + ", " + city;
			System.out.println("address ne : " + address);
		} else if (!user.getAddress().equalsIgnoreCase(address)) {
			String city = request.getParameter("city");
			String district = request.getParameter("district");
			if (city != null && district != null)
				address += ", " + district + ", " + city;
			System.out.println("address ne : " + address);
		}

		Customer cus = new Customer();
		cus.setName(name);
		cus.setEmail(email);
		cus.setPhone(phone);
		cus.setAddress(address);
		cus.setId(user.getId());

		vcsv.updateReceiveInfo(cus);
		session.setAttribute("user", loginService.loginByUsername(user.getUsername(), user.getPassword()));

		// quay ve trang index view cart
		index(request, response);
		request.setAttribute("controller", "/viewcart");
		request.setAttribute("action", "index");

	}

	private void checkout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		updateinfo(request, response);

		ICheckoutService cosv = new CheckoutService();
		HomeService hosv = new HomeService();
		HomeController homectr = new HomeController();
		OrderController orctr = new OrderController();
		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");

		if (hosv.CartQuantityCount(cart) == 0) {
			// chua co gi trong cart thi khong cho check out
			homectr.index(request, response);
			request.setAttribute("controller", "/home");
			request.setAttribute("action", "index");
		} else if (user == null) {
			homectr.login(request, response);
		} else {
			cosv.checkout(cart, user.getId());
			// xoa cart
			session.removeAttribute("cart");
			session.setAttribute("cartQuantity", 0);

			// chuyen toi trang order
			request.setAttribute("orderstatus", "");
			orctr.index(request, response);
			request.setAttribute("controller", "/order");
			request.setAttribute("action", "index");
		}
	}

	public void index(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		IViewCartService vcsv = new ViewCartService();

		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");
		double total = 0;
		double totalCart = 0;
		double feeShip = 0;
		if (cart == null) {
			System.out.println("cart null !");
		} else {
			if (user != null)
				total = vcsv.getTotalInCart(cart) * (1 - user.getRankId().getDiscount());
			else
				total = vcsv.getTotalInCart(cart);
			totalCart = vcsv.getTotalInCart(cart);
			// tinh phi ship = so brand trong cart * 30k
			List<Cart> list = vcsv.getListCartProductByBrand(cart);
			feeShip = 30000 * list.size();
		}

		request.setAttribute("totalPrice", total + feeShip);
		request.setAttribute("totalCart", totalCart);
		request.setAttribute("feeShip", feeShip);

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
