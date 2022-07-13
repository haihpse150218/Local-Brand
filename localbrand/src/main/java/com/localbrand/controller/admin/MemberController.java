package com.localbrand.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.Brand;
import com.localbrand.entities.BrandAccount;
import com.localbrand.service.implement.HomeAdmin;
import com.localbrand.service.implement.LoginAdmin;
import com.localbrand.service.implement.MemberAdminService;
import com.localbrand.sessionbeans.BrandAccountFacade;
import com.localbrand.sessionbeans.BrandFacade;

/**
 * Servlet implementation class MemberController
 */

@WebServlet(name = "MemberController", urlPatterns = { "/admin/member" })
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getAttribute("action").toString();
		
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		if (admin != null) {
			if (admin.getRole() != false) {
				switch (action) {
				case "index":
					index(request, response);
					break;
				case "insert":
					create(request, response);
					break;
				case "update":
					update(request, response);
					break;
				case "resetpassword":
					reset(request, response);
					break;
				case "disable":
					disable(request, response);
					break;
				case "remove":
					disable(request, response);
					break;

				default:
					request.setAttribute("controller", "/error");
					request.setAttribute("action", "index");
				}
			} else {
				request.setAttribute("controller", "/member");
				request.setAttribute("action", "norole");
			}

		} else {
			request.setAttribute("controller", "/login");
			request.setAttribute("action", "index");
		}
		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}

	private void disable(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		List<BrandAccount> listMember = MemberAdminService.getInstance()
				.getAllMemberByBrandId(admin.getBrandId().getId());
		Integer memberId = Integer.parseInt(request.getParameter("id"));
		BrandAccountFacade bf = new BrandAccountFacade();
		
		if (memberId != admin.getId()) {
			Integer status = MemberAdminService.getInstance().convertStatusBrandAccount(request.getParameter("status"));
			for (BrandAccount brandAccount : listMember) {
				if (brandAccount.getId() == memberId) {
					if (brandAccount.getBrandId().getId() == admin.getBrandId().getId()
							|| brandAccount.getRole() == true) {

					} else {
						brandAccount.setStatus(status);
						try {
							bf.edit(brandAccount);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
				}
			}
		}
		index(request, response);
		request.setAttribute("action", "index");
	}

	private void reset(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		List<BrandAccount> listMember = MemberAdminService.getInstance()
				.getAllMemberByBrandId(admin.getBrandId().getId());
		Integer memberId = Integer.parseInt(request.getParameter("memberId"));
		for (BrandAccount brandAccount : listMember) {
			if (brandAccount.getId() == memberId) {
				brandAccount.setPassword("6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b");
			}
		}
		index(request, response);
		request.setAttribute("action", "index");

	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		BrandAccountFacade bf = new BrandAccountFacade();
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		List<BrandAccount> listMember = MemberAdminService.getInstance()
				.getAllMemberByBrandId(admin.getBrandId().getId());
		String mess = "";
		try {
			Integer id = Integer.parseInt(request.getParameter("txtId"));
			String name = request.getParameter("txtName").trim();
			request.setAttribute("name", name);
			int status = MemberAdminService.getInstance().convertStatusBrandAccount(request.getParameter("txtStatus"));
			request.setAttribute("status", status);
			BrandAccount updateMem = bf.find(id);
			updateMem.setName(name);
			updateMem.setStatus(status);
			bf.edit(updateMem);
		} catch (Exception e) {
			mess = "Error When Update new Employee:\n" + e.getMessage();
			request.setAttribute("mess", mess);
		}
		index(request, response);
		
		request.setAttribute("action", "index");
	}

	private void create(HttpServletRequest request, HttpServletResponse response) {
		BrandAccountFacade bf = new BrandAccountFacade();
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		String mess = "";
		try {
			String name = request.getParameter("txtName").trim();
			

			String username = request.getParameter("txtUserName").trim();

			for (BrandAccount a : bf.findAll()) {
				
				if (a.getUsername().equalsIgnoreCase(username)) {
					System.out.println("Username is exited!!");

					throw new Exception("Username is exited!!!");
				}
			}
			String password = request.getParameter("txtPassword");
			password = LoginAdmin.getInstance().encodePass(password);

			int status = MemberAdminService.getInstance().convertStatusBrandAccount(request.getParameter("txtStatus"));
			
			Boolean role = false;// employee
			Brand brandId = admin.getBrandId();
			BrandAccount newMember = new BrandAccount();
			newMember.setName(name);
			newMember.setUsername(username);
			newMember.setPassword(password);
			newMember.setStatus(status);
			newMember.setRole(role);
			newMember.setBrandId(brandId);
			bf.create(newMember);
			
		} catch (Exception e) {
			mess = "Error When Create new Employee:\n" + e.getMessage();
			request.setAttribute("mess", mess);
		}
		System.out.println("mess: "+mess);
		request.setAttribute("action", "index");
		index(request, response);
		
		

	}

	private void index(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		List<BrandAccount> listMember = MemberAdminService.getInstance()
				.getAllMemberByBrandId(admin.getBrandId().getId());
		request.setAttribute("listMember", listMember);

		// -------load list status

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
