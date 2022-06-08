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

import com.localbrand.entities.Brand;
import com.localbrand.entities.Collection;
import com.localbrand.entities.MembershipTier;
import com.localbrand.entities.Payment;
import com.localbrand.sessionbeans.CollectionFacade;
import com.localbrand.sessionbeans.MembershipTierFacade;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns="/web/home")
public class HomeController extends HttpServlet {
//	PaymentFacade pf = new PaymentFacade();
	MembershipTierFacade mstf = new MembershipTierFacade();
	CollectionFacade cf = new CollectionFacade();
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
    	List<MembershipTier> list = new ArrayList<>();
    	List<Collection> listc = new ArrayList<>();
    	Collection c = new Collection();
    	Brand bid = new Brand();
    	bid.setId(1);
    	int count=0;
    	int id = 4;
    	int[] range = new int[]{1,2};
    	System.out.println("bid:" + bid.getId());

    	try {
//    		c.setId(8);
//    		c.setName("dds");
//    		c.setDescription("dsvs");
//    		c.setImageUrl("ulala");
//    		c.setStatus(0);
//    		c.setBrandId(bid);
//    		cf.edit(c);
//    		listc = cf.findRange(range);
//    		count = cf.count();
    		cf.remove(8);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("vao roi 3"+list.toString());
        String hello = "Hai Dep Chai.";
        request.setAttribute("listMembershipTier", list);
        request.setAttribute("listCollection", listc);
        request.setAttribute("clistPayment", count);
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
