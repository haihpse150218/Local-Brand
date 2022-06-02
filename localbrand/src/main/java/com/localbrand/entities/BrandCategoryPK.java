package com.localbrand.entities;

import java.io.Serializable;
public class BrandCategoryPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int cateId;
    
    private int brandId;

    public BrandCategoryPK() {
    }

    public BrandCategoryPK(int cateId, int brandId) {
        this.cateId = cateId;
        this.brandId = brandId;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public boolean equals(Object object) {
        if (!(object instanceof BrandCategoryPK)) {
            return false;
        }
        BrandCategoryPK other = (BrandCategoryPK) object;
        if (this.cateId != other.cateId) {
            return false;
        }
        if (this.brandId != other.brandId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BrandCategoryPK[ cateId=" + cateId + ", brandId=" + brandId + " ]";
    }
    
}
