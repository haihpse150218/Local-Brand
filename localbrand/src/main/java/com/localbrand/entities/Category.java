package com.localbrand.entities;

import java.io.Serializable;
import java.util.List;
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer id;
 
    private String name;
  
    private String description;
  
    private Integer gender;
   
    private Boolean status;
  
    private List<Product> productList;
   
    private List<BrandCategory> brandCategoryList;

    public Category() {
    }

    public Category(Integer id) {
        this.id = id;
    }

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<BrandCategory> getBrandCategoryList() {
        return brandCategoryList;
    }

    public void setBrandCategoryList(List<BrandCategory> brandCategoryList) {
        this.brandCategoryList = brandCategoryList;
    }

   
    public boolean equals(Object object) {
       
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category[ id=" + id + " ]";
    }
    
}
