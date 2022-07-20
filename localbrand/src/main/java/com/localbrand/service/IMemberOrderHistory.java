package com.localbrand.service;

import java.sql.SQLException;
import java.util.List;

import com.localbrand.entities.Order;

public interface IMemberOrderHistory {
	public List<Order> getMemberListOrder (int cusid) throws SQLException;
	public void setOrderStatus (int orderid, String status) throws SQLException ;
}
