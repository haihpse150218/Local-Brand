package com.localbrand.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.localbrand.entities.Brand;
import com.localbrand.entities.BrandAccount;
import com.localbrand.service.implement.SystemAdminService;

/**
 * Servlet implementation class SystemAdminController
 */
@WebServlet(urlPatterns = "/admin/systemadmin")
public class SystemAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getAttribute("action").toString();
		System.out.println("actione ne:" + action);
		/*HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("systemadmin");
		if (admin != null) {
			switch (action) {
			case "index":
				index(request, response);
				break;		
			case "createBrand":
				createBrand(request, response);
				break;
			case "createBrandAccount":
				createBrandAccount(request, response);
				break;
			case "updateBrand":
				updateBrandStatus(request, response);
				break;
			case "updateBrandAccount":
				updateBrandAccount(request, response);
				break;
			default:
				request.setAttribute("controller", "/error");
				request.setAttribute("action", "index");	
			}
		}else {
			request.setAttribute("controller", "/login");
			request.setAttribute("action", "index");
		}*/
		switch (action) {
		case "index":
			index(request, response);
			break;		
		case "createBrand":
			createBrand(request, response);
			break;
		case "createBrandAccount":
			createBrandAccount(request, response);
			break;
		case "updateBrand":
			updateBrandStatus(request, response);
			break;
		case "updateBrandAccount":
			updateBrandAccount(request, response);
			break;
		default:
			request.setAttribute("controller", "/error");
			request.setAttribute("action", "index");
		}
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
	private void index(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		SystemAdminService sas = new SystemAdminService();
		List<Brand> listb = sas.brandList();
		List<BrandAccount> listbAcc = sas.brandAdminList();
		
		System.out.println("brandList "+ listb);
		System.out.println("brandAccountList "+ listbAcc);
		
		request.setAttribute("brandList", listb);
		request.setAttribute("brandAccountList", listbAcc);
	}
	private void createBrand(HttpServletRequest request, HttpServletResponse response) {
		SystemAdminService sas = new SystemAdminService();
		Brand b = new Brand();
		
		String brandName = request.getParameter("brandName");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
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
		index(request, response);
	}
	private void createBrandAccount(HttpServletRequest request, HttpServletResponse response) {
		SystemAdminService sas = new SystemAdminService();
		BrandAccount bAcc = new BrandAccount();
		Brand b = new Brand(); 
		
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean role = Boolean.parseBoolean(request.getParameter("role"));
		int brandId = Integer.parseInt(request.getParameter("brandId"));
		b.setId(brandId);
		int status = Integer.parseInt(request.getParameter("status"));
		
		bAcc.setName(name);
		bAcc.setUsername(username);
		bAcc.setPassword(password);
		bAcc.setRole(role);
		bAcc.setBrandId(b);
		bAcc.setStatus(status);
		sas.createBrandAdmin(bAcc);
		
		request.setAttribute("action", "index");
		index(request, response);
	}
	private void updateBrandStatus(HttpServletRequest request, HttpServletResponse response) {
		SystemAdminService sas = new SystemAdminService();
		
		int brandId = Integer.parseInt(request.getParameter("brandId"));
		String status = request.getParameter("status");
		sas.updateBrandStatus(brandId, status);
		
		request.setAttribute("action", "index");
		index(request, response);
	}
	private void updateBrandAccount(HttpServletRequest request, HttpServletResponse response) {
		SystemAdminService sas = new SystemAdminService();
		int brandAccountId = Integer.parseInt(request.getParameter("brandAccountId"));
		boolean reset =  Boolean.parseBoolean(request.getParameter("reset"));
		if (reset == true) {
			String password = request.getParameter("password");
			sas.resetBrandAdminPassword(brandAccountId, password);
		}else {
			int status = Integer.parseInt(request.getParameter("status"));
			sas.updateBrandAdminStatus(brandAccountId, status);
		}
		request.setAttribute("action", "index");
		index(request, response);
	}
	
    public SystemAdminController() {
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
