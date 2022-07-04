package com.localbrand.service.implement;

import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Collection;
import com.localbrand.entities.Product;
import com.localbrand.service.IHome;
import com.localbrand.sessionbeans.BrandFacade;

public class HomeService implements IHome {

	@Override
	public List<Brand> getBrandList(int start, int end) throws Exception {
		
		BrandFacade brandFacade = new BrandFacade();
		
		List<Brand> list = brandFacade.findRange(new int[] {start, end});
		
		return list;
	}

	@Override
	public List<Collection> getTrendingCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getTrendingProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
