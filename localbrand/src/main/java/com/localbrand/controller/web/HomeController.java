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

import com.localbrand.entities.Category;
import com.localbrand.sessionbeans.CategoryFacade;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns="/web/home")
public class HomeController extends HttpServlet {
	CategoryFacade cateFacade = new CategoryFacade();
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
    	List<Category> list = new ArrayList<>();
    	try {
    		/*int[] range = {2, 6};
			list = cateFacade.findRange(range);*/
    		
    		Category newCate = new Category(14, "Man's Jeans");
    		
    		cateFacade.create(newCate);
    		
    		list = cateFacade.findAll();
    		
    		for (Category category : list) {
				System.out.println(category.getId() + ": " + category.getName());
			}
    		
    		newCate = cateFacade.find(14);
    		newCate.setName("Woman's Jeans");
    		cateFacade.edit(newCate);
    		
    		System.out.println("===================================");
    		
    		for (Category category : cateFacade.findRange(new int[] {5, 100})) {
				System.out.println(category.getName());
			}
    		
    		System.out.println("===================================");
    		
    		cateFacade.remove(14);
    		System.out.println(cateFacade.count());
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	/*System.out.println("category: " + list.toString());*/

        String hello = "Hai Dep Chai.";
        request.setAttribute("listCategory", list);

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
