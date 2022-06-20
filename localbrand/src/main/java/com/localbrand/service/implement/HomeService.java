package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.localbrand.entities.Product;
import com.localbrand.service.IHome;
import com.localbrand.sessionbeans.ProductFacade;

public class HomeService implements IHome {
	ProductFacade pf = new ProductFacade();

	@Override
	public List<Product> getTrendingProduct() {
		List<Product> listProduct = new ArrayList<>();
		List<Product> listTrendingProduct = null;
		HashMap<Integer, Product> priorMap = new HashMap<>();
		
		try {
			listProduct = pf.findAll();
			for (Product product : listProduct) {
				int prior = 0; // do uu tien = status(5d) + stars(5) = 10
				if((product.getStatus().trim()).equalsIgnoreCase("New")) {
					prior += 5;
				}
				prior += product.getStars().intValue();
				if(prior >= 5) {
					priorMap.put(prior, product);
				}
			}
			Map<Integer, Product> sortPriorMap = new TreeMap<Integer, Product>(Collections.reverseOrder());
			sortPriorMap.putAll(priorMap);
			listTrendingProduct = new ArrayList<>(sortPriorMap.values());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listTrendingProduct;
	}

}
