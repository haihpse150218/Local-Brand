package com.localbrand.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Collection;
import com.localbrand.entities.Product;
import com.localbrand.service.IBrandHome;
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
            /*case "category":
            	category(request, response);
            	break;
            case "sortLatest":
            	sortLatest(request, response);
            	break;
            case "sortRating":
            	sortRating(request, response);
            	break;
            case "filterPrice":
            	filterPrice(request, response);
            	break;*/
            default:
                request.setAttribute("controller", "/error");
                request.setAttribute("action", "index");
        }
        request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession session = request.getSession();
    	
    	String brandId			= (String)session.getAttribute("brandId");
    	String txtSearch 		= request.getParameter("txtSearchQuery");
    	String txtCateId 		= request.getParameter("txtCateId");
    	String txtSortby 		= request.getParameter("txtSortby");
    	String txtPriceRange1 	= request.getParameter("txtPriceRange1");
    	String txtPriceRange2 	= request.getParameter("txtPriceRange2");
    	
    	/*if (brandId == null) {
    		brandId = (String)session.getAttribute("brandId");
    	}
    	else {
    		session.setAttribute("brandId", brandId);
    	}
        if (brandId == null) {
        	session.setAttribute("controller", "/error");
			return;
        }*/
        if (txtSearch == null) {
        	txtSearch = (String)request.getAttribute("txtSearchQuery");
        	if (txtSearch == null) {
        		txtSearch = "";
        	}
        }
        
        if (txtCateId == null) {
        	txtCateId = (String)request.getAttribute("txtCateId");
        	if (txtCateId == null || txtCateId == "") {
        		txtCateId = "0";
        	}
        }
        
        if (txtSortby == null) {
        	txtSortby = (String)request.getAttribute("txtSortby");
        	if (txtSortby == null) {
        		txtSortby = "";
        	}
        }
        
        if (txtPriceRange1 == null) {
        	txtPriceRange1 = (String)request.getAttribute("txtPriceRange1");
        	if (txtPriceRange1 == null || txtPriceRange1 == "") {
        		txtPriceRange1 = "0";
        	}
        }
        
        if (txtPriceRange2 == null) {
        	txtPriceRange2 = (String)request.getAttribute("txtPriceRange2");
        	if (txtPriceRange2 == null || txtPriceRange2 == "") {
        		txtPriceRange2 = "10000000";
        	}
        }
        
        request.setAttribute("txtSearchQuery", txtSearch);
        request.setAttribute("txtCateId", txtCateId);
        request.setAttribute("txtSortby", txtSortby);
        request.setAttribute("txtPriceRange1", txtPriceRange1);
        request.setAttribute("txtPriceRange2", txtPriceRange2);
        
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
    	listProduct = brandHomeService.findAllProduct(	Integer.parseInt(brandId),
    													txtSearch,
    													txtCateId,
    													txtSortby,
    													txtPriceRange1,
    													txtPriceRange2
    													);  
    	
    	List<Collection> listCollection = brandHomeService.findAllCollection(Integer.parseInt(brandId));
		request.setAttribute("collections", listCollection);
    	
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
    /*
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
    
    private void filterPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	double range1 = Double.parseDouble(request.getParameter("txtPriceRange1"));
    	double range2 = Double.parseDouble(request.getParameter("txtPriceRange2"));
    	
    	if (range1 > range2) {
    		double t = range1;
    		range1 = range2;
    		range2 = t;
    	}
    	
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
    	listProduct = brandHomeService.findProductsInPriceRange(new double[] {range1, range2}, Integer.parseInt(brandId));  
    	
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
    }*/
    
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
        int pageSize = PAGE_SIZE;
    	PagingService paging = new PagingService();
    	
		List<Product> listProduct = brandHomeService.findAllProductByBrandId(Integer.parseInt(brandId.toString()));
		List<Collection> listCollection = brandHomeService.findAllCollection(Integer.parseInt(brandId.toString()));
		
		request.setAttribute("collections", listCollection);
		
         Integer page = (Integer)session.getAttribute("brandhomePage");
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

