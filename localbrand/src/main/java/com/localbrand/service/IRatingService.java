package com.localbrand.service;
import java.util.List;
import com.localbrand.entities.Brand;
import com.localbrand.entities.Product;

public interface IRatingService {
	public List<Brand> getRatingBrand();
	
	public List<Product> getRatingProduct();
}
