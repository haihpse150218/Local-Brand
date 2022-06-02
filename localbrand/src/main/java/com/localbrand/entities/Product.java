package com.localbrand.entities;
import java.io.Serializable;
import java.util.List;
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
 
    private Integer id;
  
  
    private String name;
   
    private String description;
   
    private String status;
   
    private String color;
 
    private String size;
   
    
    private boolean isMaster;
    
    private String imgMaster;
    
    private String imgChild;
   
    
    private double price;
   
    private Integer container;
   
    private Double discount;
  
    private Double stars;
   
    private List<CollectionDetail> collectionDetailList;
 
    private Category cateId;
   
    private List<Product> productList;
    
    private Product parentId;
   
    private List<OrderDetail> orderDetailList;
  
    private List<Feedback> feedbackList;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, boolean isMaster, double price) {
        this.id = id;
        this.name = name;
        this.isMaster = isMaster;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(boolean isMaster) {
        this.isMaster = isMaster;
    }

    public String getImgMaster() {
        return imgMaster;
    }

    public void setImgMaster(String imgMaster) {
        this.imgMaster = imgMaster;
    }

    public String getImgChild() {
        return imgChild;
    }

    public void setImgChild(String imgChild) {
        this.imgChild = imgChild;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getContainer() {
        return container;
    }

    public void setContainer(Integer container) {
        this.container = container;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    
    public List<CollectionDetail> getCollectionDetailList() {
        return collectionDetailList;
    }

    public void setCollectionDetailList(List<CollectionDetail> collectionDetailList) {
        this.collectionDetailList = collectionDetailList;
    }

    public Category getCateId() {
        return cateId;
    }

    public void setCateId(Category cateId) {
        this.cateId = cateId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Product getParentId() {
        return parentId;
    }

    public void setParentId(Product parentId) {
        this.parentId = parentId;
    }

   
    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

 
    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

 
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product[ id=" + id + " ]";
    }
    
}
