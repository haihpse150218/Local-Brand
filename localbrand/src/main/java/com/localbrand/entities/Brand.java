package com.localbrand.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer id;
    
    private String name;
   
    private String description;
    
    private String status;
    
    private String logo;
    
    private String banner;
    
    private Date createDate;
   
    private List<BrandAccount> brandAccountList;
   

    private List<Store> storeList;
  
    private List<BrandCategory> brandCategoryList;
  
    private List<Collection> collectionList;

    public Brand() {
    }

    public Brand(Integer id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   
    public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<BrandAccount> getBrandAccountList() {
        return brandAccountList;
    }

    public void setBrandAccountList(List<BrandAccount> brandAccountList) {
        this.brandAccountList = brandAccountList;
    }

    public List<Store> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Store> storeList) {
		this.storeList = storeList;
	}

	public List<BrandCategory> getBrandCategoryList() {
        return brandCategoryList;
    }

    public void setBrandCategoryList(List<BrandCategory> brandCategoryList) {
        this.brandCategoryList = brandCategoryList;
    }


    public List<Collection> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(List<Collection> collectionList) {
        this.collectionList = collectionList;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Brand)) {
            return false;
        }
        Brand other = (Brand) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Brand[ id=" + id + " ]";
    }
    
}
