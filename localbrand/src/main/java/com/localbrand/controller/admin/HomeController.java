package com.localbrand.controller.admin;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.Brand;
import com.localbrand.entities.BrandAccount;
import com.localbrand.entities.Collection;
import com.localbrand.service.implement.HomeAdmin;
import com.localbrand.service.models.OrderObject;

@WebServlet(name = "HomeControllerAdmin", urlPatterns = { "/admin/home" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getAttribute("action").toString();
		System.out.println("actione ne:" + action);
		switch (action) {
		case "index":
			index(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		default:
			request.setAttribute("controller", "/error");
			request.setAttribute("action", "index");
		}
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("admin");
		request.setAttribute("controller", "/login");
		request.setAttribute("action", "index");
		return;
	}

	private void index(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");

		if (admin != null) {
			System.out.println("admin " + admin.toString());
			List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
			Collections.sort(listOrderObject, new Comparator<OrderObject>() {
				@Override
				public int compare(OrderObject o1, OrderObject o2) {
					return o2.getOrderDate().compareTo(o1.getOrderDate());
				}
			});
			
			
			int size = listOrderObject.size();
			int top = 5;
			System.out.println("size" + size);
			if(size < top) top = size;
			request.setAttribute("listOrder", listOrderObject.subList(0, top));
			double totalSalesThisWeek = HomeAdmin.getInstance().SalesThisWeek(listOrderObject);
			double totalSalesLastWeek = HomeAdmin.getInstance().SalesLastWeek(listOrderObject);
			Map<Collection, Double> revenueByCollection = HomeAdmin.getInstance().getCollectionRevenue(admin.getBrandId().getId());
			Map<Integer, Double> revenueByTime = HomeAdmin.getInstance().calculateRevenueByTime(admin.getBrandId().getId());
			request.setAttribute("revenueByTime", revenueByTime);
			request.setAttribute("revenueByCollection", revenueByCollection);
			double growth = (totalSalesThisWeek - totalSalesLastWeek) / totalSalesLastWeek;
			
			System.out.println("totalSalesThisWeek"+totalSalesThisWeek);
			System.out.println("totalSalesLastWeek"+totalSalesLastWeek);
			request.setAttribute("salesThisWeek", totalSalesThisWeek);
			request.setAttribute("salesLastWeek", totalSalesLastWeek);
			request.setAttribute("growth", growth);
			double totalOrderThisWeek = HomeAdmin.getInstance().TotalOrdersThisWeek(listOrderObject);
			double totalOrderLastWeek = HomeAdmin.getInstance().TotalOrdersLastWeek(listOrderObject);
			double growthOrder = (totalOrderThisWeek - totalOrderLastWeek) / totalOrderLastWeek;
			request.setAttribute("totalOrderThisWeek", (int) totalOrderThisWeek);
			request.setAttribute("totalOrderLastWeek", totalOrderLastWeek);
			request.setAttribute("growthOrder", growthOrder);
			double newMembers = HomeAdmin.getInstance().NewCustomerThisWeek(listOrderObject);
			request.setAttribute("newMembers", (int) newMembers);
			double newMembersLastWeek = HomeAdmin.getInstance().NewCustomerLastWeek(listOrderObject);
			request.setAttribute("newMembersLastWeek", newMembersLastWeek);
			double growthNewMembers = (newMembers - newMembersLastWeek) / newMembersLastWeek;
			request.setAttribute("growthNewMembers", growthNewMembers);
		}else {
			request.setAttribute("controller", "/login");
			request.setAttribute("action", "index");
		}

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
}
