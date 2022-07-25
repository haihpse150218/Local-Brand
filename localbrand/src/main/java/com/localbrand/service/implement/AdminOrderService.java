package com.localbrand.service.implement;

import com.localbrand.entities.Order;
import com.localbrand.sessionbeans.OrderFacade;

public class AdminOrderService {
	public void updateOrderStatus(int orderId, String newStatus) {
		OrderFacade of = new OrderFacade();
		try {
			Order order = of.find(orderId);
			order.setStatus(newStatus);
			of.edit(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
