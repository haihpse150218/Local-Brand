package com.localbrand.service;

import java.sql.SQLException;
import java.util.List;

import com.localbrand.entities.Customer;
import com.localbrand.service.models.Cart;

public interface IViewCartService {
	public List<Cart> getListCartProductByBrand (Cart cart);
	public Customer getCustomer (int cusid) throws SQLException ;
	public double getDiscount (int cusid) throws SQLException;
	public void updateReceiveInfo (Customer cus) throws SQLException;
	public Cart updateOrderQuantity (int productid, int updQuantity, Cart cart) throws SQLException;
	public double getTotalInCart (Cart cart);
	
	
	
}
