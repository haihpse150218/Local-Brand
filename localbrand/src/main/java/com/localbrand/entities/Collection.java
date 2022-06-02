package com.localbrand.entities;

import java.io.Serializable;
import java.util.List;
public class Collection implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer id;
    
    private String name;
  
    private String description;
    
    private String imageUrl;
    
    private Integer status;
   
    private List<CollectionDetail> collectionDetailList;
    private Brand brandId;

    public Collection() {
    }

    public Collection(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
    public List<CollectionDetail> getCollectionDetailList() {
        return collectionDetailList;
    }

    public void setCollectionDetailList(List<CollectionDetail> collectionDetailList) {
        this.collectionDetailList = collectionDetailList;
    }

    public Brand getBrandId() {
        return brandId;
    }

    public void setBrandId(Brand brandId) {
        this.brandId = brandId;
    }


    public boolean equals(Object object) {
      
        if (!(object instanceof Collection)) {
            return false;
        }
        Collection other = (Collection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Collection[ id=" + id + " ]";
    }
    
}
