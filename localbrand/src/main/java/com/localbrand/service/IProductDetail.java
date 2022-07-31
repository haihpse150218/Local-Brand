package com.localbrand.service;
import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Customer;
import com.localbrand.entities.Feedback;
import com.localbrand.entities.Product;
public interface IProductDetail {
	public Product getProductDetail(int pid);
	public Brand getBrandDetail(int pid);
	public List<Product> getProductChild(int pid);
	public List<Product> getAllProduct(int pid);
	public List<String> getListSize(Product product);
	public List<String> getListColor(Product product);
	public void createFeedback(Feedback f);
	public List<Customer> getCusByFb (List<Feedback> fb);
}
