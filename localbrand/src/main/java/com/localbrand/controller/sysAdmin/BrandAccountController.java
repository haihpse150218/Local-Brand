package com.localbrand.controller.sysAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.Brand;
import com.localbrand.entities.BrandAccount;
import com.localbrand.entities.SystemAccount;
import com.localbrand.service.implement.SystemAdminService;

/**
 * Servlet implementation class BrandAccountController
 */
@WebServlet(urlPatterns="/sysAdmin/brandAccount")
public class BrandAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getAttribute("action").toString();
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
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
	private void index(HttpServletRequest request, HttpServletResponse response) {
		SystemAdminService sas = new SystemAdminService();
		List<BrandAccount> listbAcc = sas.brandAdminList();
		request.setAttribute("brandAccountList", listbAcc);
	}
	private void create(HttpServletRequest request, HttpServletResponse response) {
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
	private void update(HttpServletRequest request, HttpServletResponse response) {
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
    public BrandAccountController() {
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
