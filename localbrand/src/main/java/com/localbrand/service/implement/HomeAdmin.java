package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Customer;
import com.localbrand.entities.Order;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.Product;
import com.localbrand.service.IHomeAdmin;
import com.localbrand.service.models.OrderObject;
import com.localbrand.sessionbeans.CustomerFacade;
import com.localbrand.sessionbeans.OrderDetailFacade;
import com.localbrand.sessionbeans.OrderFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class HomeAdmin implements IHomeAdmin {
	private HomeAdmin() {
	}

	private static HomeAdmin instance = new HomeAdmin();

	public static HomeAdmin getInstance() {
		if (instance == null) {
			instance = new HomeAdmin();
		}
		return instance;
	}

	public List<OrderObject> getOrderListByBrandId(Integer id) {
		OrderFacade of = new OrderFacade();
		OrderDetailFacade odf = new OrderDetailFacade();
		ProductFacade pf = new ProductFacade();
		CustomerFacade cf = new CustomerFacade();
		
		List<OrderObject> listOrderObject = new ArrayList<>();
		List<Order> listOrder = new ArrayList<>();
		try {
			for (OrderDetail od : odf.findAll()) {
				Product product = pf.find(od.getProduct().getId());
				
				Order order = of.find(od.getOrder1().getId());
				for (Order o : listOrder) {
					if(o.getId() == order.getId()) {
						if(product.getBrandId().getId() == id) {
							OrderObject orderObject = new OrderObject();
							
							
							orderObject.setId(order.getId());
							Customer customer = cf.find(order.getCustomerId().getId());
							orderObject.setCustomerId(customer);
						}
					}
				}
				
				
				
				
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
