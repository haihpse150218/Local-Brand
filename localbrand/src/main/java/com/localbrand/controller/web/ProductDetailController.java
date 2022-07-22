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
		String tcmt = request.getParameter("textComment");
		System.out.println("action ne:"+action);
		System.out.println("cmt ne: "+tcmt);
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
		default:
			request.setAttribute("controller", "/error");
			request.setAttribute("action", "index");
		}
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}
	private void index(HttpServletRequest request, HttpServletResponse response) {
		
		int pid =  Integer.parseInt(request.getParameter("productid")); 
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
		System.out.println("listcus " +listcus );
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
		int pid =  Integer.parseInt(request.getParameter("productId"));
		int oid =  Integer.parseInt(request.getParameter("orderId"));
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
