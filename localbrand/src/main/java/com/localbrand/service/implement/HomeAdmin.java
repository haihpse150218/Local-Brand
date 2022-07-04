package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.localbrand.entities.Customer;
import com.localbrand.entities.Order;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.Product;
import com.localbrand.service.IHomeAdmin;
import com.localbrand.service.models.OrderObject;
import com.localbrand.sessionbeans.CustomerFacade;
import com.localbrand.sessionbeans.OrderDetailFacade;
import com.localbrand.sessionbeans.OrderFacade;
import com.localbrand.sessionbeans.ProductFacade;

public class HomeAdmin implements IHomeAdmin {
	private HomeAdmin() {
	}

	private static HomeAdmin instance = new HomeAdmin();

	public static HomeAdmin getInstance() {
		if (instance == null) {
			instance = new HomeAdmin();
		}
		return instance;
	}

	public List<OrderObject> getOrderListByBrandId(Integer id) {
		OrderFacade of = new OrderFacade();
		OrderDetailFacade odf = new OrderDetailFacade();
		ProductFacade pf = new ProductFacade();
		CustomerFacade cf = new CustomerFacade();

		List<OrderObject> listOrderObject = new ArrayList<>();
		try {

			for (OrderDetail od : odf.findAll()) {
				boolean flag = false;

				Product product = pf.find(od.getProduct().getId());

				Order order = of.find(od.getOrder1().getId());

				if (product.getBrandId().getId() == id) {
					for (OrderObject o : listOrderObject) {
						if (o.getId() == order.getId()) {
							double totalPrice = caculateTotalPrice(od);
							o.setTotal(o.getTotal() + totalPrice);
							od.setProduct(product);
							o.AddOrderDetai(od);
							flag = true;
							break;
						}
					}

					if (flag == false) {
						OrderObject orderObject = new OrderObject();
						orderObject.setId(order.getId());
						orderObject.setOrderDate(order.getOrderDate());
						orderObject.setCustomerId(cf.find(order.getCustomerId().getId()));
						orderObject.setPayId(order.getPayId());
						orderObject.setStatus(order.getStatus());
						orderObject.setTax(order.getTax());
						double totalPrice = caculateTotalPrice(od) + orderObject.getTotal();
						orderObject.setTotal(totalPrice);
						od.setProduct(product);
						orderObject.AddOrderDetai(od);
						listOrderObject.add(orderObject);
					}

				}

			}
		} catch (SQLException e) {
			System.out.println("Error HomeAdminService: " + e);
		}

		return listOrderObject;
	}

	public double caculateTotalPrice(OrderDetail od) {
		OrderDetailFacade of = new OrderDetailFacade();
		double totalPrice = od.getPrice() * (1 - od.getDiscount()) * od.getQuantity();
		return totalPrice;
	}

	public double SalesThisWeek(List<OrderObject> listOrder) {
		double totalSales = 0;
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 7 + 1);
		Date start = c.getTime();
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();
		System.out.println("check1: " + start);
		System.out.println("check2: " + end);

		for (OrderObject orderObject : listOrder) {
			if (orderObject.getOrderDate().compareTo(start) >= 0 && orderObject.getOrderDate().compareTo(end) < 0) {
				System.out.println("orderDate: " + orderObject.getOrderDate());
				totalSales += orderObject.getTotal();
				System.out.println("totalsales: " + totalSales);
			}
		}

		return totalSales;
	}

	public double SalesLastWeek(List<OrderObject> listOrder) {
		double totalSales = 0;
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 14 + 1);
		Date start = c.getTime();
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();
		System.out.println(start + " - " + end);
		for (OrderObject orderObject : listOrder) {
			if (orderObject.getOrderDate().compareTo(start) >= 0 && orderObject.getOrderDate().compareTo(end) < 0) {
				totalSales += orderObject.getTotal();
				System.out.println("totalSales");
			}
		}
		return totalSales;
	}

	public int TotalOrdersThisWeek(List<OrderObject> listOrder) {
		int totalOrders = 0;
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 7 + 1);
		Date start = c.getTime();
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();
		for (OrderObject orderObject : listOrder) {
			if (orderObject.getOrderDate().compareTo(start) >= 0 && orderObject.getOrderDate().compareTo(end) < 0) {
				totalOrders += 1;
			}
		}

		return totalOrders;
	}

	public int TotalOrdersLastWeek(List<OrderObject> listOrder) {
		int totalOrders = 0;
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 14 + 1);
		Date start = c.getTime();
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();
		for (OrderObject orderObject : listOrder) {
			if (orderObject.getOrderDate().compareTo(start) >= 0 && orderObject.getOrderDate().compareTo(end) < 0) {
				totalOrders += 1;
			}
		}
		return totalOrders;
	}

	public int NewCustomerThisWeek(List<OrderObject> listOrder) {
		int newMembers = 0;
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 7 + 1);
		Date start = c.getTime();
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();
		List<Customer> listCustomer = new ArrayList<>();
		List<OrderObject> listOrderNowAday = new ArrayList<>();
		for (OrderObject orderObject : listOrder) {
			if (orderObject.getOrderDate().compareTo(start) < 0) {
				if (!listCustomer.contains(orderObject.getCustomerId())) {
					listCustomer.add(orderObject.getCustomerId());
				}

			}
			if (orderObject.getOrderDate().compareTo(start) >= 0) {
				listOrderNowAday.add(orderObject);
			}
		}
		for (OrderObject orderObject : listOrderNowAday) {
			boolean flag = false;
			for (Customer cus : listCustomer) {
				if(cus.getId() == orderObject.getCustomerId().getId()) {
					flag =true;
					break;
				}
			}
			if(flag == false) {
				newMembers += 1;
			}
		}
		return newMembers;
	}
	public int NewCustomerLastWeek(List<OrderObject> listOrder) {
		int newMembers = 0;
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 14 + 1);
		Date start = c.getTime();
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();
		List<Customer> listCustomer = new ArrayList<>();
		List<OrderObject> listOrderNowAday = new ArrayList<>();
		for (OrderObject orderObject : listOrder) {
			
			if (orderObject.getOrderDate().compareTo(start) < 0 && orderObject.getOrderDate().compareTo(end) >= 0) {
				if (!listCustomer.contains(orderObject.getCustomerId())) {
					listCustomer.add(orderObject.getCustomerId());
				}

			}
			if (orderObject.getOrderDate().compareTo(start) >= 0 && orderObject.getOrderDate().compareTo(end) < 0) {
				listOrderNowAday.add(orderObject);
			}
		}
		for (OrderObject orderObject : listOrderNowAday) {
			boolean flag = false;
			for (Customer cus : listCustomer) {
				if(cus.getId() == orderObject.getCustomerId().getId()) {
					flag =true;
					break;
				}
			}
			if(flag == false) {
				newMembers += 1;
			}
		}
		return newMembers;
	}
}
