package com.localbrand.entities;
import java.io.Serializable;
import java.util.Date;
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
   
  
    private Integer id;
   
    private Date feedbackTime;
   
    private String textComment;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  
    private Double voting;
    
    private Integer status;
 
    private Product productId;
    
    private Order orderId;

    public Feedback() {
    }

    public Feedback(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public Double getVoting() {
        return voting;
    }

    public void setVoting(Double voting) {
        this.voting = voting;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }
    
    public Order getOrderId() {
		return orderId;
	}

	public void setOrderId(Order orderId) {
		this.orderId = orderId;
	}

	public boolean equals(Object object) {
      
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Feedback[ id=" + id + " ]";
    }
    
}
