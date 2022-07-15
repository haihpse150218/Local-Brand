package com.localbrand.service;

import java.sql.SQLException;
import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Category;
import com.localbrand.entities.Collection;
import com.localbrand.entities.Product;
import com.localbrand.service.models.Cart;

public interface IHome {
	public List<Brand> getBrandList() throws Exception;
	
	public List<Product> getListProduct() throws Exception;	
	
	public List<Collection> getTopCollection()throws Exception;	;
	
	public List<Product> getTopProduct()throws Exception;
	
	public List<Product> getListBrandProduct(int brandid) throws Exception;	
	
	public List<Product> getListProductByStatus (String status) throws Exception;	
	
	public List<Product> getListProductByCate(int cateid) throws Exception;
	
	public Brand getBrand(int brandid) throws SQLException;
	
	public Category getCategory(int cateid) throws SQLException;
	
	public Cart addToCart (int productid, int quantity, Cart cart)throws Exception;

}
