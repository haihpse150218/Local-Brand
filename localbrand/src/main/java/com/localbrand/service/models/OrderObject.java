package com.localbrand.service.models;

import java.util.Date;

import com.localbrand.entities.Customer;
import com.localbrand.entities.Payment;

public class OrderObject {
	
	private Integer id;
	   
    private Date orderDate;
    
    private Double total;
 
    private Double tax;
   
    private Customer customerId;
   
    private Payment payId;
    
    private String status;

	public OrderObject() {
		super();
	}

	public OrderObject(Integer id, Date orderDate, Double total, Double tax, Customer customerId, Payment payId,
			String status) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.total = total;
		this.tax = tax;
		this.customerId = customerId;
		this.payId = payId;
		this.status = status;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public Payment getPayId() {
		return payId;
	}

	public void setPayId(Payment payId) {
		this.payId = payId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderObject [id=" + id + ", orderDate=" + orderDate + ", total=" + total + ", tax=" + tax
				+ ", customerId=" + customerId + ", payId=" + payId + ", status=" + status + "]";
	}
	
    
}
