package com.localbrand.service.models;

import java.util.Date;
import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Category;
import com.localbrand.entities.CollectionDetail;
import com.localbrand.entities.Feedback;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.Product;

public class Item {
	private Product product;
    
    private Integer quantity;

	public Item() {
		product = null;
		quantity = 0; 
	}

	public Item(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
}
