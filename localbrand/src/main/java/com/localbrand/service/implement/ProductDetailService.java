package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Product;
import com.localbrand.service.IProductDetail;
import com.localbrand.sessionbeans.BrandFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class ProductDetailService implements IProductDetail{
	private static ProductFacade productFacade = new ProductFacade();
	private static BrandFacade brandFacade = new BrandFacade();
	@Override
	public List<Product> getProductDetail(int pid) {
		Product product = null;
		List<Product> listp = new ArrayList<>();
		try {
			product = new Product();
			product = productFacade.find(pid);
			listp.add(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listp;
	}
	@Override
	public List<Brand> getBrandDetail(int pid){
		Brand brand = null;
		Product product = null;
		List<Brand> listb = new ArrayList<>();
		try {
			product = productFacade.find(pid);
			brand = brandFacade.find(product.getBrandId().getId());
			listb.add(brand);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listb;
	}
}
