package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Product;
import com.localbrand.service.IBrandHome;
import com.localbrand.sessionbeans.ProductFacade;

public class BrandHomeService implements IBrandHome {
	ProductFacade pf = new ProductFacade();
	public List<Product> findAllProductByBrandId(int brandId){
		List<Product> resultList = new ArrayList<Product>();
		try {
			for (Product product : pf.findAll()) {
				if(product.getBrandId().getId() == brandId) {
					resultList.add(product);
					System.out.println(product.getName());
				}
			}
		} catch (SQLException e) {
			System.out.println("BrandHomeService findAllByBrandId err");
			e.printStackTrace();
		}
		return resultList;
	}
	public List<Product> findRangeProductByBrandId(int brandId, int begin, int end){
		List<Product> resultList = findAllProductByBrandId(brandId).subList(begin, end);
		return resultList;
	}
	@Override
	public Brand findBrand() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
