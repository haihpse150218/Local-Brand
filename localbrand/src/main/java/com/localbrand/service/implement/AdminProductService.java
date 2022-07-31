package com.localbrand.service.implement;

import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.BrandCategory;
import com.localbrand.entities.Product;
import com.localbrand.sessionbeans.BrandCategoryFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class AdminProductService {
	public Product getProduct(int productId) {
		Product product = null;
		ProductFacade pf = new ProductFacade();
		try {
			product = pf.find(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	
	public void editProduct(Product product) {
		ProductFacade pf = new ProductFacade();
		try {
			pf.edit(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<BrandCategory> getBrandCategories(int brandId) {
		List<BrandCategory> list = new ArrayList<BrandCategory>();
		BrandCategoryFacade bcf = new BrandCategoryFacade();
		try {
			for (BrandCategory brandCategory : bcf.findAll()) {
				if (brandCategory.getBrandCategoryPK().getBrandId() == brandId) {
					list.add(brandCategory);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Product> getDependencies(int productId) {
		List<Product> list = new ArrayList<Product>();
		ProductFacade pf = new ProductFacade();
		try {
			for (Product product : pf.findAll()) {
				if ((product.getParentId().getId() == productId) 
					&& !(product.getStatus().equals("Disable"))) {
					list.add(product);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addProduct(Product product) {
		ProductFacade pf = new ProductFacade();
		try {
			pf.create(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
