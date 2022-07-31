package com.localbrand.controller.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Customer;
import com.localbrand.entities.Feedback;
import com.localbrand.entities.Order;
import com.localbrand.entities.Product;
import com.localbrand.service.implement.HomeService;
import com.localbrand.service.implement.ProductDetailService;
import com.localbrand.service.models.Cart;

/**
 * Servlet implementation class ProductDetailController
 */
@WebServlet(urlPatterns = "/web/detail")
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getAttribute("action").toString();
		//String tcmt = request.getParameter("textComment");
		System.out.println("action ne:" + action);
		
		switch (action) {
		case "index":
			index(request, response);
			break;
		case "viewdetail":
			viewDetail(request, response);
			break;
		case "createfb":
			createFeedback(request, response);
			break;
		case "addtocart":
			try {
				addToCart(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			request.setAttribute("controller", "/error");
			request.setAttribute("action", "index");
		}
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		HomeService service = new HomeService();

		// lay product id va quantity
		int productid = Integer.parseInt(request.getParameter("productid"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println("quantity lay duoc : " + quantity);

		int varid = 0;
		String size = request.getParameter("size");
		String color = request.getParameter("color");
		varid = service.getVarId(productid, size, color);
		System.out.println("product var id : " + varid);
		ProductDetailController dectr = new ProductDetailController();
		dectr.index(request, response);

		cart = service.addToCart(varid, quantity, cart);
		System.out.println(
				"product vua add co quantity trong cart hien tai la : " + cart.getMap().get(varid).getQuantity());
		session.setAttribute("cart", cart);

		// set cartQuantity cho front end
		int cartQuantity = service.CartQuantityCount(cart);
		System.out.println("cart quantity la : " + cartQuantity);
		session.setAttribute("cartQuantity", cartQuantity);

		// set default la vao trang index cua uri lay duoc
		request.setAttribute("action", "index");

	}

	private void index(HttpServletRequest request, HttpServletResponse response) {

		int pid = Integer.parseInt(request.getParameter("productid"));
		System.out.println("pid " + pid);
		ProductDetailService pds = new ProductDetailService();
		Product listp = pds.getProductDetail(pid);
		if (listp.getProductList().size() > 0) {
			List<String> listSize = pds.getListSize(listp);
			request.setAttribute("listSize", listSize);
			List<String> listColor = pds.getListColor(listp);
			request.setAttribute("listColor", listColor);
		}

		List<Feedback> listf = pds.getProductDetail(pid).getFeedbackList();
		List<Customer> listcus = pds.getCusByFb(listf);

		Brand brand = pds.getBrandDetail(pid);
		List<Product> child = pds.getProductChild(pid);
		List<Product> listAll = pds.getAllProduct(pid);
//		System.out.println("list " + listAll);
		
		request.setAttribute("pAll", listAll);
		request.setAttribute("pChild", child);
		request.setAttribute("fDetail", listf);

		request.setAttribute("cusDetail", listcus);
		request.setAttribute("pDetail", listp);
		request.setAttribute("bDetail", brand);

	}

	private void createFeedback(HttpServletRequest request, HttpServletResponse response) {

		ProductDetailService pds = new ProductDetailService();
		String tcmt = request.getParameter("textComment");
		Double voting = Double.parseDouble(request.getParameter("stars"));
		int pid = Integer.parseInt(request.getParameter("productId"));
		int oid = Integer.parseInt(request.getParameter("orderId"));
		Product p = new Product();
		Order o = new Order();
		Feedback f = new Feedback();
		p.setId(pid);
		o.setId(oid);
		f.setFeedbackTime(new Date());
		f.setTextComment(tcmt);
		f.setVoting(voting);
		f.setStatus(1);
		f.setProductId(p);
		f.setOrderId(o);
		pds.createFeedback(f);

		request.setAttribute("action", "index");
		request.setAttribute("productId", pid);
		index(request, response);

	}

	private void viewDetail(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		// set uri

	}

	public ProductDetailController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		processRequest(request, response);
	}

}
