package com.localbrand.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		System.out.println("admin " + admin.toString());
		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		request.setAttribute("listOrder", listOrderObject);

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