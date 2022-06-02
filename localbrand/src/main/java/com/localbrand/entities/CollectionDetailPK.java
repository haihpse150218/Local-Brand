package com.localbrand.entities;

import java.io.Serializable;


public class CollectionDetailPK implements Serializable {

    private int collectionId;
  
    private int productId;

    public CollectionDetailPK() {
    }

    public CollectionDetailPK(int collectionId, int productId) {
        this.collectionId = collectionId;
        this.productId = productId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

   
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CollectionDetailPK)) {
            return false;
        }
        CollectionDetailPK other = (CollectionDetailPK) object;
        if (this.collectionId != other.collectionId) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CollectionDetailPK[ collectionId=" + collectionId + ", productId=" + productId + " ]";
    }
    
}
