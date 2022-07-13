package com.localbrand.controller.admin;

import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.localbrand.entities.BrandAccount;
import com.localbrand.entities.BrandCategory;
import com.localbrand.entities.BrandCategoryPK;
import com.localbrand.entities.Category;
import com.localbrand.entities.Product;
import com.localbrand.service.implement.HomeAdmin;
import com.localbrand.service.models.OrderObject;
import com.localbrand.service.models.ProductRevenue;
import com.localbrand.sessionbeans.BrandCategoryFacade;
import com.localbrand.sessionbeans.CategoryFacade;
import com.localbrand.sessionbeans.ProductFacade;

@WebServlet(name = "ProductControllerAdmin", urlPatterns = { "/admin/product" })
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getAttribute("action").toString();
		System.out.println("actione ne:" + action);
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		if (admin != null) {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "fillter":
				fillter(request, response);
				break;
			case "sort":
				sort(request, response);
				break;
			case "create":
				create(request, response);
				break;
			default:
				request.setAttribute("controller", "/error");
				request.setAttribute("action", "index");
			}

		} else {
			request.setAttribute("controller", "/login");
			request.setAttribute("action", "index");
		}

		request.getRequestDispatcher(Common.LAYOUT).forward(request, response);
	}

	public void create(HttpServletRequest request, HttpServletResponse response) {
		ProductFacade pf = new ProductFacade();
		CategoryFacade cf= new CategoryFacade();
		BrandCategoryFacade bcf = new BrandCategoryFacade();
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		//product
		String name = request.getParameter("txtName1");
		
		Double price = Double.parseDouble(request.getParameter("txtPrice1"));
		String cateName = request.getParameter("txtCategory");
		Category cate = null;
		String status = request.getParameter("txtStatus");
		String srcImg = request.getParameter("txtImage1");
		String size = request.getParameter("txtSize1");
		String color = request.getParameter("txtColor1");
		String description = request.getParameter("txtDescription");
		try {
			for (Category c : cf.findAll()) {
				if(c.getName().equalsIgnoreCase(cateName)) {
					cate = c;
					
					BrandCategoryPK keyId = new BrandCategoryPK(cate.getId(),admin.getBrandId().getId());
					if(bcf.find(keyId) == null) {
						BrandCategory bc = new BrandCategory(keyId);
						bc.setName(c.getName());
						bc.setStatus(true);
						System.out.println("brandCate: "+ bc);
						bcf.create(bc);
					}
					break;	
				}
				
			}
			Product p = new Product();
			p.setCateId(cate);
			p.setName(name);
			p.setPrice(price);
			p.setStatus(status);
			p.setImgMaster(srcImg);
			p.setSize(size);
			p.setColor(color);
			p.setDescription(description);
			System.out.println("product" + p);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

	private void sort(HttpServletRequest request, HttpServletResponse response) {
		String op = request.getParameter("op");
		System.out.println("op: " + op);
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");

		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		List<ProductRevenue> listProduct = HomeAdmin.getInstance().listReportProduct(listOrderObject);
		
		System.out.println(op+"before: " +listProduct.toString());
		switch (op) {
		case "numberOrder":
			Collections.sort(listProduct, new Comparator<ProductRevenue>() {
				@Override
				public int compare(ProductRevenue o1, ProductRevenue o2) {
					return o2.getNumberOrdered() - o1.getNumberOrdered();
				}
			});
			break;
		case "turnover":
			Collections.sort(listProduct, new Comparator<ProductRevenue>() {
				@Override
				public int compare(ProductRevenue o1, ProductRevenue o2) {
					return o2.getNumberDelivered() - o1.getNumberDelivered();
				}
			});
			break;
		case "proceeds":
			Collections.sort(listProduct, new Comparator<ProductRevenue>() {
				@Override
				public int compare(ProductRevenue o1, ProductRevenue o2) {
					return (int) (o2.getProceeds() - o1.getProceeds());
				}
			});
			break;
		default:
			break;
		}
		System.out.println(op+": " +listProduct.toString());
		request.setAttribute("controller", "/product");
		request.setAttribute("action", "index");
		request.setAttribute("listProduct", listProduct);

	}

	private void fillter(HttpServletRequest request, HttpServletResponse response) {
		String op = request.getParameter("op");
		System.out.println("op: " + op);
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");

		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		List<ProductRevenue> listProduct = new ArrayList<ProductRevenue>();
		switch (op) {
		case "all":
			break;
			
		case "aWeek":
			
			break;
		default:
			break;
		}
		request.setAttribute("controller", "/product");
		request.setAttribute("action", "index");
		request.setAttribute("listProduct", listProduct);

	}

	private void index(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BrandCategoryFacade bcf = new BrandCategoryFacade();
		CategoryFacade cf = new CategoryFacade();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		List<ProductRevenue> listProduct = HomeAdmin.getInstance().listReportProduct(listOrderObject);
		
		List<String> listStatus = new ArrayList<>(Arrays.asList("Active", "New", "Disable", "Hot"));
		request.setAttribute("listStatus", listStatus);
		List<Category> listCate;
		try {
			listCate = cf.findAll();
			request.setAttribute("listCate", listCate);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		request.setAttribute("listProduct", listProduct);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}