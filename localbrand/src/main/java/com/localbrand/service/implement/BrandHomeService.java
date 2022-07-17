package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.BrandCategory;
import com.localbrand.entities.Product;
import com.localbrand.entities.Collection;
import com.localbrand.entities.CollectionDetail;
import com.localbrand.service.IBrandHome;
import com.localbrand.sessionbeans.BrandCategoryFacade;
import com.localbrand.sessionbeans.BrandFacade;
import com.localbrand.sessionbeans.CollectionFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class BrandHomeService implements IBrandHome {
	
	ProductFacade pf = new ProductFacade();
	BrandFacade bf = new BrandFacade();
	CollectionFacade cf = new CollectionFacade();
	BrandCategoryFacade bcf = new BrandCategoryFacade();
	
	public List<Product> findAllProductByBrandId(int brandId){
		List<Product> resultList = new ArrayList<Product>();
		try {
			for (Product product : pf.findAll()) {
				if(product.getBrandId().getId() == brandId
						&& product.getIsMaster() == true) {
					resultList.add(product);
					//System.out.println(product.getName());
				}
			}
		} catch (SQLException e) {
			System.out.println("BrandHomeService findAllByBrandId err");
			e.printStackTrace();
		}
		return resultList;
	}
	
	public Brand findBrand(int id) throws Exception { 
		Brand brand = bf.find(id);
		List<Collection> listCollection = new ArrayList<Collection>();
		for (Collection collection : cf.findAll()) {
			if (id == collection.getBrandId().getId()) {
				listCollection.add(collection);
			}
		}
		brand.setCollectionList(listCollection);
		List<BrandCategory> listBrandCate = new ArrayList<BrandCategory>();
		for (BrandCategory category : bcf.findAll()) {
			if (brand.getId().equals(category.getBrandCategoryPK().getBrandId())) {
				listBrandCate.add(category);
			}
		}
		brand.setBrandCategoryList(listBrandCate);
		for (BrandCategory brandCategory : listBrandCate) {
			System.out.println(brandCategory);
		}
		return brand;
	}
	
	public List<Product> findAllProduct(int brandId,
										String txtSearch, 
										String txtCateId, 
										String txtSortBy,
										String txtPriceRange1,
										String txtPriceRange2) 
	{
		int cateId = 0;
		try {
			cateId = Integer.parseInt(txtCateId);
		} catch (Exception e) {
			cateId = 0;
		}
		double priceRange1 = Double.parseDouble(txtPriceRange1);
		double priceRange2 = Double.parseDouble(txtPriceRange2);
		
		if (priceRange1 > priceRange2) {
    		double t = priceRange1;
    		priceRange1 = priceRange2;
    		priceRange2 = t;
    	}
		
		List<Product> resultList = new ArrayList<Product>();
		try {
			for (Product product : pf.findAll()) {
				if (product.getBrandId().getId() == brandId
						&& product.getIsMaster() == true
						&& product.getName().contains(txtSearch)
						&& (cateId==0? true : product.getCateId().getId() == cateId)
						&& product.getPrice() >= priceRange1
						&& product.getPrice() <= priceRange2) 
				{
					resultList.add(product);
					//System.out.println(product.getName());
				}
			}
			if (txtSortBy.equals("rating")) {
				Collections.sort(resultList, new Comparator<Product>() {                 
					@Override                 
					public int compare(Product o1, Product o2) {    
						return o2.getStars().compareTo(o1.getStars());            
					}            
				});
			} else if (txtSortBy.equals("latest")) {
				Collections.sort(resultList, new Comparator<Product>() {                 
					@Override                 
					public int compare(Product o1, Product o2) {    
						return o2.getCreateDate().compareTo(o1.getCreateDate());            
					}            
				});
			}
		} catch (SQLException e) {
			System.out.println("BrandHomeService searchAll err");
			e.printStackTrace();
		}
		return resultList;
	}
	
	public List<Collection> findAllCollection(int brandId) {
		List<Collection> resultList = new ArrayList<Collection>();
		try {
			for (Collection collection : cf.findAll()) {
				if (collection.getBrandId().getId() == brandId) {
					resultList.add(collection);
					System.out.println(collection);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	public List<Product> sortAllByLatest(int brandId) {
		List<Product> resultList = new ArrayList<Product>();
		try {
			for (Product product : pf.findAll()) {
				if(product.getBrandId().getId() == brandId
						&& product.getIsMaster() == true) {
					resultList.add(product);
					System.out.println(product.getName());
				}
			}
			Collections.sort(resultList, new Comparator<Product>() {                 
				@Override                 
				public int compare(Product o1, Product o2) {    
					return o2.getCreateDate().compareTo(o1.getCreateDate());            
				}            
			});
		} catch (SQLException e) {
			System.out.println("BrandHomeService sortAllByLatest err");
			e.printStackTrace();
		}
		return resultList;
	}
	/*
	public List<Product> findAllProductByName(String name, int brandId) {
		List<Product> resultList = new ArrayList<Product>();
		try {
			for (Product product : pf.findAll()) {
				if(product.getBrandId().getId() == brandId
						&& product.getIsMaster() == true
						&& product.getName().contains(name)) {
					resultList.add(product);
					System.out.println(product.getName());
				}
			}
		} catch (SQLException e) {
			System.out.println("BrandHomeService findAllByName err");
			e.printStackTrace();
		}
		return resultList;
	}
	
	public List<Product> findAllProductByCategory(int cateId, int brandId) {
		List<Product> resultList = new ArrayList<Product>();
		try {
			for (Product product : pf.findAll()) {
				if(product.getBrandId().getId() == brandId
						&& product.getIsMaster() == true
						&& product.getCateId().getId() == cateId) {
					resultList.add(product);
					System.out.println(product.getName());
				}
			}
		} catch (SQLException e) {
			System.out.println("BrandHomeService findAllByCate err");
			e.printStackTrace();
		}
		return resultList;
	}
	
	public List<Product> findRangeProductByBrandId(int brandId, int begin, int end){
		List<Product> resultList = findAllProductByBrandId(brandId).subList(begin, end);
		return resultList;
	}
	
	public List<Product> sortAllByRating(int brandId) {
		List<Product> resultList = new ArrayList<Product>();
		try {
			for (Product product : pf.findAll()) {
				if(product.getBrandId().getId() == brandId
						&& product.getIsMaster() == true) {
					resultList.add(product);
					System.out.println(product.getName());
				}
			}
			Collections.sort(resultList, new Comparator<Product>() {                 
				@Override                 
				public int compare(Product o1, Product o2) {    
					return o2.getStars().compareTo(o1.getStars());            
				}            
			});
		} catch (SQLException e) {
			System.out.println("BrandHomeService sortAllByRating err");
			e.printStackTrace();
		}
		return resultList;
	}
	
	public List<Product> findProductsInPriceRange(double[] range, int brandId) {
		List<Product> resultList = new ArrayList<Product>();
		try {
			for (Product product : pf.findAll()) {
				if(product.getBrandId().getId() == brandId
						&& product.getPrice() >= range[0]
						&& product.getPrice() <= range[1]) {
					resultList.add(product);
					System.out.println(product.getName());
				}
			}
		} catch (SQLException e) {
			System.out.println("BrandHomeService findAllByCate err");
			e.printStackTrace();
		}
		return resultList;
	}
	*/
}
