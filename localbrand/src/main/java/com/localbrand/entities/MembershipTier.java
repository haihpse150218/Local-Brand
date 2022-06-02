package com.localbrand.entities;
import java.io.Serializable;
import java.util.List;
public class MembershipTier implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer id;
    
    private String rank;
   
    private String description;
   
    private Double discount;
 
    private Integer minimumCoins;
  
    private List<Customer> customerList;

    public MembershipTier() {
    }

    public MembershipTier(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getMinimumCoins() {
        return minimumCoins;
    }

    public void setMinimumCoins(Integer minimumCoins) {
        this.minimumCoins = minimumCoins;
    }

    
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

   
    @Override
    public boolean equals(Object object) {
 
        if (!(object instanceof MembershipTier)) {
            return false;
        }
        MembershipTier other = (MembershipTier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MembershipTier[ id=" + id + " ]";
    }
    
}
