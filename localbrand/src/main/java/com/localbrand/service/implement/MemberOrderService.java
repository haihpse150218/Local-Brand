package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.localbrand.entities.Feedback;
import com.localbrand.entities.Order;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.Product;
import com.localbrand.service.IMemberOrderHistory;
import com.localbrand.sessionbeans.BrandFacade;
import com.localbrand.sessionbeans.FeedbackFacade;
import com.localbrand.sessionbeans.OrderDetailFacade;
import com.localbrand.sessionbeans.OrderFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class MemberOrderService implements IMemberOrderHistory {
	OrderFacade orfc = new OrderFacade();
	OrderDetailFacade odfc = new OrderDetailFacade();
	BrandFacade brfc = new BrandFacade();
	ProductFacade prfc = new ProductFacade();
	FeedbackFacade fbfc = new FeedbackFacade();

	public List<Order> getMemberListOrder(int cusid, String status) throws SQLException {
		List<Order> list = new ArrayList<>();
		List<Order> listOr = new ArrayList<>();
		List<OrderDetail> listOd = new ArrayList<>();
		List<Feedback> listFb = new ArrayList<>();

		listOd = odfc.findAll();
		listOr = orfc.findAll();
		listFb = fbfc.findAll();

		for (Order or : listOr) {
			// cusid = cusid trong order
			if (or.getCustomerId().getId() == cusid) {
				if (status == "" || status == null) {

					// them list order detail vao
					List<OrderDetail> listOdbyOr = new ArrayList<>();
					for (OrderDetail od : listOd) {
						// orderid = orderid trong order details
						if (od.getOrderDetailPK().getOrderId() == or.getId()) {

							Product product = prfc.find(od.getProduct().getId());
							product.setBrandId(brfc.find(product.getBrandId().getId()));
							od.setProduct(product);

							listOdbyOr.add(od);
						}
					}
					or.setOrderDetailList(listOdbyOr);
					/*
					 * //them list feedback vao List<Feedback> listFbbyOr = new ArrayList<>(); for
					 * (Feedback fb : listFb) { // orderid = orderid trong order details if
					 * (fb.getOrderId().getId() == or.getId()) { listFbbyOr.add(fb); } }
					 * or.setFeedbackList(listFbbyOr);
					 */
					list.add(or);
				} else if (or.getStatus().equalsIgnoreCase(status)) {

					// them list order detail vao
					List<OrderDetail> listOdbyOr = new ArrayList<>();
					for (OrderDetail od : listOd) {
						// orderid = orderid trong order details
						if (od.getOrderDetailPK().getOrderId() == or.getId()) {

							Product product = prfc.find(od.getProduct().getId());
							product.setBrandId(brfc.find(product.getBrandId().getId()));
							od.setProduct(product);

							listOdbyOr.add(od);
						}
					}
					or.setOrderDetailList(listOdbyOr);
					list.add(or);
				}

			}
		}
		if (list.size() == 0)
			return null;
		Collections.reverse(list);
		return list;
	}

	public void setOrderStatus(int orderid, String status) throws SQLException {
		Order order = orfc.find(orderid);
		order.setStatus(status);
		orfc.edit(order);
	}

	public OrderDetail getOrderDetail(int orderid, int productid) throws SQLException {
		List<OrderDetail> list = odfc.findAll();
		for (OrderDetail details : list) {
			// orderid = orderid trong order details
			if (details.getOrderDetailPK().getOrderId() == orderid && details.getProduct().getId() == productid) {
				details.setProduct(prfc.find(details.getProduct().getId()));
				details.setOrder1(orfc.find(details.getOrder1().getId()));
				return details;
			}
		}
		return null;
	}

	public Feedback getFeedback(int orderid, int productid) throws SQLException {
		List<Feedback> list = fbfc.findAll();
		for (Feedback fb : list) {
			if (fb.getProductId().getId() == productid && fb.getOrderId().getId() == orderid) {
				return fb;
			}
		}
		return null;
	}

	@Override
	public void createFeedback(int orderid, int detailid, String txtCmt, Double voting) throws SQLException {
		// tao feedback, neu co roi thi edit
		Feedback oldfb = getFeedback(orderid, detailid);
		if (oldfb == null) {
			Feedback newfb = new Feedback();
			newfb.setFeedbackTime(new Date());
			newfb.setOrderId(new Order(orderid));
			newfb.setProductId(new Product(detailid));
			newfb.setStatus(1);
			newfb.setTextComment(txtCmt);
			newfb.setVoting(voting);
			fbfc.create(newfb);
		} else {
			oldfb.setTextComment(txtCmt);
			oldfb.setVoting(voting);
			fbfc.edit(oldfb);
		}

		// tinh trung binh *
		double totalStar = 0;
		int count = 0;
		List<Feedback> list = fbfc.findAll();
		for (Feedback fb : list) {
			Product product = prfc.find(fb.getProductId().getId());
			if (product.getId() == detailid) {
				totalStar += fb.getVoting();
				count += 1;
			} else if (product.getIsMaster() == false) {
				if (product.getParentId().getId() == detailid) {
					totalStar += fb.getVoting();
					count += 1;
				}
			}
		}
		// tinh so star
		double star = 0;
		if (count != 0) {
			star = totalStar / count;
		}

		Product product = prfc.find(detailid);
		if (product.getIsMaster() == true)
			product.setStars(star);
		else {
			product = prfc.find(product.getParentId().getId());
			product.setStars(star);
		}
		prfc.edit(product);
	}
}
