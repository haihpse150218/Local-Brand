package com.localbrand.entities;

import java.io.Serializable;
import java.util.List;
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer id;
   
    private String name;
   

    private String username;
   
    private String password;
   
    private String avatar;
   
    private String email;
   
    private String phone;
  
    private String address;

    private String status;
 
    private Integer coins;
 
    private List<Order> order1List;
  
    private MembershipTier rankId;

    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Customer(Integer id, String username, String password, String email, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    
    public List<Order> getOrder1List() {
        return order1List;
    }

    public void setOrder1List(List<Order> order1List) {
        this.order1List = order1List;
    }

    public MembershipTier getRankId() {
        return rankId;
    }

    public void setRankId(MembershipTier rankId) {
        this.rankId = rankId;
    }

  
    public boolean equals(Object object) {
        
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer[ id=" + id + " ]";
    }
    
}
