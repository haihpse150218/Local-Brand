package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.localbrand.entities.BrandCategory;
import com.localbrand.entities.Customer;
import com.localbrand.entities.Order;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.Product;
import com.localbrand.service.IHomeAdmin;
import com.localbrand.service.models.OrderObject;
import com.localbrand.service.models.ProductRevenue;
import com.localbrand.sessionbeans.BrandCategoryFacade;
import com.localbrand.sessionbeans.CategoryFacade;
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

		for (OrderObject orderObject : listOrder) {
			if (orderObject.getOrderDate().compareTo(start) >= 0 && orderObject.getOrderDate().compareTo(end) < 0) {

				totalSales += orderObject.getTotal();

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

		for (OrderObject orderObject : listOrder) {
			if (orderObject.getOrderDate().compareTo(start) >= 0 && orderObject.getOrderDate().compareTo(end) < 0) {
				totalSales += orderObject.getTotal();

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
				if (cus.getId() == orderObject.getCustomerId().getId()) {
					flag = true;
					break;
				}
			}
			if (flag == false) {
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
				if (cus.getId() == orderObject.getCustomerId().getId()) {
					flag = true;
					break;
				}
			}
			if (flag == false) {
				newMembers += 1;
			}
		}
		return newMembers;
	}

	public boolean isOrdered(OrderObject o) {
		boolean flag = false;

		if (o.getStatus().equalsIgnoreCase("Delivered") || o.getStatus().equalsIgnoreCase("Completed")) {
			flag = true;
		}

		return flag;
	}

	public boolean isCanceled(OrderObject o) {
		boolean flag = false;

		if (o.getStatus().equalsIgnoreCase("Canceled") || o.getStatus().equalsIgnoreCase("Cancel")) {
			flag = true;
		}
		return flag;
	}

	public List<OrderObject> getThisWeekOrderListByBrandId(List<OrderObject> listOrder, Date date) {
		List<OrderObject> result = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i + 1);
		Date start = c.getTime();
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();
		for (OrderObject orderObject : listOrder) {
			if (orderObject.getOrderDate().compareTo(start) >= 0 && orderObject.getOrderDate().compareTo(end) <= 0) {
				result.add(orderObject);
			}
		}

		return result;
	}

	public List<ProductRevenue> listReportProduct(List<OrderObject> list) {
		List<ProductRevenue> result = new ArrayList<>();
		for (OrderObject o : list) {
			for (OrderDetail od : o.getListOrderDetail()) {
				boolean flag = false;

				Product p = od.getProduct();
				for (ProductRevenue pr : result) {
					if (pr.getId() == p.getId()) {
						flag = true;
						double proceeds = pr.getProceeds();
						if (isOrdered(o)) {
							proceeds += od.getPrice() * (1 - od.getDiscount()) * od.getQuantity();
						}
						pr.setProceeds(proceeds);
						int numberOrdered = pr.getNumberOrdered() + od.getQuantity();
						pr.setNumberOrdered(numberOrdered);

						int numberDelivered = pr.getNumberDelivered() + (isOrdered(o) ? od.getQuantity() : 0);
						pr.setNumberDelivered(numberDelivered);
						int numberCancel = pr.getNumberCancel() + (isCanceled(o) ? od.getQuantity() : 0);
						pr.setNumberCancel(numberCancel);

						flag = true;
					}
				}
				if (flag == false) {
					ProductRevenue productRevenue = new ProductRevenue();
					productRevenue.setId(p.getId());
					productRevenue.setName(p.getName());
					productRevenue.setImg(p.getIsMaster() ? p.getImgMaster() : p.getImgChild());
					productRevenue.setDiscount(od.getDiscount());
					productRevenue.setPrice(od.getPrice());
					double proceeds = 0;

					if (isOrdered(o)) {
						proceeds += od.getPrice() * (1 - od.getDiscount()) * od.getQuantity();
					}
					productRevenue.setProceeds(proceeds);
					productRevenue.setNumberOrdered(od.getQuantity());
					productRevenue.setNumberDelivered(isOrdered(o) ? od.getQuantity() : 0);
					productRevenue.setNumberCancel(isCanceled(o) ? od.getQuantity() : 0);

					result.add(productRevenue);
				}
			}

		}

		return result;
	}

	public List<BrandCategory> getListBrandCateByBrandId(int brandId) throws SQLException {
		BrandCategoryFacade bcf = new BrandCategoryFacade();
		CategoryFacade cf = new CategoryFacade();
		List<BrandCategory> listCate = new ArrayList<>();

		for (BrandCategory brandCategory : bcf.findAll()) {
			if (brandCategory.getBrand().getId() == brandId) {
				brandCategory.setCategory(cf.find(brandCategory.getBrandCategoryPK().getCateId()));
				listCate.add(brandCategory);
			}

		}
		return listCate;

	}
	
	public  List<BrandCategory> addBrandCate(String cateName){
		List<BrandCategory> listCate = new ArrayList<>();
		
		
		
		
		
		return listCate;
	}

}
