package com.localbrand.controller.admin;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.BrandAccount;
import com.localbrand.service.implement.AdminOrderService;
import com.localbrand.service.implement.HomeAdmin;
import com.localbrand.service.models.OrderObject;

@WebServlet(name = "OrderedControllerAdmin", urlPatterns = { "/admin/ordered" })
public class OrderedController extends HttpServlet {
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
			case "search":
				search(request, response);
				break;
			case "update":
				update(request, response);
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

	
	private static String removeAccent(String s) {
		  
		  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		  return pattern.matcher(temp).replaceAll("");
		 }
	
	private void search(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		
		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		
		request.setAttribute("listOrder", listOrderObject);
		List<OrderObject> listResult = new ArrayList<>();
		String searchByCustomerName = request.getParameter("searchCustomerName");
		request.setAttribute("searchCustomerName", searchByCustomerName);
		

		String cusName = removeAccent(searchByCustomerName).toLowerCase();
		for (OrderObject orderObject : listOrderObject) {
			if(removeAccent(orderObject.getCustomerId().getName().toLowerCase()).contains(cusName)){
				listResult.add(orderObject);
			}
		}
		
		request.setAttribute("listOrder", listResult);
		request.setAttribute("action", "index");
		
	}

	private void sort(HttpServletRequest request, HttpServletResponse response) {
		String op = request.getParameter("op");
		
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");

		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		switch (op) {
		case "latest":
			Collections.sort(listOrderObject, new Comparator<OrderObject>() {
				@Override
				public int compare(OrderObject o1, OrderObject o2) {
					return o2.getOrderDate().compareTo(o1.getOrderDate());
				}
			});
			break;
		case "status":
			Collections.sort(listOrderObject, new Comparator<OrderObject>() {
				@Override
				public int compare(OrderObject o1, OrderObject o2) {
					return o1.getStatus().compareTo(o2.getStatus());
				}
			});
			break;
		case "price":
			Collections.sort(listOrderObject, new Comparator<OrderObject>() {
				@Override
				public int compare(OrderObject o1, OrderObject o2) {
					return (int)(o2.getTotal() - o1.getTotal());
				}
			});
			break;
		default:
			break;
		}
		request.setAttribute("controller", "/ordered");
		request.setAttribute("action", "index");
		request.setAttribute("listOrder", listOrderObject);

	}

	private void fillter(HttpServletRequest request, HttpServletResponse response) {
		String op = request.getParameter("op");
		System.out.println("op: " + op);
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");

		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		List<OrderObject> result = new ArrayList<OrderObject>();

		switch (op) {
		case "intransit":
			for (OrderObject o : listOrderObject) {
				if (o.getStatus().equalsIgnoreCase("Shipping") || o.getStatus().equalsIgnoreCase("Intransit")) {
					result.add(o);
				}
			}

			break;
		case "delivered":
			for (OrderObject o : listOrderObject) {
				if (o.getStatus().equalsIgnoreCase("Delivered") || o.getStatus().equalsIgnoreCase("Completed")) {
					result.add(o);
				}
			}
			break;
		case "canceled":
			for (OrderObject o : listOrderObject) {
				if (o.getStatus().equalsIgnoreCase("Canceled") || o.getStatus().equalsIgnoreCase("Cancel")) {
					result.add(o);
				}
			}
			break;
		default:
			break;
		}
		request.setAttribute("controller", "/ordered");
		request.setAttribute("action", "index");
		request.setAttribute("listOrder", result);

	}

	private void index(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		
		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		
		request.setAttribute("listOrder", listOrderObject);
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) {
		
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		String newStatus = request.getParameter("status");
		
		AdminOrderService adminOrderService = new AdminOrderService();
		adminOrderService.updateOrderStatus(orderId, newStatus);
		
		index(request, response);
		request.setAttribute("action", "index");
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