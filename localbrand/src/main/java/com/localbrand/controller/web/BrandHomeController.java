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
import javax.servlet.http.HttpSession;

import com.localbrand.entities.Product;
import com.localbrand.sessionbeans.BrandCategoryFacade;
import com.localbrand.sessionbeans.ProductFacade;


/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns="/web/brandhome")
public class BrandHomeController extends HttpServlet {
	ProductFacade pf = new ProductFacade();
	BrandCategoryFacade bcfc = new BrandCategoryFacade();
	private static final long serialVersionUID = 1L;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getAttribute("action").toString();
        switch (action) {
            case "index":
                index(request, response);
                break;
            default:
                request.setAttribute("controller", "error");
                request.setAttribute("action", "index");
        }
        request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
    }
    private void index(HttpServletRequest request, HttpServletResponse response) {
    	
    	 int pageSize = 6;                       
         HttpSession session = request.getSession();
         //Xac dinh so thu tu cua trang hien tai
         Integer page = (Integer) session.getAttribute("brandhomePage");
         Integer box = (Integer) session.getAttribute("box");
         
         if (page == null) {
             page = 1;
         }
         if (box == null) {
			box = 1;
		}
         //Xac dinh tong so trang
         Integer totalPage = (Integer) session.getAttribute("totalBrandhomePage");
         if (totalPage == null) {
             int count = pf.count();
             System.out.println("count"+count);
             totalPage = (int) Math.ceil((double) (count) / pageSize);
             System.out.println("total================="+totalPage);
         }
         String op = request.getParameter("op");
         if (op == null) {
             op = "FirstPage";
         }
         switch (op) {
             case "FirstPage":
            	 System.out.println("sc1:" + page);
                 page = 1;
                 break;
             case "PreviousPage":
                 if (page > 1) {
                     page--;
                 }
                 System.out.println("sc2:" + page);
                 break;
             case "NextPage":
                 if (page < totalPage) {
                     page++;
                 }
                 System.out.println("sc3:" + page);
                 break;
             case "LastPage":
                 page = totalPage;
                 System.out.println("sc4:" + page);
                 break;
             case "GotoPage":
                 page = Integer.parseInt(request.getParameter("gotoPage"));
                 System.out.println("sc5:" + page);
                 break;
         }
         int n1 = (page - 1) * pageSize + 1;

         int n2 = n1 + pageSize - 1;
         List<Product> list = null;
         try {
             list = pf.findRange(new int[]{n1, n2});
         } catch (SQLException ex) {
        	 System.out.println(ex);
         }
         //Luu thong tin vao session va request
         List<Integer> listNumberBox = new ArrayList<>();

         if(page == 1 || totalPage <= 3) {
        	 listNumberBox.add(1);
             listNumberBox.add(2);
             listNumberBox.add(3);

         }
         if(page == totalPage) {
        	 listNumberBox.add(page-2);
             listNumberBox.add(page-1);
             listNumberBox.add(page);
         }
         if(page > 1 && page < totalPage) {
        	 listNumberBox.add(page-1);
             listNumberBox.add(page);
             listNumberBox.add(page+1);
         }
         
         
         session.setAttribute("listNumberBox", listNumberBox);
         session.setAttribute("brandhomePage", page);
         session.setAttribute("totalBrandhomePage", totalPage);
         request.setAttribute("list", list);
         
    	List<Product> listProduct;
    	
    	
		try {
			listProduct = pf.findAll();
			for (Product p : listProduct) {
	    		System.out.println("product: "+ p.getName());				
			}
			System.out.println(pf.count());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String uri = request.getServletPath();
        String controller = uri.substring(uri.lastIndexOf("/"));
        session.setAttribute("uri", controller);
    	
    	
    	
    	

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

