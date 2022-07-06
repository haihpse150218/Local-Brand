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

import com.localbrand.entities.Brand;
import com.localbrand.entities.Product;
import com.localbrand.service.implement.BrandHomeService;
import com.localbrand.service.implement.PagingService;
import com.localbrand.sessionbeans.BrandCategoryFacade;
import com.localbrand.sessionbeans.ProductFacade;


/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns="/web/brandhome")
public class BrandHomeController extends HttpServlet {
	
	private static final int PAGE_SIZE = 6;
	
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
            case "search":
            	search(request, response);
            	break;
            case "category":
            	category(request, response);
            	break;
            case "sortLatest":
            	sortLatest(request, response);
            	break;
            case "sortRating":
            	sortRating(request, response);
            	break;
            default:
                request.setAttribute("controller", "/error");
                request.setAttribute("action", "index");
        }
        request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
    }
    
    

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String brandId = request.getParameter("id");
    	String searchQuery = request.getParameter("search_query");
    	HttpSession session = request.getSession();
    	if(brandId == null) {
    		brandId = (String) session.getAttribute("brandId");
    	}
    	else {
    		session.setAttribute("brandId", brandId);
    	}
        if(brandId == null) {
        	request.setAttribute("controller", "/error");
			return;
        }
        BrandHomeService brandHomeService = new BrandHomeService();
        Brand currentBrand = null;
    	try {
    		currentBrand = brandHomeService.findBrand(Integer.parseInt(brandId));
    		request.setAttribute("brand", currentBrand);
    	} catch (Exception e) {
    		request.setAttribute("BRAND_ERROR", "Loading brand error!");
    	}
        if(currentBrand == null) {
        	request.setAttribute("controller", "/error");
			return;
        }
      //Paging
        int pageSize = PAGE_SIZE;
    	PagingService paging = new PagingService();
    	
    	List<Product> listProduct = new ArrayList<>();
    	listProduct = brandHomeService.findAllProductByName(searchQuery, Integer.parseInt(brandId));  
    	
        Integer page = (Integer) session.getAttribute("brandhomePage");
        int count = 0;
        count = listProduct.size();
        
        Integer totalPage = (int) Math.ceil((double) (count) / pageSize);;
        System.out.println("totalPage: "+ totalPage);
        String op = request.getParameter("op");
        Integer gotoPage = null;
        
        if(request.getParameter("gotoPage") != null) {
        	gotoPage = Integer.parseInt(request.getParameter("gotoPage"));
        }
        page = paging.getPage(op,totalPage,page,gotoPage);
        
        System.out.println("page: "+page);
        
        int n1 = (page - 1) * pageSize ;
        int n2 = 0;
        
        if((count - n1) <= pageSize) {
        	n2 = count;
        } else {
        	n2 = n1 + pageSize;
        }
        
        List<Product> list = listProduct.subList(n1, n2);
        List<Integer> listNumberBox = paging.getListNumberBox(page, totalPage);
        
        session.setAttribute("listNumberBox", listNumberBox);
        session.setAttribute("brandhomePage", page);
        session.setAttribute("totalBrandhomePage", totalPage);
        request.setAttribute("list", list);
        request.setAttribute("brand", currentBrand);
        
        String uri = request.getServletPath();
        String controller = uri.substring(uri.lastIndexOf("/"));
        
        session.setAttribute("uri", controller);
        request.setAttribute("action", "index");
    }
    
    private void category(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int cateId = Integer.parseInt(request.getParameter("cateId"));
    	
    	HttpSession session = request.getSession();
    	
    	String brandId = (String) session.getAttribute("brandId");
    	
        BrandHomeService brandHomeService = new BrandHomeService();
        Brand currentBrand = null;
    	try {
    		currentBrand = brandHomeService.findBrand(Integer.parseInt(brandId));
    		request.setAttribute("brand", currentBrand);
    	} catch (Exception e) {
    		request.setAttribute("BRAND_ERROR", "Loading brand error!");
    	}
        if(currentBrand == null) {
        	request.setAttribute("controller", "/error");
			return;
        }
        //Paging
        int pageSize = PAGE_SIZE;
    	PagingService paging = new PagingService();
    	
    	List<Product> listProduct = new ArrayList<>();
    	listProduct = brandHomeService.findAllProductByCategory(cateId, Integer.parseInt(brandId));  
    	
        Integer page = (Integer) session.getAttribute("brandhomePage");
        int count = 0;
        count = listProduct.size();
        
        Integer totalPage = (int) Math.ceil((double) (count) / pageSize);;
        System.out.println("totalPage: "+ totalPage);
        String op = request.getParameter("op");
        Integer gotoPage = null;
        
        if(request.getParameter("gotoPage") != null) {
        	gotoPage = Integer.parseInt(request.getParameter("gotoPage"));
        }
        page = paging.getPage(op,totalPage,page,gotoPage);
        
        System.out.println("page: "+page);
        
        int n1 = (page - 1) * pageSize ;
        int n2 = 0;
        
        if((count - n1) <= pageSize) {
        	n2 = count;
        } else {
        	n2 = n1 + pageSize;
        }
        
        List<Product> list = listProduct.subList(n1, n2);
        List<Integer> listNumberBox = paging.getListNumberBox(page, totalPage);
        
        session.setAttribute("listNumberBox", listNumberBox);
        session.setAttribute("brandhomePage", page);
        session.setAttribute("totalBrandhomePage", totalPage);
        request.setAttribute("list", list);
        request.setAttribute("brand", currentBrand);
        
        String uri = request.getServletPath();
        String controller = uri.substring(uri.lastIndexOf("/"));
        
        session.setAttribute("uri", controller);
        request.setAttribute("action", "index");
    }
    
    private void sortLatest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	
    	String brandId = (String) session.getAttribute("brandId");
    	
        BrandHomeService brandHomeService = new BrandHomeService();
        Brand currentBrand = null;
    	try {
    		currentBrand = brandHomeService.findBrand(Integer.parseInt(brandId));
    		request.setAttribute("brand", currentBrand);
    	} catch (Exception e) {
    		request.setAttribute("BRAND_ERROR", "Loading brand error!");
    	}
        if(currentBrand == null) {
        	request.setAttribute("controller", "/error");
			return;
        }
        //Paging
        int pageSize = PAGE_SIZE;
    	PagingService paging = new PagingService();
    	
    	List<Product> listProduct = new ArrayList<>();
    	listProduct = brandHomeService.sortAllByLatest(Integer.parseInt(brandId));
    	
        Integer page = (Integer) session.getAttribute("brandhomePage");
        int count = 0;
        count = listProduct.size();
        
        Integer totalPage = (int) Math.ceil((double) (count) / pageSize);;
        System.out.println("totalPage: "+ totalPage);
        String op = request.getParameter("op");
        Integer gotoPage = null;
        
        if(request.getParameter("gotoPage") != null) {
        	gotoPage = Integer.parseInt(request.getParameter("gotoPage"));
        }
        page = paging.getPage(op,totalPage,page,gotoPage);
        
        System.out.println("page: "+page);
        
        int n1 = (page - 1) * pageSize ;
        int n2 = 0;
        
        if((count - n1) <= pageSize) {
        	n2 = count;
        } else {
        	n2 = n1 + pageSize;
        }
        
        List<Product> list = listProduct.subList(n1, n2);
        List<Integer> listNumberBox = paging.getListNumberBox(page, totalPage);
        
        session.setAttribute("listNumberBox", listNumberBox);
        session.setAttribute("brandhomePage", page);
        session.setAttribute("totalBrandhomePage", totalPage);
        request.setAttribute("list", list);
        request.setAttribute("brand", currentBrand);
        
        String uri = request.getServletPath();
        String controller = uri.substring(uri.lastIndexOf("/"));
        
        session.setAttribute("uri", controller);
        request.setAttribute("action", "index");
    }

    private void sortRating(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	
    	String brandId = (String) session.getAttribute("brandId");
    	
        BrandHomeService brandHomeService = new BrandHomeService();
        Brand currentBrand = null;
    	try {
    		currentBrand = brandHomeService.findBrand(Integer.parseInt(brandId));
    		request.setAttribute("brand", currentBrand);
    	} catch (Exception e) {
    		request.setAttribute("BRAND_ERROR", "Loading brand error!");
    	}
        if(currentBrand == null) {
        	request.setAttribute("controller", "/error");
			return;
        }
        //Paging
        int pageSize = PAGE_SIZE;
    	PagingService paging = new PagingService();
    	
    	List<Product> listProduct = new ArrayList<>();
    	listProduct = brandHomeService.sortAllByRating(Integer.parseInt(brandId));
    	
        Integer page = (Integer) session.getAttribute("brandhomePage");
        int count = 0;
        count = listProduct.size();
        
        Integer totalPage = (int) Math.ceil((double) (count) / pageSize);;
        System.out.println("totalPage: "+ totalPage);
        String op = request.getParameter("op");
        Integer gotoPage = null;
        
        if(request.getParameter("gotoPage") != null) {
        	gotoPage = Integer.parseInt(request.getParameter("gotoPage"));
        }
        page = paging.getPage(op,totalPage,page,gotoPage);
        
        System.out.println("page: "+page);
        
        int n1 = (page - 1) * pageSize ;
        int n2 = 0;
        
        if((count - n1) <= pageSize) {
        	n2 = count;
        } else {
        	n2 = n1 + pageSize;
        }
        
        List<Product> list = listProduct.subList(n1, n2);
        List<Integer> listNumberBox = paging.getListNumberBox(page, totalPage);
        
        session.setAttribute("listNumberBox", listNumberBox);
        session.setAttribute("brandhomePage", page);
        session.setAttribute("totalBrandhomePage", totalPage);
        request.setAttribute("list", list);
        request.setAttribute("brand", currentBrand);
        
        String uri = request.getServletPath();
        String controller = uri.substring(uri.lastIndexOf("/"));
        
        session.setAttribute("uri", controller);
        request.setAttribute("action", "index");
    }
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String brandId = request.getParameter("id");
    	HttpSession session = request.getSession();
    	if(brandId == null) {
    		brandId = (String) session.getAttribute("brandId");
    	}
    	else {
    		session.setAttribute("brandId", brandId);
    	}
        if(brandId == null) {
        	request.setAttribute("controller", "/error");
			return;
        }
        BrandHomeService brandHomeService = new BrandHomeService();
        Brand currentBrand = null;
    	try {
    		currentBrand = brandHomeService.findBrand(Integer.parseInt(brandId));
    		request.setAttribute("brand", currentBrand);
    	} catch (Exception e) {
    		request.setAttribute("BRAND_ERROR", "Loading brand error!");
    	}
        if(currentBrand == null) {
        	request.setAttribute("controller", "/error");
			return;
        }
      //Paging
        int pageSize = 6;
    	PagingService paging = new PagingService();
    	
    	List<Product> listProduct = new ArrayList<>();
		listProduct = brandHomeService.findAllProductByBrandId(Integer.parseInt(brandId.toString()));               
         Integer page = (Integer) session.getAttribute("brandhomePage");
         int count = 0;
         count = listProduct.size();
         Integer totalPage = (int) Math.ceil((double) (count) / pageSize);;
         System.out.println("totalPage: "+ totalPage);
         String op = request.getParameter("op");
         Integer gotoPage = null;
         if(request.getParameter("gotoPage") != null) {
        	  gotoPage = Integer.parseInt(request.getParameter("gotoPage"));
         }
         page = paging.getPage(op,totalPage,page,gotoPage);
         System.out.println("page: "+page);
         int n1 = (page - 1) * pageSize ;
         int n2 = 0;
         if((count - n1) <= pageSize) {
        	 n2 = count;
         }else {
        	 n2 = n1 + pageSize;
         }
         List<Product> list = listProduct.subList(n1, n2);
         List<Integer> listNumberBox = paging.getListNumberBox(page, totalPage);
         session.setAttribute("listNumberBox", listNumberBox);
         session.setAttribute("brandhomePage", page);
         session.setAttribute("totalBrandhomePage", totalPage);
         request.setAttribute("list", list);
         request.setAttribute("brand", currentBrand);
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

