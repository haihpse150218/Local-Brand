package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.localbrand.entities.Customer;
import com.localbrand.entities.Order;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.Payment;
import com.localbrand.entities.Product;
import com.localbrand.service.ICheckoutService;
import com.localbrand.service.IViewCartService;
import com.localbrand.service.models.Cart;
import com.localbrand.sessionbeans.CustomerFacade;
import com.localbrand.sessionbeans.OrderDetailFacade;
import com.localbrand.sessionbeans.OrderFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class CheckoutService implements ICheckoutService {
	
	CustomerFacade csfc = new CustomerFacade();
	OrderFacade orfc = new OrderFacade();
	OrderDetailFacade odfc = new OrderDetailFacade();
	IViewCartService vcsv = new ViewCartService();
	ProductFacade prfc = new ProductFacade();
	
	public void checkout (Cart cart,int cusid) throws SQLException {
		double total = (1-vcsv.getDiscount(cusid))*vcsv.getTotalInCart(cart);
		//chia cart ra thanh cac order le
		List<Cart> list = vcsv.getListCartProductByBrand(cart);
		
		//bat dau thuc hien add order va order details xuong database
		for (Cart brandOrders : list) {
			//add order
			Order order = new Order();
			order.setCustomerId(new Customer(cusid));
			//pay id hien tai mac dinh la 3 : COD
			order.setPayId(new Payment(3));
			//set Date
			order.setOrderDate(new Date());
			//mac dinh preparing
			order.setStatus("Preparing");
			//tinh total tung brand
			double totalByBrand = 0;
			for (int key : brandOrders.getMap().keySet()) {
				totalByBrand += brandOrders.getMap().get(key).getProduct().getPrice()
						*(1-vcsv.getDiscount(cusid) - brandOrders.getMap().get(key).getProduct().getDiscount());
			}
			//tax 10%
			order.setTax(totalByBrand*0.1);
			// + phi ship 30k
			order.setTotal(totalByBrand + 30000);
			orfc.create(order);
			
			//add order details
			for (int key : brandOrders.getMap().keySet()) {
				Product product = brandOrders.getMap().get(key).getProduct();
				int ordQuantity = brandOrders.getMap().get(key).getQuantity();
				OrderDetail orderDetails = new OrderDetail();
				//discount = tier dis + product dis
				orderDetails.setDiscount(vcsv.getDiscount(cusid)+product.getDiscount());
				orderDetails.setPrice(product.getPrice()* ( 1 - vcsv.getDiscount(cusid) - product.getDiscount()));
				orderDetails.setProduct(new Product(product.getId()));
				orderDetails.setQuantity(ordQuantity);
				
				//lay order id vua tao
				List<Order> listAllOrder = orfc.findAll();
				orderDetails.setOrder1(new Order(listAllOrder.get(listAllOrder.size()-1).getId()));
				
				odfc.create(orderDetails);
				
				//xoa bot quantity trong container cua product
				Product productUpd = prfc.find(product.getId());
				int container = productUpd.getContainer() - ordQuantity;
				productUpd.setContainer(container);
				prfc.edit(productUpd);
			}
		}
		
		//tinh gia tri thuc te don hang
		int addCoin = (int) total/100;
		//lay coin hien tai va add them vao
		Customer cus = vcsv.getCustomer(cusid);
		int updCoins = cus.getCoins()+addCoin;
		cus.setCoins(updCoins);
		csfc.edit(cus);
		
	}

}
