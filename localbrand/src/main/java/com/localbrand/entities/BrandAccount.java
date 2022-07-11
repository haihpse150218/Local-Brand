package com.localbrand.entities;
import java.io.Serializable;
public class BrandAccount implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer id;
  
    private String name;
    
    private String username;
   
    private String password;
   
    private Boolean role;
  
    
    private Brand brandId;

    public BrandAccount() {
    }

    public BrandAccount(Integer id) {
        this.id = id;
    }

    public BrandAccount(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public Brand getBrandId() {
        return brandId;
    }

    public void setBrandId(Brand brandId) {
        this.brandId = brandId;
    }


    public boolean equals(Object object) {
       
        if (!(object instanceof BrandAccount)) {
            return false;
        }
        BrandAccount other = (BrandAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "BrandAccount [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", role=" + role + ", brandId=" + brandId + "]";
	}

    
    
}
