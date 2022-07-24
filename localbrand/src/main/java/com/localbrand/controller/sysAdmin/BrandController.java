package com.localbrand.controller.sysAdmin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.Brand;
import com.localbrand.entities.SystemAccount;
import com.localbrand.service.implement.SystemAdminService;

/**
 * Servlet implementation class BrandController
 */
@WebServlet(urlPatterns="/sysAdmin/brand")
public class BrandController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getAttribute("action").toString().toLowerCase();
		System.out.println("actione ne:" + action);
		HttpSession session = request.getSession();
		SystemAccount sysAdmin = (SystemAccount) session.getAttribute("sysAdmin");
		if (sysAdmin != null) {
			switch (action) {
			case "index":
				index(request, response);
				break;		
			case "create":
				create(request, response);
				break;
			case "update":
				update(request, response);
				break;
			default:
				request.setAttribute("controller", "/error");
				request.setAttribute("action", "index");	
			}
		}else {
			request.setAttribute("controller", "/login");
			request.setAttribute("action", "index");
	}
		System.out.println("/WEB-INF/decorators/sysAdmin/main.jsp");
		request.setAttribute("sysAdmin", sysAdmin);
		request.getRequestDispatcher("/sysAdmin/home").forward(request, response);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
	private void index(HttpServletRequest request, HttpServletResponse response) {
		SystemAdminService sas = new SystemAdminService();
		List<Brand> listb = sas.brandList();
		request.setAttribute("brandList", listb);
		System.out.println(listb);
	}
	private void create(HttpServletRequest request, HttpServletResponse response) {
		SystemAdminService sas = new SystemAdminService();
		Brand b = new Brand();
		
		String brandName = request.getParameter("brandName");
		String description = request.getParameter("description");
		String status = "Active";//request.getParameter("status");
		String logo = request.getParameter("logo");
		String banner = request.getParameter("banner");
		Double stars = Double.parseDouble(request.getParameter("stars"));
		Date date = new Date();
		Date today = new Date(date.getYear()+1900,date.getMonth()+1,date.getDate(),date.getHours(),date.getMinutes(),date.getSeconds());
		
		b.setName(brandName);
		b.setDescription(description);
		b.setStatus(status);
		b.setLogo(logo);
		b.setBanner(banner);
		b.setStars(stars);
		b.setCreateDate(today);
		sas.createNewBrand(b);
		
		request.setAttribute("action", "index");
		
	}
	private void update(HttpServletRequest request, HttpServletResponse response) {
		SystemAdminService sas = new SystemAdminService();
		
		int brandId = Integer.parseInt(request.getParameter("brandId"));
		String status = request.getParameter("status");
		sas.updateBrandStatus(brandId, status);
		
		request.setAttribute("action", "index");
		
	}
    public BrandController() {
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
