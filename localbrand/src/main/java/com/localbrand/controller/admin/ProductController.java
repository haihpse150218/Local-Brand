package com.localbrand.controller.admin;

import java.io.IOException;
import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
			case "sort":
				sort(request, response);
				break;
			case "create":
				create(request, response);
				break;
			case "search":
				search(request, response);
				break;
			case "delete":
				delete(request, response);
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

	private void search(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		List<ProductRevenue> listProduct = HomeAdmin.getInstance().listReportProduct(listOrderObject);
		List<ProductRevenue> listAllProduct =  HomeAdmin.getInstance().ListAllReportProduct(listProduct, admin.getBrandId().getId());
		String searchByNameProduct = request.getParameter("searchByNameProduct");
		List<ProductRevenue> listResult = new ArrayList<>();
		
		for (ProductRevenue productRevenue : listAllProduct) {
			System.out.println("check "+ productRevenue.getName().contains(searchByNameProduct));
			if(productRevenue.getName().toLowerCase().contains(searchByNameProduct.toLowerCase())) {
				System.out.println("check2");
				listResult.add(productRevenue);
				System.out.println("check2" + listResult);
			}
		}
		
		
		CategoryFacade cf = new CategoryFacade();
		List<String> listStatus = new ArrayList<>(Arrays.asList("Active", "New", "Disable", "Hot"));
		request.setAttribute("listStatus", listStatus);
		List<Category> listCate;
		try {
			listCate = cf.findAll();
			request.setAttribute("listCate", listCate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("listProduct", listResult);
		request.setAttribute("action", "index");
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		
		ProductFacade pf = new ProductFacade();
		Integer id = Integer.parseInt(request.getParameter("productId"));
		try {
			Product p = pf.find(id);
			p.setStatus("Disable");
			System.out.println("check"+ p.toString());
			pf.edit(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index(request, response);
		
		request.setAttribute("action", "index");
		
	}

	public void create(HttpServletRequest request, HttpServletResponse response) {
		ProductFacade pf = new ProductFacade();
		CategoryFacade cf = new CategoryFacade();
		BrandCategoryFacade bcf = new BrandCategoryFacade();
		try {

			HttpSession session = request.getSession();
			BrandAccount admin = (BrandAccount) session.getAttribute("admin");
			// create product Master
			String name = request.getParameter("txtName1");
			Integer cateId = Integer.parseInt(request.getParameter("txtCategory"));
			Category cate = cf.find(cateId);
			String status = request.getParameter("txtStatus");
			String description = request.getParameter("txtDescription");
			Double price = Double.parseDouble(request.getParameter("txtPrice1"));
			String srcImg = request.getParameter("txtImage1");
			String size = request.getParameter("txtSize1");
			String color = request.getParameter("txtColor1");
			BrandCategoryPK keyId = new BrandCategoryPK(cate.getId(), admin.getBrandId().getId());
			if (bcf.find(keyId) == null) {
				BrandCategory bc = new BrandCategory(keyId);
				bc.setName(cate.getName());
				bc.setStatus(true);
				bcf.create(bc);
			}
			Product p = new Product();
			p.setCateId(cate);
			p.setName(name);
			p.setPrice(price);
			p.setStatus(status);
			p.setImgMaster(srcImg);
			p.setImgChild("");
			p.setSize(size);
			p.setIsMaster(true);
			p.setColor(color);
			p.setDescription(description);
			p.setCreateDate(new java.util.Date());
			p.setContainer(100);
			p.setDiscount(0.0);
			p.setStars(0.0);
			p.setBrandId(admin.getBrandId());
			Product productId = new Product();
			p.setParentId(productId);
			pf.create(p);
			for (Product pParent : pf.findAll()) {
				if(pParent.getIsMaster() 
						&& pParent.getName().equalsIgnoreCase(p.getName()) 
						&& pParent.getBrandId().getId() == p.getBrandId().getId()) {
					p = pParent;
				}
			}
			// Add more dependence
			int i = 2;
			while(true) {
				String srcImgChild = request.getParameter("txtImage" + i);
				
				String sizeChild = request.getParameter("txtSize" + i);
				
				String colorChild = request.getParameter("txtColor" + i);
				
				if(colorChild == null) {
					break;
				}else {
					Product pChild = new Product();
					pChild.setColor(colorChild);
					pChild.setSize(sizeChild);
					pChild.setImgChild(srcImgChild);
					pChild.setPrice(price);
					//
					pChild.setParentId(p);
					pChild.setIsMaster(false);
					pChild.setImgMaster("");
					pChild.setName(name);
					//
					pChild.setCateId(cate);
					pChild.setStatus(status);
					pChild.setDescription(description);
					pChild.setCreateDate(new java.util.Date());
					pChild.setContainer(100);
					pChild.setDiscount(0.0);
					pChild.setStars(0.0);
					pChild.setBrandId(admin.getBrandId());
					
					pf.create(pChild);
				}
				i++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index(request, response);
		request.setAttribute("action", "index");
	}

	private void sort(HttpServletRequest request, HttpServletResponse response) {
		String op = request.getParameter("op");
		
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");

		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		List<ProductRevenue> listProduct = HomeAdmin.getInstance().listReportProduct(listOrderObject);
		List<ProductRevenue> listAllProduct =  HomeAdmin.getInstance().ListAllReportProduct(listProduct, admin.getBrandId().getId());

		
		switch (op) {
		case "numberOrder":
			Collections.sort(listAllProduct, new Comparator<ProductRevenue>() {
				@Override
				public int compare(ProductRevenue o1, ProductRevenue o2) {
					return o2.getNumberOrdered() - o1.getNumberOrdered();
				}
			});
			break;
		case "turnover":
			Collections.sort(listAllProduct, new Comparator<ProductRevenue>() {
				@Override
				public int compare(ProductRevenue o1, ProductRevenue o2) {
					return o2.getNumberDelivered() - o1.getNumberDelivered();
				}
			});
			break;
		case "proceeds":
			Collections.sort(listAllProduct, new Comparator<ProductRevenue>() {
				@Override
				public int compare(ProductRevenue o1, ProductRevenue o2) {
					return (int) (o2.getProceeds() - o1.getProceeds());
				}
			});
			break;
		default:
			break;
		}
		
		CategoryFacade cf = new CategoryFacade();
		List<String> listStatus = new ArrayList<>(Arrays.asList("Active", "New", "Disable", "Hot"));
		request.setAttribute("listStatus", listStatus);
		List<Category> listCate;
		try {
			listCate = cf.findAll();
			request.setAttribute("listCate", listCate);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		request.setAttribute("controller", "/product");
		request.setAttribute("action", "index");
		request.setAttribute("listProduct", listAllProduct);
	}

	
	private void index(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		BrandCategoryFacade bcf = new BrandCategoryFacade();
		CategoryFacade cf = new CategoryFacade();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		List<OrderObject> listOrderObject = HomeAdmin.getInstance().getOrderListByBrandId(admin.getBrandId().getId());
		List<ProductRevenue> listProduct = HomeAdmin.getInstance().listReportProduct(listOrderObject);
		List<ProductRevenue> listAllProduct =  HomeAdmin.getInstance().ListAllReportProduct(listProduct, admin.getBrandId().getId());
	
		
		List<String> listStatus = new ArrayList<>(Arrays.asList("Active", "New", "Disable", "Hot"));
		request.setAttribute("listStatus", listStatus);
		List<Category> listCate;
		try {
			listCate = cf.findAll();
			request.setAttribute("listCate", listCate);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		request.setAttribute("listProduct", listAllProduct);
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