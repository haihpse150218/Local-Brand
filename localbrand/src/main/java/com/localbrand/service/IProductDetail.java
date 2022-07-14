package com.localbrand.service;
import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Product;
public interface IProductDetail {
	public Product getProductDetail(int pid);
	public Brand getBrandDetail(int pid);
	public List<Product> getProductChild(int pid);
	public List<String> getListSize(Product product);
	public List<String> getListColor(Product product);
}
