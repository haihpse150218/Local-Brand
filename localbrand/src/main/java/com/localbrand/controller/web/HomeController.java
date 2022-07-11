package com.localbrand.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Category;
import com.localbrand.entities.Collection;
import com.localbrand.entities.Customer;
import com.localbrand.entities.Product;
import com.localbrand.service.Models.Cart;
import com.localbrand.service.implement.HomeService;
import com.localbrand.service.implement.LoginService;
import com.localbrand.service.implement.PagingService;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns = "/web/home")
public class HomeController extends HttpServlet {
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
			case "login":
				login(request, response);
				break;
			case "logout":
				logout(request, response);
				break;
			case "viewlistbrandproduct":
				viewListBrandProduct(request, response);
				break;
			case "addtocart":
				addToCart(request, response);
				break;
			case "viewlistproductbystatus":
				viewListProductbyStatus(request, response);
				break;
			case "viewlistproductbycate":
				viewListProductbyCate(request, response);
				break;
			default:
				request.setAttribute("controller", "/error");
				request.setAttribute("action", "index");
			}
		} catch (Exception e) {
			System.out.println("Error at Home Controller");
			e.printStackTrace();
		}
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}

	private void viewListProductbyCate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		String id = request.getParameter("cateid");
		System.out.println("cate id tren req ne : " + id);
		Integer cateid;
		if (id == null)
			cateid = (Integer) session.getAttribute("uricateid");
		else
			cateid = Integer.parseInt(id);

		HomeService service = new HomeService();
		List<Product> listProduct = service.getListProductByCate(cateid);
		System.out.println("size list product by cate : " + listProduct.size());

		Category cate = service.getCategory(cateid);
		request.setAttribute("catename", cate.getName());
		session.setAttribute("uriaction", "viewlistproductbycate");
		session.setAttribute("uricateid", cateid);

		// setup parameter paging
		int pageSize = 6;
		PagingService paging = new PagingService();
		Integer page = (Integer) session.getAttribute("homePage");
		int count = listProduct.size();
		Integer totalPage = (int) Math.ceil((double) (count) / pageSize);
		System.out.println("totalPage: " + totalPage);
		String op = request.getParameter("op");
		Integer gotoPage = null;
		if (request.getParameter("gotoPage") != null) {
			gotoPage = Integer.parseInt(request.getParameter("gotoPage"));
		}
		// pagination
		page = paging.getPage(op, totalPage, page, gotoPage);
		System.out.println("page: " + page);
		int n1 = (page - 1) * pageSize;
		System.out.println("n1: " + n1);
		int n2 = 0;
		if ((count - n1) <= pageSize) {
			n2 = count;
		} else {
			n2 = n1 + pageSize;
		}
		System.out.println("n2 " + n2);
		List<Product> list = listProduct.subList(n1, n2);
		List<Integer> listNumberBox = paging.getListNumberBox(page, totalPage);
		session.setAttribute("listNumberBox", listNumberBox);
		session.setAttribute("homePage", page);
		session.setAttribute("totalHomePage", totalPage);
		request.setAttribute("list", list);

		String uri = request.getServletPath();
		String controller = uri.substring(uri.lastIndexOf("/"));
		System.out.println("controller uri : " + controller);
		session.setAttribute("uri", controller);

		request.setAttribute("action", "viewlistproductbycate");
		request.setAttribute("controller", controller);
	}

	private void viewListProductbyStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		String status = request.getParameter("status");
		System.out.println("status tren req ne : " + status);
		if (status == null)
			status = (String) session.getAttribute("uristatus");

		HomeService service = new HomeService();
		List<Product> listProduct = service.getListProductByStatus(status);
		System.out.println("size list product by status : " + listProduct.size());

		request.setAttribute("statusname", status);
		session.setAttribute("uriaction", "viewlistproductbystatus");
		session.setAttribute("uristatus", status);

		// setup parameter paging
		int pageSize = 6;
		PagingService paging = new PagingService();
		Integer page = (Integer) session.getAttribute("homePage");
		int count = listProduct.size();
		Integer totalPage = (int) Math.ceil((double) (count) / pageSize);
		System.out.println("totalPage: " + totalPage);
		String op = request.getParameter("op");
		Integer gotoPage = null;
		if (request.getParameter("gotoPage") != null) {
			gotoPage = Integer.parseInt(request.getParameter("gotoPage"));
		}
		// pagination
		page = paging.getPage(op, totalPage, page, gotoPage);
		System.out.println("page: " + page);
		int n1 = (page - 1) * pageSize;
		System.out.println("n1: " + n1);
		int n2 = 0;
		if ((count - n1) <= pageSize) {
			n2 = count;
		} else {
			n2 = n1 + pageSize;
		}
		System.out.println("n2 " + n2);
		List<Product> list = listProduct.subList(n1, n2);
		List<Integer> listNumberBox = paging.getListNumberBox(page, totalPage);
		session.setAttribute("listNumberBox", listNumberBox);
		session.setAttribute("homePage", page);
		session.setAttribute("totalHomePage", totalPage);
		request.setAttribute("list", list);

		String uri = request.getServletPath();
		String controller = uri.substring(uri.lastIndexOf("/"));
		System.out.println("controller uri : " + controller);
		session.setAttribute("uri", controller);

		request.setAttribute("action", "viewlistproductbystatus");
		request.setAttribute("controller", controller);

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		String uri = request.getPathInfo();
		System.out.println("uri: " + uri);

		if (uri == null || uri.equalsIgnoreCase("/home")) {
			request.setAttribute("controller", "/home");
			String action = (String) session.getAttribute("uriaction");
			System.out.println("uri action : " + action);
			if (action.equalsIgnoreCase("viewlistbrandproduct")) {
				int uribrandid = (int) session.getAttribute("uribrandid");
				System.out.println("uri brand id : " + uribrandid);
				request.setAttribute("brandid", uribrandid);
				viewListBrandProduct(request, response);
			} else if (action.equalsIgnoreCase("viewlistproductbystatus")) {
				String uristatus = (String) session.getAttribute("uristatus");
				System.out.println("uri status : " + uristatus);
				request.setAttribute("status", uristatus);
				viewListProductbyStatus(request, response);
			} else if (action.equalsIgnoreCase("viewlistproductbycate")) {
				int uricateid = (int) session.getAttribute("uricateid");
				System.out.println("uri cate : " + uricateid);
				request.setAttribute("cateid", uricateid);
				viewListProductbyCate(request, response);
			} else
				index(request, response);
		} else
			request.setAttribute("controller", uri);

		request.setAttribute("action", "index");
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		HomeService service = new HomeService();

		List<Category> listCate = service.getListCate();
		session.setAttribute("category", listCate);

		Cart cart = (Cart) session.getAttribute("cart");
		int cartQuantity = 0;
		if (cart != null) {
			for (int key : cart.getMap().keySet()) {
				cartQuantity += cart.getMap().get(key).getQuantity();
			}
		}
		System.out.println("cart quantity la : " + cartQuantity);
		session.setAttribute("cartQuantity", cartQuantity);

		List<Product> listTopProduct = service.getTopProduct();
		request.setAttribute("listTopProduct", listTopProduct);
		System.out.println("top 6 product size : " + listTopProduct.size());

		List<Collection> listTopCollection = service.getTopCollection();
		request.setAttribute("listTopCollection", listTopCollection);
		System.out.println("top 5 collection size : " + listTopCollection.size());

		// List <Product> listAllProduct = service.getListProduct();
		List<Brand> listAllBrand = service.getBrandList();
		System.out.println("brand size : " + listAllBrand.size());
		session.setAttribute("listAllBrand", listAllBrand);

		session.setAttribute("uriaction", "index");

		// setup parameter paging
		int pageSize = 6;
		PagingService paging = new PagingService();
		Integer page = (Integer) session.getAttribute("homePage");
		int count = listTopProduct.size();
		Integer totalPage = (int) Math.ceil((double) (count) / pageSize);
		System.out.println("totalPage: " + totalPage);
		String op = request.getParameter("op");
		Integer gotoPage = null;
		if (request.getParameter("gotoPage") != null) {
			gotoPage = Integer.parseInt(request.getParameter("gotoPage"));
		}
		// pagination
		page = paging.getPage(op, totalPage, page, gotoPage);
		System.out.println("page: " + page);
		int n1 = (page - 1) * pageSize;
		System.out.println("n1: " + n1);
		int n2 = 0;
		if ((count - n1) <= pageSize) {
			n2 = count;
		} else {
			n2 = n1 + pageSize;
		}
		System.out.println("n2 " + n2);
		List<Product> list = listTopProduct.subList(n1, n2);
		List<Integer> listNumberBox = paging.getListNumberBox(page, totalPage);
		session.setAttribute("listNumberBox", listNumberBox);
		session.setAttribute("homePage", page);
		session.setAttribute("totalHomePage", totalPage);
		request.setAttribute("list", list);

		String uri = request.getServletPath();
		String controller = uri.substring(uri.lastIndexOf("/"));
		System.out.println("controller uri : " + controller);
		session.setAttribute("uri", controller);

		request.setAttribute("controller", controller);
		request.setAttribute("action", "index");
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		session.setAttribute("user", null);

		String uri = (String) session.getAttribute("uri");
		System.out.println("uri: " + uri);

		request.setAttribute("action", "index");

		if (uri == null || uri.equalsIgnoreCase("/home")) {
			request.setAttribute("controller", "/home");
			String action = (String) session.getAttribute("uriaction");
			System.out.println("uri action : " + action);
			if (action.equalsIgnoreCase("viewlistbrandproduct")) {
				int uribrandid = (int) session.getAttribute("uribrandid");
				System.out.println("uri brand id : " + uribrandid);
				request.setAttribute("brandid", uribrandid);
				viewListBrandProduct(request, response);
			} else if (action.equalsIgnoreCase("viewlistproductbystatus")) {
				String uristatus = (String) session.getAttribute("uristatus");
				System.out.println("uri status : " + uristatus);
				request.setAttribute("status", uristatus);
				viewListProductbyStatus(request, response);
			} else if (action.equalsIgnoreCase("viewlistproductbycate")) {
				int uricateid = (int) session.getAttribute("uricateid");
				System.out.println("uri cate : " + uricateid);
				request.setAttribute("cateid", uricateid);
				viewListProductbyCate(request, response);
			} else
				index(request, response);
		} else
			request.setAttribute("controller", uri);
	}

	private void viewListBrandProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = request.getParameter("brandid");
		System.out.println("brand id tren req ne : " + id);
		Integer brandid;
		if (id == null)
			brandid = (Integer) session.getAttribute("uribrandid");
		else
			brandid = Integer.parseInt(id);
		HomeService service = new HomeService();
		List<Product> listBrandProduct = service.getListBrandProduct(brandid);
		Brand brand = service.getBrand(brandid);

		System.out.println("brand id lay duoc : " + brandid);
		System.out.println("brand product size : " + listBrandProduct.size());

		request.setAttribute("brandname", brand.getName());
		session.setAttribute("uriaction", "viewlistbrandproduct");
		session.setAttribute("uribrandid", brandid);

		// setup parameter paging
		int pageSize = 6;
		PagingService paging = new PagingService();
		Integer page = (Integer) session.getAttribute("homePage");
		int count = listBrandProduct.size();
		Integer totalPage = (int) Math.ceil((double) (count) / pageSize);
		System.out.println("totalPage: " + totalPage);
		String op = request.getParameter("op");
		Integer gotoPage = null;
		if (request.getParameter("gotoPage") != null) {
			gotoPage = Integer.parseInt(request.getParameter("gotoPage"));
		}
		// pagination
		page = paging.getPage(op, totalPage, page, gotoPage);
		System.out.println("page: " + page);
		int n1 = (page - 1) * pageSize;
		System.out.println("n1: " + n1);
		int n2 = 0;
		if ((count - n1) <= pageSize) {
			n2 = count;
		} else {
			n2 = n1 + pageSize;
		}
		System.out.println("n2 " + n2);
		List<Product> list = listBrandProduct.subList(n1, n2);
		List<Integer> listNumberBox = paging.getListNumberBox(page, totalPage);
		session.setAttribute("listNumberBox", listNumberBox);
		session.setAttribute("homePage", page);
		session.setAttribute("totalHomePage", totalPage);
		request.setAttribute("list", list);

		String uri = request.getServletPath();
		String controller = uri.substring(uri.lastIndexOf("/"));
		System.out.println("controller uri : " + controller);
		session.setAttribute("uri", controller);
		request.setAttribute("action", "viewlistbrandproduct");
		request.setAttribute("controller", controller);

	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		int productid = Integer.parseInt(request.getParameter("productid"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		HomeService service = new HomeService();
		Cart cart = (Cart) session.getAttribute("cart");

		cart = service.addToCart(productid, quantity, cart);
		System.out.println("product co quantity hien tai la : " + cart.getMap().get(productid).getQuantity());
		session.setAttribute("cart", cart);

		int cartQuantity = 0;
		if (cart != null) {
			for (int key : cart.getMap().keySet()) {
				cartQuantity += cart.getMap().get(key).getQuantity();
			}
		}
		System.out.println("cart quantity la : " + cartQuantity);
		session.setAttribute("cartQuantity", cartQuantity);

		String uri = request.getServletPath();
		String controller = uri.substring(uri.lastIndexOf("/"));
		System.out.println("controller uri cart : " + controller);
		session.setAttribute("uri", controller);
		request.setAttribute("controller", controller);
		
		//set default la vao trang index cua uri lay duoc
		request.setAttribute("action", "index");

		//neu dang o trang home thi se vao action phu hop
		if (controller.equalsIgnoreCase("/home")) {
			String action = (String) session.getAttribute("uriaction");
			System.out.println("uri action : " + action);
			if (action == null)
				action = "index";
			else if (action.equalsIgnoreCase("viewlistbrandproduct")) {
				int uribrandid = (int) session.getAttribute("uribrandid");
				System.out.println("uri brand id : " + uribrandid);
				request.setAttribute("brandid", uribrandid);
				viewListBrandProduct(request, response);
			} else if (action.equalsIgnoreCase("viewlistproductbystatus")) {
				String uristatus = (String) session.getAttribute("uristatus");
				System.out.println("uri status : " + uristatus);
				request.setAttribute("status", uristatus);
				viewListProductbyStatus(request, response);
			} else if (action.equalsIgnoreCase("viewlistproductbycate")) {
				int uricateid = (int) session.getAttribute("uricateid");
				System.out.println("uri cate : " + uricateid);
				request.setAttribute("cateid", uricateid);
				viewListProductbyCate(request, response);
			} else
				index(request, response);

			request.setAttribute("action", action);
			
		
		} 
		
		//chinh cho cac trang web khac nhu brandhome,.. o day
		//else if (controller.equalsIgnoreCase("/brandhome")){}

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
