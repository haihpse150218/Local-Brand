package com.localbrand.service.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.localbrand.entities.CollectionDetail;
import com.localbrand.entities.Feedback;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.Product;
import com.localbrand.sessionbeans.CollectionDetailFacade;
import com.localbrand.sessionbeans.FeedbackFacade;
import com.localbrand.sessionbeans.OrderDetailFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class Cart {
	// String cho parameter product id, item de luu du lieu product + order quantity
	Map<Integer, Item> map;
	ProductFacade pf = new ProductFacade();
	CollectionDetailFacade cf = new CollectionDetailFacade();
	FeedbackFacade ff = new FeedbackFacade();
	OrderDetailFacade of = new OrderDetailFacade();

	public Cart() {
		map = new HashMap();
	}

	public void add(int id, int quantity) throws SQLException {
		if (map.get(id) == null) {
			Product product = pf.find(id);
			Item item = new Item();	
			item.setProduct(product);
			item.setQuantity(quantity);
			map.put(id, item);
		} else {
			Item item = map.get(id);
			int oldQuantity = item.getQuantity();
			int newQuantity = oldQuantity + quantity;
			
			update(id, newQuantity);
		}

	}

	public void update(int id, int quantity) {
//	        Lay item tu cart trong gio hang ra
		Item item = map.get(id);
//	        update quantity
		item.setQuantity(quantity);
	}

	public void delete(int id) {
//	        xoa item trong cart
		map.remove(id);
	}

	public void empty() {
//	        xoa item trong cart
		map.clear();
	}

	public Map<Integer, Item> getMap() {
		return map;
	}

	public void setMap(Map<Integer, Item> map) {
		this.map = map;
	}

}
