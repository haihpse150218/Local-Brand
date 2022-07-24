package com.localbrand.service;

import java.sql.SQLException;

import com.localbrand.service.models.Cart;

public interface ICheckoutService {
	public void checkout (Cart cart,int cusid) throws SQLException;
}
