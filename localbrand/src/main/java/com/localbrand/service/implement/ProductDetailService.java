package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Customer;
import com.localbrand.entities.Feedback;
import com.localbrand.entities.Order;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.Product;
import com.localbrand.service.IProductDetail;
import com.localbrand.sessionbeans.BrandFacade;
import com.localbrand.sessionbeans.CustomerFacade;
import com.localbrand.sessionbeans.FeedbackFacade;
import com.localbrand.sessionbeans.OrderDetailFacade;
import com.localbrand.sessionbeans.OrderFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class ProductDetailService implements IProductDetail{
	private static ProductFacade productFacade = new ProductFacade();
	private static BrandFacade brandFacade = new BrandFacade();
	private static FeedbackFacade feedbackFacade = new FeedbackFacade();
	private static OrderFacade orderFacade = new OrderFacade();
	private static OrderDetailFacade orderDetailFacade = new OrderDetailFacade();
	private static CustomerFacade customerFacade = new CustomerFacade();
	@Override
	public Product getProductDetail(int pid) {
		Product product = null;
		List<Product> listAllProduct = new ArrayList<>();
		List<Product> listp = new ArrayList<>();
		List<Feedback> listAllFeedback = new ArrayList<>();
		List<Feedback> listf = new ArrayList<>();
		List<OrderDetail> listAllOrderDetail = new ArrayList<>();
		List<OrderDetail> listod = new ArrayList<>();
		try {
			product = new Product();
			product = productFacade.find(pid);
			
			listAllProduct = productFacade.findAll();
			listAllFeedback = feedbackFacade.findAll();
			listAllOrderDetail = orderDetailFacade.findAll();
			for (Product p : listAllProduct) {
				if (p.getIsMaster() ==false && p.getParentId().getId() == pid) {
					listp.add(p);				
					}
			}
			for (Feedback f : listAllFeedback) {
				// master
				if (product.getId() == f.getProductId().getId()) {
					listf.add(f);
				}
				for (Product var : listp) {
					if (var.getId() == f.getProductId().getId() && f.getTextComment() != null) {
						listf.add(f);
					}
				}
			}
			for (OrderDetail od : listAllOrderDetail) {
				// master
				if (product.getId() == od.getProduct().getId()) {
					listod.add(od);
				}
				for (Product var : listp) {
					if (var.getId() == od.getProduct().getId()) {
						listod.add(od);
					}
				}
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		product.setProductList(listp);
		product.setFeedbackList(listf);
		product.setOrderDetailList(listod);
		return product;
	}
	@Override
	public Brand getBrandDetail(int pid){
		Brand brand = null;
		Product product = null;
		try {
			product = productFacade.find(pid);
			brand = brandFacade.find(product.getBrandId().getId());
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return brand;
	}
	@Override
	public List<Product> getProductChild(int pid){
		List<Product> listp = new ArrayList<>();
		List<Product> listAllProduct = new ArrayList<>();
		try {
			listAllProduct = productFacade.findAll();
			for (Product product : listAllProduct) {
				if (product.getImgChild() != null && product.getParentId().getId() == pid) {				
					listp.add(product);				
					}
				}		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listp;
	}
	@Override
	public List<Product> getAllProduct(int pid){
		List<Product> listp = new ArrayList<>();
		List<Product> listAllProduct = new ArrayList<>();
		try {
			listAllProduct = productFacade.findAll();
			for (Product product : listAllProduct) {
				if(product.getId() == pid) {
					listp.add(product);	
					}
				if (product.getImgChild() != null && product.getParentId().getId() == pid) {				
					listp.add(product);				
					}
				}		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listp;
	}
	@Override
	public List<String> getListSize(Product product){
		Set<String> listSize = new HashSet<String>();
		listSize.add(product.getSize());
		for (Product p : product.getProductList())
		{
			listSize.add(p.getSize());
		}
		
		List<String> result = convertSetToList (listSize);
		Collections.sort(result, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				 return o2.compareTo(o1);
			}
		});
		return result;
	}
	@Override
	public List<String> getListColor(Product product){
		Set<String> listColor = new HashSet<String>();
		
		listColor.add(product.getColor());
		
		for (Product p : product.getProductList())
		{
			listColor.add(p.getColor());
		}
		
		List<String> result = convertSetToList (listColor);
		Collections.sort(result, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				 return o2.compareTo(o1);
			}
		});
		return result;
	}
	@Override
	public void createFeedback(Feedback f) {
		try {
			feedbackFacade.create(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<Customer> getCusByFb (List<Feedback> fb){
		List<Order> listAllOrder = new ArrayList<>();
		List<Customer> listcus = new ArrayList<>(); 
		Customer cus = null;
		
		try {
			listAllOrder = orderFacade.findAll();	
			for (Feedback f:fb){
				 for (Order o :listAllOrder) {
					if (f.getOrderId().getId() == o.getId()) {
						cus = customerFacade.find(o.getCustomerId().getId());
						listcus.add(cus);
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listcus;
	}
	public static <T> List<T> convertSetToList(Set<T> set)
    {
        // create a list from Set
        return set
  
            // Create stream from the Set
            .stream()
  
            // Convert the set to list and collect it
            .collect(Collectors.toList());
    }
}
