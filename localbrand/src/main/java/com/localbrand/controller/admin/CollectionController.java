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

import com.localbrand.entities.Brand;
import com.localbrand.entities.BrandAccount;
import com.localbrand.entities.BrandCategory;
import com.localbrand.entities.BrandCategoryPK;
import com.localbrand.entities.Category;
import com.localbrand.entities.Collection;
import com.localbrand.entities.CollectionDetail;
import com.localbrand.entities.CollectionDetailPK;
import com.localbrand.entities.Product;
import com.localbrand.service.implement.AdminCollectionService;
import com.localbrand.service.implement.HomeAdmin;
import com.localbrand.service.models.OrderObject;
import com.localbrand.service.models.ProductRevenue;
import com.localbrand.sessionbeans.BrandCategoryFacade;
import com.localbrand.sessionbeans.CategoryFacade;
import com.localbrand.sessionbeans.ProductFacade;

@WebServlet(name = "CollectionControllerAdmin", urlPatterns = { "/admin/collection" })
public class CollectionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getAttribute("action").toString();
		System.out.println("actione ne:" + action);
		HttpSession session = request.getSession();
		BrandAccount admin = (BrandAccount) session.getAttribute("admin");
		if (admin != null) {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "info":
				info(request, response);
				break;
			case "create":
				create(request, response);
				break;
			case "update":
				update(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			case "createDetail":
				createDetail(request, response);
				break;
			case "deleteDetail":
				deleteDetail(request, response);
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
	
	private void createDetail(HttpServletRequest request, HttpServletResponse response) {
		AdminCollectionService adminCollectionService = new AdminCollectionService();
		try {
			int collectionId = Integer.parseInt(request.getParameter("collectionId"));
			int productId = Integer.parseInt(request.getParameter("productId"));
			
			CollectionDetail collectionDetail = new CollectionDetail();
			collectionDetail.setCollectionDetailPK(new CollectionDetailPK(collectionId, productId));
			
			adminCollectionService.addCollectionDetail(collectionDetail);
			request.setAttribute("collectionId", collectionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		info(request, response);
	}
	
	private void deleteDetail(HttpServletRequest request, HttpServletResponse response) {
		AdminCollectionService adminCollectionService = new AdminCollectionService();
		try {
			int collectionId = Integer.parseInt(request.getParameter("collectionId"));
			int productId = Integer.parseInt(request.getParameter("productId"));
			
			adminCollectionService.deleteCollectionDetail(collectionId, productId);
			request.setAttribute("collectionId", collectionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		info(request, response);
	}
	
	private void info(HttpServletRequest request, HttpServletResponse response) {
		AdminCollectionService adminCollectionService = new AdminCollectionService();
		try {
			HttpSession session = request.getSession();
			BrandAccount admin = (BrandAccount) session.getAttribute("admin");
			
			int collectionId = 0;
			if (request.getParameter("id") == null) {
				collectionId = (int)request.getAttribute("collectionId");
			} else {
				collectionId = Integer.parseInt(request.getParameter("id"));
			}
			Collection collection = adminCollectionService.getCollectionInfo(collectionId);
			List<Product> productNotInCollection = adminCollectionService.getProductNotInCollection(collectionId, admin.getBrandId().getId());
			request.setAttribute("collection", collection);
			request.setAttribute("productsNotInCollection", productNotInCollection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("action", "info");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		AdminCollectionService adminCollectionService = new AdminCollectionService();
		try {
			int collectionId = Integer.parseInt(request.getParameter("id"));
			adminCollectionService.deleteCollection(collectionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		index(request, response);
	}

	public void create(HttpServletRequest request, HttpServletResponse response) {
		AdminCollectionService adminCollectionService = new AdminCollectionService();
		try {
			HttpSession session = request.getSession();
			BrandAccount admin = (BrandAccount) session.getAttribute("admin");
			// create product Master
			String name = request.getParameter("txtName");
			String description = request.getParameter("txtDescription");
			String imageUrl = request.getParameter("txtImageUrl");
			int status = 1;
			Collection collection = new Collection();
			collection.setBrandId(admin.getBrandId());
			collection.setName(name);
			collection.setDescription(description);
			collection.setImageUrl(imageUrl);
			collection.setStatus(status);
			adminCollectionService.addCollection(collection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		AdminCollectionService adminCollectionService = new AdminCollectionService();
		try {
			HttpSession session = request.getSession();
			BrandAccount admin = (BrandAccount) session.getAttribute("admin");
			// create product Master
			int id = Integer.parseInt(request.getParameter("txtId"));
			String name = request.getParameter("txtName");
			String description = request.getParameter("txtDescription");
			String imageUrl = request.getParameter("txtImageUrl");
			int status = 1;
			Collection collection = new Collection();
			collection.setBrandId(admin.getBrandId());
			collection.setId(id);
			collection.setName(name);
			collection.setDescription(description);
			collection.setImageUrl(imageUrl);
			collection.setStatus(status);
			adminCollectionService.updateCollection(collection);
			request.setAttribute("collectionId", collection.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		info(request, response);
	}

	private void index(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		BrandAccount admin = (BrandAccount)session.getAttribute("admin");
		
		AdminCollectionService adminCollectionService = new AdminCollectionService();
		List<Collection> listCollection = adminCollectionService.getCollection(admin.getBrandId().getId());

		request.setAttribute("listCollection", listCollection);
		request.setAttribute("action", "index");
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