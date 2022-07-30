package com.localbrand.service.implement;

import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Collection;
import com.localbrand.entities.CollectionDetail;
import com.localbrand.entities.CollectionDetailPK;
import com.localbrand.entities.Product;
import com.localbrand.sessionbeans.CollectionDetailFacade;
import com.localbrand.sessionbeans.CollectionFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class AdminCollectionService {
	
	public Collection getCollectionInfo(int collectionId) {
		CollectionFacade cf = new CollectionFacade();
		Collection collection = new Collection();
		
		try {
			collection = cf.find(collectionId);
			getCollectionDetail(collection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return collection;
	}
	
	public void getCollectionDetail(Collection collection) {
		CollectionDetailFacade cdf = new CollectionDetailFacade();
		ProductFacade pf = new ProductFacade();
		try {
			List<CollectionDetail> list = new ArrayList<CollectionDetail>();
			for (CollectionDetail collectionDetail : cdf.findAll()) {
				if (collection.getId() == collectionDetail.getCollectionDetailPK().getCollectionId()) {
					collectionDetail.setProduct(pf.find(collectionDetail.getCollectionDetailPK().getProductId()));
					list.add(collectionDetail);
				}
			}
			collection.setCollectionDetailList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addCollectionDetail(CollectionDetail collectionDetail) {
		CollectionDetailFacade cdf = new CollectionDetailFacade();
		try {
			Collection collection = new Collection();
			collection.setId(collectionDetail.getCollectionDetailPK().getCollectionId());
			Product product = new Product();
			product.setId(collectionDetail.getCollectionDetailPK().getProductId());
			
			collectionDetail.setCollection(collection);
			collectionDetail.setProduct(product);
			cdf.create(collectionDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCollectionDetail(int collectionId, int productId) {
		CollectionDetailFacade cdf = new CollectionDetailFacade();
		try {
			cdf.remove(new CollectionDetailPK(collectionId, productId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Collection> getCollection(int brandId) {
		CollectionFacade cf = new CollectionFacade();
		
		List<Collection> list = new ArrayList<Collection>();
		
		try {
			for (Collection collection : cf.findAll()) {
				if (brandId == collection.getBrandId().getId()) {
					getCollectionDetail(collection);
					list.add(collection);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public void addCollection(Collection collection) {
		CollectionFacade cf = new CollectionFacade();
		
		try {
			cf.create(collection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateCollection(Collection collection) {
		CollectionFacade cf = new CollectionFacade();
		try {
			cf.edit(collection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCollection(int collectionId) {
		CollectionFacade cf = new CollectionFacade();
		
		try {
			cf.remove(collectionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> getProductsFromBrand(int brandId) {
		List<Product> list = new ArrayList<Product>();
		ProductFacade pf = new ProductFacade();
		try {
			for (Product product : pf.findAll()) {
				if (product.getBrandId().getId() == brandId) {
					list.add(product);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Product> getProductNotInCollection(int collectionId, int brandId) {
		List<Product> list = new ArrayList<Product>();
		ProductFacade pf = new ProductFacade();
		try {
			List<Product> productsInCollection = new ArrayList<Product>();
			Collection collection = getCollectionInfo(collectionId);
			for (CollectionDetail collectionDetail : collection.getCollectionDetailList()) {
				productsInCollection.add(collectionDetail.getProduct());
			}
			for (Product product : getProductsFromBrand(brandId)) {
				if (!productsInCollection.contains(product) && product.getIsMaster() == true) {
					list.add(product);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
