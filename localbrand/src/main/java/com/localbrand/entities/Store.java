package com.localbrand.entities;
import java.io.Serializable;
import java.util.List;

public class Store implements Serializable {

    private static final long serialVersionUID = 1L;
   
   
    private Integer id;
   
    private String lat;
   
    private String lon;
   
    private String phone;
  
    private List<Brand> brandList;

    public Store() {
    }

    public Store(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

  
    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

  
    public boolean equals(Object object) {
       
        if (!(object instanceof Store)) {
            return false;
        }
        Store other = (Store) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Store[ id=" + id + " ]";
    }
    
}
