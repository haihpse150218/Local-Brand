package com.localbrand.service.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.localbrand.entities.Customer;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.Payment;

public class OrderObject {
	
	private Integer id;//Order
	   
    private Date orderDate;//Order
    
    private Double total;//OrderDetail
 
    private Double tax;//Order
   
    private Customer customerId;//OrderDetail => Customer
   
    private Payment payId; //Order
    
    private String status;// Order
    private int quantity;
    
   
    
    public int getQuantity() {
    	quantity = 0;
    	for (OrderDetail orderDetail : listOrderDetail) {
    		quantity += orderDetail.getQuantity();
		}
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	private List<OrderDetail> listOrderDetail;
    public OrderObject() {
		super();
		listOrderDetail = new ArrayList<OrderDetail>();
		total = (double) 0;
	}
    

    public void AddOrderDetai(OrderDetail od) {
    	listOrderDetail.add(od);	
    }
	public List<OrderDetail> getListOrderDetail() {
		return listOrderDetail;
	}
	

	public void setListOrderDetail(List<OrderDetail> listOrderDetail) {
		this.listOrderDetail = listOrderDetail;
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
				+ ", customerId=" + customerId + ", payId=" + payId + ", status=" + status + ", listOrderDetail="
				+ listOrderDetail + "]";
	}

	
	
    
}
