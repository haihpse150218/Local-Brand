package com.localbrand.controller.web;

import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Product;
import com.localbrand.service.implement.ProductDetailService;

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
		System.out.println("action ne:"+action);

		switch (action) {
		case "index":
			index(request, response);
			break;
		case "viewdetail":
			viewDetail(request, response);
			break;
		
			
		default:
			request.setAttribute("controller", "/error");
			request.setAttribute("action", "index");
		}
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}
	private void index(HttpServletRequest request, HttpServletResponse response) {
		
		int pid =  Integer.parseInt(request.getParameter("productId")); 
		ProductDetailService pds = new ProductDetailService();
		List<Product> listp = pds.getProductDetail(pid);
		List<Brand> listb = pds.getBrandDetail(pid);
		request.setAttribute("pDetail", listp);
		request.setAttribute("bDetail", listb);
		System.out.println("Product Detail: "+listp);
		System.out.println("Brand Detail: "+listb);
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
