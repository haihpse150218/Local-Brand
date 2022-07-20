package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Order;
import com.localbrand.entities.OrderDetail;
import com.localbrand.service.IMemberOrderHistory;
import com.localbrand.sessionbeans.OrderDetailFacade;
import com.localbrand.sessionbeans.OrderFacade;

public class MemberOrderService implements IMemberOrderHistory {
	OrderFacade orfc = new OrderFacade();
	OrderDetailFacade odfc = new OrderDetailFacade();
	
	public List<Order> getMemberListOrder (int cusid) throws SQLException{
		List<Order> list = new ArrayList<>();
		List<Order> listOr = new ArrayList<>();
		List<OrderDetail> listOd = new ArrayList<>();
		listOd = odfc.findAll();
		listOr = orfc.findAll();
		for (Order or : listOr) {
			//cusid = cusid trong order
			if (or.getCustomerId().getId()==cusid) {
				List<OrderDetail> listOdbyOr = new ArrayList<>();
				for(OrderDetail od : listOd) {
					//orderid = orderid trong order details
					if (od.getOrderDetailPK().getOrderId()==or.getId()) {
						listOdbyOr.add(od);
					}
				}
				or.setOrderDetailList(listOdbyOr);
				list.add(or);
			}
		}
		return list;
	}
	public void setOrderStatus (int orderid, String status) throws SQLException {
		Order order = orfc.find(orderid);
		order.setStatus(status);
		orfc.edit(order);
	}
	
}
