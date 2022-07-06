package com.localbrand.service;
import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Product;
public interface IProductDetail {
	public List<Product> getProductDetail(int pid);
	public List<Brand> getBrandDetail(int pid);
}
