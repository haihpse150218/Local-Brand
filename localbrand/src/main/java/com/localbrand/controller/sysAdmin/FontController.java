package com.localbrand.controller.sysAdmin;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.BrandAccount;
import com.localbrand.entities.SystemAccount;

/**
 * Servlet implementation class FontController
 */
@WebServlet(urlPatterns = "/sysAdmin/*")
public class FontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	
            throws ServletException, IOException {
		HttpSession session =  request.getSession();
        response.setContentType("text/html;charset=UTF-8"); 
        String page = "/sysAdmin";
        String url= request.getPathInfo(); 
        System.out.println("url:" + url);
        if(url == null) {
        	SystemAccount sysAdmin = (SystemAccount) session.getAttribute("sysAdmin");
        	if(sysAdmin == null) {
        		url = "/login/index.do";
        	}else {
        		System.out.println("admin"+sysAdmin.getName());
        		url ="/home/index.do";
        	}
        }
        System.out.println("url:" + url);
        String controller = url.substring(0, url.lastIndexOf("/"));
        String action=url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
        System.out.println("ServletPath: "+url);
        System.out.println("Controller: "+controller);
        System.out.println("Action: "+action);
        request.setAttribute("controller", controller);
        request.setAttribute("action", action);
        request.setAttribute("page", page);
        request.getRequestDispatcher(page+controller).forward(request, response);
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
