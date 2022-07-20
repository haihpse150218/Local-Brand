package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.localbrand.entities.Customer;
import com.localbrand.entities.MembershipTier;
import com.localbrand.service.IViewCartService;
import com.localbrand.service.models.Cart;
import com.localbrand.service.models.Item;
import com.localbrand.sessionbeans.CustomerFacade;
import com.localbrand.sessionbeans.MembershipTierFacade;

public class ViewCartService implements IViewCartService {

	CustomerFacade csfc = new CustomerFacade();
	MembershipTierFacade msfc = new MembershipTierFacade();
	
	public List<Cart> getListCartProductByBrand (Cart cart){
		
		//tao map co Key la brand id, Value la 1 list chua cac product co brand id do
		Map<Integer,List<Item>> map = new HashMap<>();
		
		//duyet phan tu co trong cart 
		for (int key : cart.getMap().keySet()) {
			//quantity > 0
			if (cart.getMap().get(key).getQuantity()>0) {
				//kiem tra brand id co trong map chua
				if (map.get(cart.getMap().get(key).getProduct().getBrandId().getId())==null) {
					//chua co thi put vao
					List<Item> list = new ArrayList<>();
					list.add(cart.getMap().get(key));
					map.put(cart.getMap().get(key).getProduct().getBrandId().getId(), list);
				} else {
					//co roi thi update
					List<Item> list = new ArrayList<>();
					list.addAll(map.get(cart.getMap().get(key).getProduct().getBrandId().getId()));
					list.add(cart.getMap().get(key));
					map.put(cart.getMap().get(key).getProduct().getBrandId().getId(), list);
				}
			}
		}
		
		//tao 1 list cart, moi cart la list product cua 1 brand id
		List<Cart> listResult = new ArrayList<>();
		//moi key la 1 brand
		for (int key : map.keySet()) {
			//moi cart la 1 brand
			Cart brandCart = new Cart();
			Map<Integer, Item> productMap = new HashMap<>();
			//lay het list item cua brand bo vao map
			for (Item item : map.get(key)) {
			productMap.put(item.getProduct().getId(), item);
			}
			brandCart.setMap(productMap);
			listResult.add(brandCart);
		}
		return listResult;
	}
	
	public Customer getCustomer (int cusid) throws SQLException {
		Customer cus = csfc.find(cusid);
		return cus;
	}
	
	public double getDiscount (int cusid) throws SQLException {
		Customer cus = getCustomer(cusid);
		double discount = 0;
		List <MembershipTier> list = new ArrayList<>();
		//chay vong lap check tung tier
		for (MembershipTier tier : list) {
			//neu coin khong du tier thi dung vong lap
			if (cus.getCoins()<tier.getMinimumCoins()) {
				break;
			}
			//gan discount cua tier hien tai vao, bat dau tu unrank
			discount = tier.getDiscount();
		}
		return discount;
	}
	
	public Cart updateOrderQuantity (int productid, int updQuantity, Cart cart) throws SQLException {
		//lay quantity hien tai ra
		int curQuantity = cart.getMap().get(productid).getQuantity();
		//tinh xem can add them bnh quantity de ra dung con so yeu cau (co the am co the duong)
		int addQuantity = updQuantity-curQuantity;
		//su dung lai tinh nang add co san
		cart.add(productid, addQuantity);
		return cart;
	}
	
	public void updateReceiveInfo (Customer cus) throws SQLException {
		//co the update name, email, phone, address
		Customer dummy = getCustomer(cus.getId());
		dummy.setName(cus.getName());
		dummy.setEmail(cus.getEmail());
		dummy.setPhone(cus.getPhone());
		dummy.setAddress(cus.getAddress());
		
		csfc.edit(dummy);
	}
	
	public double getTotalInCart (Cart cart) {
		double total = 0;
		
		for (int key : cart.getMap().keySet()) {
			int quantity = cart.getMap().get(key).getQuantity() ;
			double dis = cart.getMap().get(key).getProduct().getDiscount();
			double price = cart.getMap().get(key).getProduct().getPrice();
			total += quantity*dis*price;
		}
		return total;
	}
}
