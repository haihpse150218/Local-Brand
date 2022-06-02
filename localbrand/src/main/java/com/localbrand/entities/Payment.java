package com.localbrand.entities;
import java.io.Serializable;
import java.util.List;

public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer id;
  
    private String payMethod;
  
    private String status;
    
    private List<Order> order1List;

    public Payment() {
    }

    public Payment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  
    public List<Order> getOrder1List() {
        return order1List;
    }

    public void setOrder1List(List<Order> order1List) {
        this.order1List = order1List;
    }

   
    public boolean equals(Object object) {
       
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Payment[ id=" + id + " ]";
    }
    
}
