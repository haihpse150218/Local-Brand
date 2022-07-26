package com.localbrand.service.implement;

import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Collection;
import com.localbrand.entities.CollectionDetail;
import com.localbrand.sessionbeans.CollectionDetailFacade;
import com.localbrand.sessionbeans.CollectionFacade;

public class AdminCollectionService {
	
	public void getCollectionDetail(Collection collection) {
		CollectionDetailFacade cdf = new CollectionDetailFacade();
		
		try {
			List<CollectionDetail> list = new ArrayList<CollectionDetail>();
			for (CollectionDetail collectionDetail : cdf.findAll()) {
				if (collection.getId() == collectionDetail.getCollectionDetailPK().getCollectionId()) {
					list.add(collectionDetail);
				}
			}
			collection.setCollectionDetailList(list);
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
		CollectionDetailFacade cdf = new CollectionDetailFacade();
		
		try {
			cf.create(collection);
			for (CollectionDetail collectionDetail : collection.getCollectionDetailList()) {
				cdf.create(collectionDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateCollection(Collection collection) {
		CollectionFacade cf = new CollectionFacade();
		CollectionDetailFacade cdf = new CollectionDetailFacade();
		
		try {
			cf.edit(collection);
			for (CollectionDetail collectionDetail : collection.getCollectionDetailList()) {
				if (cdf.find(collectionDetail.getCollectionDetailPK()) == null) {
					cdf.create(collectionDetail);
				}
				cdf.edit(collectionDetail);
			}
			for (CollectionDetail collectionDetail : cdf.findAll()) {
				if (collectionDetail.getCollectionDetailPK().getCollectionId() == collection.getId()
						&& !collection.getCollectionDetailList().contains(collectionDetail)) {
					cdf.remove(collectionDetail);
				}
			}
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
}
