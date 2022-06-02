package com.localbrand.entities;

import java.io.Serializable;


public class BrandCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    
    protected BrandCategoryPK brandCategoryPK;
    
    private String name;
   
  
    private String description;
    
    private Boolean status;
  
    private Brand brand;
  
    private Category category;

    public BrandCategory() {
    }

    public BrandCategory(BrandCategoryPK brandCategoryPK) {
        this.brandCategoryPK = brandCategoryPK;
    }

    public BrandCategory(BrandCategoryPK brandCategoryPK, String name) {
        this.brandCategoryPK = brandCategoryPK;
        this.name = name;
    }

    public BrandCategory(int cateId, int brandId) {
        this.brandCategoryPK = new BrandCategoryPK(cateId, brandId);
    }

    public BrandCategoryPK getBrandCategoryPK() {
        return brandCategoryPK;
    }

    public void setBrandCategoryPK(BrandCategoryPK brandCategoryPK) {
        this.brandCategoryPK = brandCategoryPK;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean equals(Object object) {
       
        if (!(object instanceof BrandCategory)) {
            return false;
        }
        BrandCategory other = (BrandCategory) object;
        if ((this.brandCategoryPK == null && other.brandCategoryPK != null) || (this.brandCategoryPK != null && !this.brandCategoryPK.equals(other.brandCategoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BrandCategory[ brandCategoryPK=" + brandCategoryPK + " ]";
    }
    
}
