package com.localbrand.service.implement;

import com.localbrand.entities.Brand;
import com.localbrand.sessionbeans.BrandFacade;

public class AdminBrandInfoService {
	public Brand getBrand(int brandId) {
		Brand brand = null;
		BrandFacade bf = new BrandFacade();
		try {
			brand = bf.find(brandId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brand;
	}
	
	public void updateBrand(Brand brand) {
		BrandFacade bf = new BrandFacade();
		try {
			bf.edit(brand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
