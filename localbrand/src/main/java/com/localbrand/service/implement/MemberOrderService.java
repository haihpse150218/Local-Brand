package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.localbrand.entities.Feedback;
import com.localbrand.entities.Order;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.Product;
import com.localbrand.service.IMemberOrderHistory;
import com.localbrand.sessionbeans.BrandFacade;
import com.localbrand.sessionbeans.FeedbackFacade;
import com.localbrand.sessionbeans.OrderDetailFacade;
import com.localbrand.sessionbeans.OrderFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class MemberOrderService implements IMemberOrderHistory {
	OrderFacade orfc = new OrderFacade();
	OrderDetailFacade odfc = new OrderDetailFacade();
	BrandFacade brfc = new BrandFacade();
	ProductFacade prfc = new ProductFacade();
	FeedbackFacade fbfc = new FeedbackFacade();

	public List<Order> getMemberListOrder(int cusid, String status) throws SQLException {
		List<Order> list = new ArrayList<>();
		List<Order> listOr = new ArrayList<>();
		List<OrderDetail> listOd = new ArrayList<>();
		List<Feedback> listFb = new ArrayList<>();
		
		listOd = odfc.findAll();
		listOr = orfc.findAll();
		listFb = fbfc.findAll();
		
		for (Order or : listOr) {
			// cusid = cusid trong order
			if (or.getCustomerId().getId() == cusid) {
				if (status == "" || status == null) {
					
					//them list order detail vao
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
					
					//them list feedback vao
					List<Feedback> listFbbyOr = new ArrayList<>();
					for (Feedback fb : listFb) {
						// orderid = orderid trong order details
						if (fb.getOrderId().getId() == or.getId()) {
							listFbbyOr.add(fb);
						}
					}
					or.setFeedbackList(listFbbyOr);
					
					list.add(or);
				} else if (or.getStatus().equalsIgnoreCase(status)) {

					//them list order detail vao
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
		if (list.size()==0) return null;
		Collections.reverse(list);
		return list;
	}

	public void setOrderStatus(int orderid, String status) throws SQLException {
		Order order = orfc.find(orderid);
		order.setStatus(status);
		orfc.edit(order);
	}

}
