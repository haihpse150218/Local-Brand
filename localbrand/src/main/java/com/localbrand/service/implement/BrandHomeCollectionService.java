package com.localbrand.service.implement;

import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Product;
import com.localbrand.entities.Collection;
import com.localbrand.entities.CollectionDetail;
import com.localbrand.service.IBrandHome;
import com.localbrand.sessionbeans.BrandCategoryFacade;
import com.localbrand.sessionbeans.BrandFacade;
import com.localbrand.sessionbeans.CollectionDetailFacade;
import com.localbrand.sessionbeans.CollectionFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class BrandHomeCollectionService implements IBrandHome {
	
	ProductFacade pf = new ProductFacade();
	BrandFacade bf = new BrandFacade();
	CollectionFacade cf = new CollectionFacade();
	BrandCategoryFacade bcf = new BrandCategoryFacade();
	CollectionDetailFacade cdfc = new CollectionDetailFacade();

	public Collection findCollection(int id) throws Exception { 
		Collection collection = cf.find(id);

		List<CollectionDetail> listCd = new ArrayList<CollectionDetail>();
		for (CollectionDetail cd : cdfc.findAll()) {
			
			cd.setCollection(collection);
			cd.setProduct(pf.find(cd.getProduct().getId()));
			
			if (cd.getCollectionDetailPK().getCollectionId() == id) {
				listCd.add(cd);
			}
		}
		collection.setCollectionDetailList(listCd);

		return collection;
	}
	
	public List<Product> findProductByCollection(int brandId, int collectionId) {
		List<Product> listProduct = new ArrayList<Product>();
		try {
			Collection collection = findCollection(collectionId);
			List<CollectionDetail> cdList = collection.getCollectionDetailList();
			
			for (CollectionDetail cd : cdList) {
				if (cd.getCollectionDetailPK().getCollectionId() == collectionId) {
					listProduct.add(cd.getProduct());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listProduct;
	}

}
