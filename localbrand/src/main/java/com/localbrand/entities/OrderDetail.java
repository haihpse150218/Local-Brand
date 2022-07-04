package com.localbrand.entities;
import java.io.Serializable;
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;
  
    protected OrderDetailPK orderDetailPK;
  
    private int quantity;
 
    private Double discount;
  
   
    private double price;
   
    private Order order1;

    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(OrderDetailPK orderDetailPK) {
        this.orderDetailPK = orderDetailPK;
    }

    public OrderDetail(OrderDetailPK orderDetailPK, int quantity, double price) {
        this.orderDetailPK = orderDetailPK;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetail(int orderId, int productId) {
        this.orderDetailPK = new OrderDetailPK(orderId, productId);
    }

    public OrderDetailPK getOrderDetailPK() {
        return orderDetailPK;
    }

    public void setOrderDetailPK(OrderDetailPK orderDetailPK) {
        this.orderDetailPK = orderDetailPK;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order getOrder1() {
        return order1;
    }

    public void setOrder1(Order order1) {
        this.order1 = order1;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

   
    public boolean equals(Object object) {
       
        if (!(object instanceof OrderDetail)) {
            return false;
        }
        OrderDetail other = (OrderDetail) object;
        if ((this.orderDetailPK == null && other.orderDetailPK != null) || (this.orderDetailPK != null && !this.orderDetailPK.equals(other.orderDetailPK))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "OrderDetail [orderDetailPK=" + orderDetailPK + ", quantity=" + quantity + ", discount=" + discount
				+ ", price=" + price + ", order1=" + order1 + ", product=" + product + "]";
	}

    
    
}
