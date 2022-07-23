package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Order;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.Product;
import com.localbrand.service.IMemberOrderHistory;
import com.localbrand.sessionbeans.BrandFacade;
import com.localbrand.sessionbeans.OrderDetailFacade;
import com.localbrand.sessionbeans.OrderFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class MemberOrderService implements IMemberOrderHistory {
	OrderFacade orfc = new OrderFacade();
	OrderDetailFacade odfc = new OrderDetailFacade();
	BrandFacade brfc = new BrandFacade();
	ProductFacade prfc = new ProductFacade();

	public List<Order> getMemberListOrder(int cusid, String status) throws SQLException {
		List<Order> list = new ArrayList<>();
		List<Order> listOr = new ArrayList<>();
		List<OrderDetail> listOd = new ArrayList<>();
		listOd = odfc.findAll();
		listOr = orfc.findAll();
		for (Order or : listOr) {
			// cusid = cusid trong order
			if (or.getCustomerId().getId() == cusid) {
				if (status == "") {
					List<OrderDetail> listOdbyOr = new ArrayList<>();
					for (OrderDetail od : listOd) {
						// orderid = orderid trong order details
						if (od.getOrderDetailPK().getOrderId() == or.getId()) {
							
							Product product = prfc.find(od.getProduct().getId());
							product.setBrandId(brfc.find(product.getBrandId().getId()));
							od.setProduct(product);
							
							listOdbyOr.add(od);
						}
					}
					or.setOrderDetailList(listOdbyOr);
					list.add(or);
				} else if (or.getStatus().equalsIgnoreCase(status)) {
					List<OrderDetail> listOdbyOr = new ArrayList<>();
					for (OrderDetail od : listOd) {
						// orderid = orderid trong order details
						if (od.getOrderDetailPK().getOrderId() == or.getId()) {
							
							Product product = prfc.find(od.getProduct().getId());
							product.setBrandId(brfc.find(product.getBrandId().getId()));
							od.setProduct(product);
							
							listOdbyOr.add(od);
						}
					}
					or.setOrderDetailList(listOdbyOr);
					list.add(or);
				}

			}
		}
		Collections.reverse(list);
		return list;
	}

	public void setOrderStatus(int orderid, String status) throws SQLException {
		Order order = orfc.find(orderid);
		order.setStatus(status);
		orfc.edit(order);
	}

}
