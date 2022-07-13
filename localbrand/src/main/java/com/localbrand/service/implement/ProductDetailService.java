package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Product;
import com.localbrand.service.IProductDetail;
import com.localbrand.sessionbeans.BrandFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class ProductDetailService implements IProductDetail{
	private static ProductFacade productFacade = new ProductFacade();
	private static BrandFacade brandFacade = new BrandFacade();
	@Override
	public Product getProductDetail(int pid) {
		Product product = null;
		List<Product> listAllProduct = new ArrayList<>();
		List<Product> listp = new ArrayList<>();
		try {
			product = new Product();
			product = productFacade.find(pid);
			
			listAllProduct = productFacade.findAll();
			for (Product p : listAllProduct) {
				if (p.getIsMaster() ==false && p.getParentId().getId() == pid) {
					listp.add(p);				
					}
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		product.setProductList(listp);
		return product;
	}
	@Override
	public Brand getBrandDetail(int pid){
		Brand brand = null;
		Product product = null;
		try {
			product = productFacade.find(pid);
			brand = brandFacade.find(product.getBrandId().getId());
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return brand;
	}
	@Override
	public List<Product> getProductChild(int pid){
		List<Product> listp = new ArrayList<>();
		List<Product> listAllProduct = new ArrayList<>();
		try {
			listAllProduct = productFacade.findAll();
			for (Product product : listAllProduct) {
				if (product.getImgChild() != null && product.getParentId().getId() == pid) {				
					listp.add(product);				
					}
				}		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listp;
	}
	@Override
	public List<String> getListSize(Product product){
		Set<String> listSize = new HashSet<String>();
		listSize.add(product.getSize());
		for (Product p : product.getProductList())
		{
			listSize.add(p.getSize());
		}
		
		List<String> result = convertSetToList (listSize);
		Collections.sort(result, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				 return o2.compareTo(o1);
			}
		});
		return result;
	}
	@Override
	public List<String> getListColor(Product product){
		Set<String> listColor = new HashSet<String>();
		
		listColor.add(product.getColor());
		
		for (Product p : product.getProductList())
		{
			listColor.add(p.getColor());
		}
		
		List<String> result = convertSetToList (listColor);
		Collections.sort(result, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				 return o2.compareTo(o1);
			}
		});
		return result;
	}
	public static <T> List<T> convertSetToList(Set<T> set)
    {
        // create a list from Set
        return set
  
            // Create stream from the Set
            .stream()
  
            // Convert the set to list and collect it
            .collect(Collectors.toList());
    }
}
