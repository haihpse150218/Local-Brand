package com.localbrand.entities;
import java.io.Serializable;

public class OrderDetailPK implements Serializable {

   
    private int orderId;
  
    private int productId;

    public OrderDetailPK() {
    }

    public OrderDetailPK(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    
    public boolean equals(Object object) {
        if (!(object instanceof OrderDetailPK)) {
            return false;
        }
        OrderDetailPK other = (OrderDetailPK) object;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderDetailPK[ orderId=" + orderId + ", productId=" + productId + " ]";
    }
    
}
