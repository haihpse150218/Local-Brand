package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.localbrand.sessionbeans.BrandFacade;
import com.localbrand.entities.Brand;
import com.localbrand.entities.Product;
import com.localbrand.service.IRatingService;

public class RatingService implements IRatingService{

	@Override
	public List<Brand> getRatingBrand() {
		List<Brand> result = new ArrayList<>();
		BrandFacade bf = new BrandFacade();
		try {
			List<Brand> allBrand = bf.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> getRatingProduct() {
		// TODO Auto-generated method stub
		return null;
	}

}
