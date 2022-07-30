package com.localbrand.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.localbrand.entities.Feedback;
import com.localbrand.entities.Order;
import com.localbrand.entities.OrderDetail;

public interface IMemberOrderHistory {
	public List<Order> getMemberListOrder (int cusid, String status) throws SQLException;
	public void setOrderStatus (int orderid, String status) throws SQLException ;
	public OrderDetail getOrderDetail(int orderid, int productid) throws SQLException;
	public Feedback getFeedback(int orderid, int detailid) throws SQLException ;
	public void createFeedback(int orderid, int detailid, String txtCmt, Double voting) throws SQLException ;
}
