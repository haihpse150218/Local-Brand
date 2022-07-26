package com.localbrand.controller.admin;

import java.io.IOException;
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
import com.localbrand.service.implement.AdminBrandInfoService;
import com.localbrand.service.implement.HomeAdmin;
import com.localbrand.service.models.OrderObject;

@WebServlet(name = "InformationControllerAdmin", urlPatterns = { "/admin/information" })
public class InformationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getAttribute("action").toString();
		System.out.println("actione ne:" + action);
		switch (action) {
		case "index":
			index(request, response);
			break;
		case "update":
			update(request, response);
			break;
		default:
			request.setAttribute("controller", "/error");
			request.setAttribute("action", "index");
		}
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		
		AdminBrandInfoService adminBrandInfoService = new AdminBrandInfoService();
		if (admin != null) {
			try {
				Brand brand = adminBrandInfoService.getBrand(admin.getBrandId().getId());
				String brandName = request.getParameter("txtBrandName");
				String logo = request.getParameter("txtLogo");
				String banner = request.getParameter("txtBanner");
				String description = request.getParameter("txtDescription");
				String status = request.getParameter("txtStatus");
				brand.setName(brandName);
				brand.setLogo(logo);
				brand.setBanner(banner);
				brand.setDescription(description);
				brand.setStatus(status);
				
				System.out.println(brand.getDescription());
				adminBrandInfoService.updateBrand(brand);
				
				request.setAttribute("brand", brand);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("action", "index");
		}else {
			request.setAttribute("controller", "/login");
			request.setAttribute("action", "index");
		}
	}

	private void index(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");

		AdminBrandInfoService adminBrandInfoService = new AdminBrandInfoService();
		if (admin != null) {
			Brand brand = adminBrandInfoService.getBrand(admin.getBrandId().getId());
			request.setAttribute("brand", brand);
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
