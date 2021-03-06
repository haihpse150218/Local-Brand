package com.localbrand.service.implement;

import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.localbrand.entities.BrandCategory;
import com.localbrand.entities.Collection;
import com.localbrand.entities.CollectionDetail;
import com.localbrand.entities.Customer;
import com.localbrand.entities.Order;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.Product;
import com.localbrand.service.IHomeAdmin;
import com.localbrand.service.models.OrderObject;
import com.localbrand.service.models.ProductRevenue;
import com.localbrand.sessionbeans.BrandCategoryFacade;
import com.localbrand.sessionbeans.CategoryFacade;
import com.localbrand.sessionbeans.CollectionDetailFacade;
import com.localbrand.sessionbeans.CollectionFacade;
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
		double totalPrice = od.getPrice() ;
		return totalPrice;
	}

	public double SalesThisWeek(List<OrderObject> listOrder) {
		double totalSales = 0;
		Date date = new Date();
		Calendar c = Calendar.getInstance();

		c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i + 1);
		Date start = c.getTime();
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();
		System.out.println("s" +start +" end "+ end);

		for (OrderObject orderObject : listOrder) {
			if (orderObject.getOrderDate().compareTo(start) >= 0 && orderObject.getOrderDate().compareTo(end) <= 0) {

				totalSales += orderObject.getTotal();

			}
		}

		return totalSales;
	}

	public double SalesLastWeek(List<OrderObject> listOrder) {
		double totalSales = 0;
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 7 + 1);
		Date start = c.getTime();
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();
		System.out.println("Last week s" +start +" end "+ end);
		for (OrderObject orderObject : listOrder) {
			System.out.println("Last week s" +orderObject.getOrderDate() +" cmp "+ orderObject.getOrderDate().compareTo(start));
			if (orderObject.getOrderDate().compareTo(start) >= 0 && orderObject.getOrderDate().compareTo(end) <= 0) {
				totalSales += orderObject.getTotal();

			}
		}
		return totalSales;
	}

	public int TotalOrdersThisWeek(List<OrderObject> listOrder) {
		int totalOrders = 0;
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i  + 1);
		Date start = c.getTime();
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();
		for (OrderObject orderObject : listOrder) {
			if (orderObject.getOrderDate().compareTo(start) >= 0 && orderObject.getOrderDate().compareTo(end) <= 0) {
				totalOrders += 1;
			}
		}

		return totalOrders;
	}

	public int TotalOrdersLastWeek(List<OrderObject> listOrder) {
		int totalOrders = 0;
		Date date = new Date();
		Calendar c = Calendar.getInstance();


		c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 7 + 1);
		Date start = c.getTime();
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();
		for (OrderObject orderObject : listOrder) {
			if (orderObject.getOrderDate().compareTo(start) >= 0 && orderObject.getOrderDate().compareTo(end) <= 0) {
				totalOrders += 1;
			}
		}
		return totalOrders;
	}

	public int NewCustomerThisWeek(List<OrderObject> listOrder) {
		int newMembers = 0;
		Date date = new Date();
		Calendar c = Calendar.getInstance();

		c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i + 1);
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

		c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 7 + 1);
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
		c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
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

						if (pr.getOrderDate().compareTo(o.getOrderDate()) < 0) {
							pr.setOrderDate(o.getOrderDate());
						}
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
					productRevenue.setOrderDate(o.getOrderDate());
					productRevenue.setMaster(p.getIsMaster());

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

	public List<BrandCategory> addBrandCate(String cateName) {
		List<BrandCategory> listCate = new ArrayList<>();
		return listCate;
	}

	public List<ProductRevenue> ListAllReportProduct(List<ProductRevenue> listReportProduct, Integer brandId) {
		System.out.println("brandID" + brandId);
		List<ProductRevenue> result = new ArrayList<>(listReportProduct);

		ProductFacade pf = new ProductFacade();
		try {
			for (Product p : pf.findAll()) {
				if (p.getBrandId().getId() == brandId) {
					boolean flag = true;
					for (ProductRevenue productRevenue : listReportProduct) {
						if (p.getId() == productRevenue.getId()) {
							
							// set again price folow new price and discount
							productRevenue.setPrice(p.getPrice());
							productRevenue.setDiscount(p.getDiscount());
							productRevenue.setStatus(p.getStatus());
							if(productRevenue.getStatus().equalsIgnoreCase("Disable")) {
								result.remove(productRevenue);
							}
							flag = false;
							break;
						}
					}
					if (flag) {
						ProductRevenue productRevenue = new ProductRevenue();
						productRevenue.setId(p.getId());
						productRevenue.setName(p.getName());
						productRevenue.setImg(p.getIsMaster() ? p.getImgMaster() : p.getImgChild());
						productRevenue.setDiscount(p.getDiscount());
						productRevenue.setPrice(p.getPrice());
						productRevenue.setProceeds(0);
						productRevenue.setNumberOrdered(0);
						productRevenue.setNumberDelivered(0);
						productRevenue.setNumberCancel(0);
						productRevenue.setMaster(p.getIsMaster());
						productRevenue.setStatus(p.getStatus());
						if(productRevenue.getStatus().equalsIgnoreCase("Active")) {
							result.add(productRevenue);
						}
						
					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private static int YEAR_COUNT = 2;
	public Map<Integer, Double> calculateRevenueByTime(int brandId) {
		Map<Integer, Double> result = new HashMap<Integer, Double>();
		OrderFacade of = new OrderFacade();
		OrderDetailFacade odf = new OrderDetailFacade();
		ProductFacade pf = new ProductFacade();
		try {
			for (OrderDetail od : odf.findAll()) {
				Product prod = pf.find(od.getOrderDetailPK().getProductId());
				if (prod.getBrandId().getId() == brandId) {
					Order o = of.find(od.getOrderDetailPK().getOrderId());
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(o.getOrderDate());
					int orderYear = calendar.get(Calendar.YEAR);
					calendar.setTime(new Date());
					int thisYear = calendar.get(Calendar.YEAR);
					if (thisYear - orderYear <= YEAR_COUNT) {
						if (result.containsKey(orderYear)) {
							double newPrice = result.get(orderYear) + od.getPrice();
							result.put(orderYear, newPrice);
						} else {
							result.put(orderYear, od.getPrice());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private double calculateRevenueFromProduct(int productId) {
		double result = 0;
		OrderDetailFacade odf = new OrderDetailFacade();
		try {
			for (OrderDetail od : odf.findAll()) {
				if (od.getOrderDetailPK().getProductId() == productId) {
					result += od.getPrice();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private List<Product> getProductFromCollection(int collectionId) {
		List<Product> list = new ArrayList<Product>();
		ProductFacade pf = new ProductFacade();
		CollectionDetailFacade cdf = new CollectionDetailFacade();
		try {
			for (CollectionDetail cd : cdf.findAll()) {
				if (cd.getCollectionDetailPK().getCollectionId() == collectionId) {
					list.add(pf.find(cd.getCollectionDetailPK().getProductId()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private List<Collection> getCollectionFromBrand(int brandId) {
		List<Collection> list = new ArrayList<Collection>();
		CollectionFacade cf = new CollectionFacade();
		try {
			for (Collection c : cf.findAll()) {
				if (c.getBrandId().getId() == brandId) {
					list.add(c);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Map<Collection, Double> getCollectionRevenue(int brandId) {
		Map<Collection, Double> revenueList = new HashMap<Collection, Double>();
		for (Collection collection : getCollectionFromBrand(brandId)) {
			Double revenue = 0d;
			for (Product product : getProductFromCollection(collection.getId())) {
				revenue += calculateRevenueFromProduct(product.getId());
			}
			revenueList.put(collection, revenue);
		}
		return revenueList;
	}
	
	private static String removeAccent(String s) {
		  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		  return pattern.matcher(temp).replaceAll("");
		 }
}
