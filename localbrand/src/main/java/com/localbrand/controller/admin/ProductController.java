package com.localbrand.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.BrandAccount;
import com.localbrand.service.implement.HomeAdmin;
import com.localbrand.service.models.OrderObject;
import com.localbrand.service.models.ProductRevenue;

@WebServlet(name = "ProductControllerAdmin", urlPatterns = { "/admin/product" })
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getAttribute("action").toString();
		System.out.println("actione ne:" + action);
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		if (admin != null) {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "fillter":
				fillter(request, response);
				break;
			case "sort":
				sort(request, response);
				break;
			default:
				request.setAttribute("controller", "/error");
				request.setAttribute("action", "index");
			}

		} else {
			request.setAttribute("controller", "/login");
			request.setAttribute("action", "index");
		}

		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}

	private void sort(HttpServletRequest request, HttpServletResponse response) {
		String op = request.getParameter("op");
		System.out.println("op: " + op);
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");

		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		List<ProductRevenue> listProduct = HomeAdmin.getInstance().listReportProduct(listOrderObject);
		
		System.out.println(op+"before: " +listProduct.toString());
		switch (op) {
		case "numberOrder":
			Collections.sort(listProduct, new Comparator<ProductRevenue>() {
				@Override
				public int compare(ProductRevenue o1, ProductRevenue o2) {
					return o2.getNumberOrdered() - o1.getNumberOrdered();
				}
			});
			break;
		case "turnover":
			Collections.sort(listProduct, new Comparator<ProductRevenue>() {
				@Override
				public int compare(ProductRevenue o1, ProductRevenue o2) {
					return o2.getNumberDelivered() - o1.getNumberDelivered();
				}
			});
			break;
		case "proceeds":
			Collections.sort(listProduct, new Comparator<ProductRevenue>() {
				@Override
				public int compare(ProductRevenue o1, ProductRevenue o2) {
					return (int) (o2.getProceeds() - o1.getProceeds());
				}
			});
			break;
		default:
			break;
		}
		System.out.println(op+": " +listProduct.toString());
		request.setAttribute("controller", "/product");
		request.setAttribute("action", "index");
		request.setAttribute("listProduct", listProduct);

	}

	private void fillter(HttpServletRequest request, HttpServletResponse response) {
		String op = request.getParameter("op");
		System.out.println("op: " + op);
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");

		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		List<ProductRevenue> listProduct = new ArrayList<ProductRevenue>();
		switch (op) {
		case "all":
			listProduct = HomeAdmin.getInstance().listReportProduct(listOrderObject);

			break;
		case "aWeek":
			List<OrderObject> listOrderThisWeek = HomeAdmin.getInstance().getThisWeekOrderListByBrandId(listOrderObject,
					new Date());
			System.out.println(listOrderThisWeek);
			listProduct = HomeAdmin.getInstance().listReportProduct(listOrderThisWeek);
			break;
		default:
			break;
		}
		request.setAttribute("controller", "/product");
		request.setAttribute("action", "index");
		request.setAttribute("listProduct", listProduct);

	}

	private void index(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		List<ProductRevenue> listProduct = HomeAdmin.getInstance().listReportProduct(listOrderObject);

		request.setAttribute("listProduct", listProduct);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}