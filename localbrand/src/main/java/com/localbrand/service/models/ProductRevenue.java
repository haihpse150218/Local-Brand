package com.localbrand.service.models;

public class ProductRevenue {
	private int id;
	private String name;
	private String img;
	private double discount;
	private double price;
	private double proceeds;
	private int numberOrdered;
	private int numberDelivered;
	private int numberCancel;
	public ProductRevenue() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getProceeds() {
		return proceeds;
	}
	public void setProceeds(double proceeds) {
		this.proceeds = proceeds;
	}
	public int getNumberOrdered() {
		return numberOrdered;
	}
	public void setNumberOrdered(int numberOrdered) {
		this.numberOrdered = numberOrdered;
	}
	public int getNumberDelivered() {
		return numberDelivered;
	}
	public void setNumberDelivered(int numberDelivered) {
		this.numberDelivered = numberDelivered;
	}
	public int getNumberCancel() {
		return numberCancel;
	}
	public void setNumberCancel(int numberCancel) {
		this.numberCancel = numberCancel;
	}
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ProductRevenue [id=" + id + ", name=" + name + ", img=" + img + ", discount=" + discount + ", price="
				+ price + ", proceeds=" + proceeds + ", numberOrdered=" + numberOrdered + ", numberDelivered="
				+ numberDelivered + ", numberCancel=" + numberCancel + "]";
	}
	
	
	
	
}
