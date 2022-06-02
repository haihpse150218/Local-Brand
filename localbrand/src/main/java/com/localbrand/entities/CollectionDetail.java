
package com.localbrand.entities;

import java.io.Serializable;

public class CollectionDetail implements Serializable {

    private static final long serialVersionUID = 1L;
   
    protected CollectionDetailPK collectionDetailPK;
    
    private String name;
  
  
    private String status;
   
    private Collection collection;
  
    private Product product;

    public CollectionDetail() {
    }

    public CollectionDetail(CollectionDetailPK collectionDetailPK) {
        this.collectionDetailPK = collectionDetailPK;
    }

    public CollectionDetail(int collectionId, int productId) {
        this.collectionDetailPK = new CollectionDetailPK(collectionId, productId);
    }

    public CollectionDetailPK getCollectionDetailPK() {
        return collectionDetailPK;
    }

    public void setCollectionDetailPK(CollectionDetailPK collectionDetailPK) {
        this.collectionDetailPK = collectionDetailPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

  
    public boolean equals(Object object) {
       
        if (!(object instanceof CollectionDetail)) {
            return false;
        }
        CollectionDetail other = (CollectionDetail) object;
        if ((this.collectionDetailPK == null && other.collectionDetailPK != null) || (this.collectionDetailPK != null && !this.collectionDetailPK.equals(other.collectionDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CollectionDetail[ collectionDetailPK=" + collectionDetailPK + " ]";
    }
    
}
