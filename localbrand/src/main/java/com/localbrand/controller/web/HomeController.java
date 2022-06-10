package com.localbrand.controller.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.localbrand.entities.Collection;
import com.localbrand.entities.CollectionDetail;
import com.localbrand.entities.CollectionDetailPK;
import com.localbrand.entities.Product;
import com.localbrand.sessionbeans.CollectionDetailFacade;
import com.localbrand.sessionbeans.MembershipTierFacade;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns="/web/home")
public class HomeController extends HttpServlet {
	CollectionDetailFacade cdf = new CollectionDetailFacade();
	MembershipTierFacade mstf = new MembershipTierFacade();
	private static final long serialVersionUID = 1L;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getAttribute("action").toString();
        System.out.println("vao roi:" + action);
        switch (action) {
            case "index":
            	System.out.println("vao roi2:" + action);
                index(request, response);
                break;
            default:
                request.setAttribute("controller", "error");
                request.setAttribute("action", "index");
        }
        request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
    }
    private void index(HttpServletRequest request, HttpServletResponse response) {
    	List<CollectionDetail> list = new ArrayList<>();
    	CollectionDetail cd = new CollectionDetail();
    	CollectionDetailPK pk = new CollectionDetailPK();
    	Collection c = new Collection();
    	Product p = new Product();
    	int count = 0;
    	int[] range = new int[]{1,2};
    	try {
    		
			list = cdf.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	System.out.println("vao roi 3"+list.toString());

        String hello = "Hai Dep Chai.";
        request.setAttribute("listMembershipTier", list);
//        request.setAttribute("list", cd);
        request.setAttribute("count", count);

        request.setAttribute("hello", hello);
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 processRequest(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 processRequest(request, response);
		doGet(request, response);
	}

}
