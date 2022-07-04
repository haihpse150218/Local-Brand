package com.localbrand.service;

import java.util.List;
import com.localbrand.entities.Brand;
import com.localbrand.entities.Collection;
import com.localbrand.entities.Product;

public interface IHome {
	public List<Brand> getBrandList(int start, int end) throws Exception;
	
	public List<Collection> getTrendingCollection();
	
	public List<Product> getTrendingProducts();

}
