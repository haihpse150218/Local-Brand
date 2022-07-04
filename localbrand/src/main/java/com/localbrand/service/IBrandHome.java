package com.localbrand.service;

import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Product;

public interface IBrandHome {
	public List<Product> findAllProductByBrandId(int brandId);
	public List<Product> findRangeProductByBrandId(int brandId, int begin, int end);
	public Brand findBrand();

}
