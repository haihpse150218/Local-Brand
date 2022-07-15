package com.localbrand.service.implement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localbrand.entities.Brand;
import com.localbrand.entities.Category;
import com.localbrand.entities.Collection;
import com.localbrand.entities.CollectionDetail;
import com.localbrand.entities.Feedback;
import com.localbrand.entities.OrderDetail;
import com.localbrand.entities.Product;
import com.localbrand.entities.Store;
import com.localbrand.service.IHome;
import com.localbrand.service.models.Cart;
import com.localbrand.sessionbeans.BrandFacade;
import com.localbrand.sessionbeans.CategoryFacade;
import com.localbrand.sessionbeans.CollectionDetailFacade;
import com.localbrand.sessionbeans.CollectionFacade;
import com.localbrand.sessionbeans.FeedbackFacade;
import com.localbrand.sessionbeans.OrderDetailFacade;
import com.localbrand.sessionbeans.ProductFacade;
import com.localbrand.sessionbeans.StoreFacade;

public class HomeService implements IHome {
	ProductFacade pf = new ProductFacade();
	CollectionDetailFacade cf = new CollectionDetailFacade();
	CollectionFacade clf = new CollectionFacade();
	CategoryFacade ctf = new CategoryFacade();
	FeedbackFacade ff = new FeedbackFacade();
	OrderDetailFacade of = new OrderDetailFacade();
	BrandFacade bf = new BrandFacade();
	StoreFacade sf = new StoreFacade();

	@Override
	public List<Product> getTopProduct() throws Exception {

		List<Product> listTopProduct = new ArrayList<>();
		List<CollectionDetail> listC = new ArrayList<>();
		List<Feedback> listF = new ArrayList<>();
		List<OrderDetail> listOD = new ArrayList<>();
		List<Product> listAllProduct = new ArrayList<>();

		listAllProduct = pf.findAll();
		listC = cf.findAll();
		listF = ff.findAll();
		listOD = of.findAll();

		int quantityCount = 0;

		Product top1 = new Product();
		top1.setBrandId(new Brand(0));
		Product top2 = new Product();
		top2.setBrandId(new Brand(0));
		Product top3 = new Product();
		top3.setBrandId(new Brand(0));
		Product top4 = new Product();
		top4.setBrandId(new Brand(0));
		Product top5 = new Product();
		top5.setBrandId(new Brand(0));
		Product top6 = new Product();
		top6.setBrandId(new Brand(0));
		int elo1 = 0, elo2 = 0, elo3 = 0, elo4 = 0, elo5 = 0, elo6 = 0;

		for (Product product : listAllProduct) {

			// kiem tra co phai master khong
			if (product.getIsMaster() == true && (product.getStatus().equalsIgnoreCase("New")
					|| product.getStatus().equalsIgnoreCase("Active"))) {
				List<CollectionDetail> list1 = new ArrayList<>();
				List<Feedback> list2 = new ArrayList<>();
				List<OrderDetail> list3 = new ArrayList<>();
				List<Product> list4 = new ArrayList<>();

				// lay variants
				for (Product dummy : listAllProduct) {
					if (product.getId() == dummy.getParentId().getId()) {
						list4.add(dummy);
					}
				}
				for (CollectionDetail dummy : listC) {
					if (product.getId() == dummy.getProduct().getId()) {
						list1.add(dummy);
					}
				}
				for (Feedback dummy : listF) {
					// master
					if (product.getId() == dummy.getProductId().getId()) {
						list2.add(dummy);
					}
					// var
					for (Product var : list4) {
						if (var.getId() == dummy.getProductId().getId()) {
							list2.add(dummy);
						}
					}
				}
				for (OrderDetail dummy : listOD) {
					// master
					if (product.getId() == dummy.getProduct().getId()) {
						list3.add(dummy);
					}
					// var
					for (Product var : list4) {
						if (var.getId() == dummy.getProduct().getId()) {
							list3.add(dummy);
							quantityCount += dummy.getQuantity();
						}
					}
				}
				product.setCollectionDetailList(list1);
				product.setFeedbackList(list2);
				product.setOrderDetailList(list3);
				product.setProductList(list4);

				// tinh elo
				int elo = 0;

				// var
				if (product.getProductList().size() > 5) {
					elo += 2;
				} else if (product.getProductList().size() > 3) {
					elo += 1;
				}

				// feeback quantity
				if (product.getFeedbackList().size() > 0) {
					if (product.getOrderDetailList().size() / product.getFeedbackList().size() < 2) {
						elo += 2;
					} else if (product.getOrderDetailList().size() / product.getFeedbackList().size() < 4) {
						elo += 1;
					}
				}

				// collection
				if (product.getCollectionDetailList().size() > 0)
					elo += 3;

				// order quantity
				if (quantityCount > 5000)
					elo += 15;
				else if (quantityCount > 1000)
					elo += 3;

				// new product
				if (product.getStatus().equalsIgnoreCase("New"))
					elo += 3;
				// stars
				if (product.getStars() > 4 && product.getFeedbackList().size() > 10)
					elo += 10;
				else if (product.getStars() > 3 && product.getFeedbackList().size() > 10)
					elo += 7;

				// so sanh elo
				if (elo > elo6)
					if (elo <= elo5) {
						elo6 = elo;
						top6 = product;
					} else if (elo <= elo4) {
						elo6 = elo5;
						top6 = top5;
						elo5 = elo;
						top5 = product;
					} else if (elo <= elo3) {
						elo6 = elo5;
						top6 = top5;
						elo5 = elo4;
						top5 = top4;
						elo4 = elo;
						top4 = product;
					} else if (elo <= elo2) {
						elo6 = elo5;
						top6 = top5;
						elo5 = elo4;
						top5 = top4;
						elo4 = elo3;
						top4 = top3;
						elo3 = elo;
						top3 = product;
					} else if (elo <= elo1) {
						elo6 = elo5;
						top6 = top5;
						elo5 = elo4;
						top5 = top4;
						elo4 = elo3;
						top4 = top3;
						elo3 = elo2;
						top3 = top2;
						elo2 = elo;
						top2 = product;
					} else {
						elo6 = elo5;
						top6 = top5;
						elo5 = elo4;
						top5 = top4;
						elo4 = elo3;
						top4 = top3;
						elo3 = elo2;
						top3 = top2;
						elo2 = elo1;
						top2 = top1;
						elo1 = elo;
						top1 = product;
					}
				if (elo1 == 0)
					top1 = product;
				else if (elo2 == 0)
					top2 = product;
				else if (elo3 == 0)
					top3 = product;
				else if (elo4 == 0)
					top4 = product;
				else if (elo5 == 0)
					top5 = product;
				else if (elo6 == 0)
					top6 = product;
			}
		}
		// lay top 6 ra
		listTopProduct.add(top1);
		listTopProduct.add(top2);
		listTopProduct.add(top3);
		listTopProduct.add(top4);
		listTopProduct.add(top5);
		listTopProduct.add(top6);
		return listTopProduct;

		/*
		 * try { listProduct = pf.findAll(); for (Product product : listProduct) { int
		 * prior = 0; // do uu tien = status(5d) + stars(5) = 10 if
		 * ((product.getStatus().trim()).equalsIgnoreCase("New")) { prior += 5; } prior
		 * += product.getStars().intValue(); if (prior >= 5) { priorMap.put(prior,
		 * product); } } Map<Integer, Product> sortPriorMap = new TreeMap<Integer,
		 * Product>(Collections.reverseOrder()); sortPriorMap.putAll(priorMap);
		 * listTrendingProduct = new ArrayList<>(sortPriorMap.values()); } catch
		 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

	}

	@Override
	public List<Collection> getTopCollection() throws Exception {
		List<Collection> listC = new ArrayList<>();
		List<Brand> listAllBrand = getBrandList();
		List<Collection> listAllCl = new ArrayList<>();

		Collection top1 = new Collection();
		top1.setBrandId(new Brand(0));
		Collection top2 = new Collection();
		top2.setBrandId(new Brand(0));
		Collection top3 = new Collection();
		top3.setBrandId(new Brand(0));
		Collection top4 = new Collection();
		top4.setBrandId(new Brand(0));
		Collection top5 = new Collection();
		top5.setBrandId(new Brand(0));

		int elo1 = 0, elo2 = 0, elo3 = 0, elo4 = 0, elo5 = 0;

		for (Brand brand : listAllBrand) {
			listAllCl.addAll(brand.getCollectionList());
		}
		for (Collection cl : listAllCl) {
			int elo = 0;
			List<Product> listProduct = getListBrandProduct(cl.getBrandId().getId());
			if (listProduct.size() > 5) {
				elo += 2;
			} else if (listProduct.size() > 3)
				elo += 1;

			// product stars (nhieu hon 7 san pham moi duoc)
			if (listProduct.size() > 7) {
				double averageStars = 0;
				for (Product product : listProduct) {
					averageStars += product.getStars();
				}
				if (averageStars / (double) listProduct.size() > 4) {
					elo = (int) (5 * averageStars);
				} else if (averageStars / (double) listProduct.size() > 3) {
					elo = (int) (3.5 * averageStars);
				} else
					elo = (int) (2 * averageStars);
			}

			/*
			 * //stars if (cl.getBrandId().getStars>4) elo+=8; else if
			 * (cl.getBrandId().getStars>3) elo+=3;
			 */

			// so sanh elo
			if (elo > elo5)
				if (elo <= elo4) {
					elo5 = elo;
					top5 = cl;
				} else if (elo <= elo3) {
					elo5 = elo4;
					top5 = top4;
					elo4 = elo;
					top4 = cl;
				} else if (elo <= elo2) {
					elo5 = elo4;
					top5 = top4;
					elo4 = elo3;
					top4 = top3;
					elo3 = elo;
					top3 = cl;
				} else if (elo <= elo1) {
					elo5 = elo4;
					top5 = top4;
					elo4 = elo3;
					top4 = top3;
					elo3 = elo2;
					top3 = top2;
					elo2 = elo;
					top2 = cl;
				} else {
					elo5 = elo4;
					top5 = top4;
					elo4 = elo3;
					top4 = top3;
					elo3 = elo2;
					top3 = top2;
					elo2 = elo1;
					top2 = top1;
					elo1 = elo;
					top1 = cl;
				}
			if (elo1 == 0)
				top1 = cl;
			else if (elo2 == 0)
				top2 = cl;
			else if (elo3 == 0)
				top3 = cl;
			else if (elo4 == 0)
				top4 = cl;
			else if (elo5 == 0)
				top5 = cl;
		}
		// lay top 5 ra
		listC.add(top1);
		listC.add(top2);
		listC.add(top3);
		listC.add(top4);
		listC.add(top5);

		return listC;
	}

	@Override
	public List<Brand> getBrandList() throws Exception {

		List<Brand> listAllBrand = new ArrayList<>();
		List<Brand> listBr = bf.findAll();

		for (Brand brand : listBr) {
			// store
			List<Store> listStore = new ArrayList<>();
			List<Store> dummy = new ArrayList<>();
			dummy = sf.findAll();
			for (Store store : dummy) {
				if (store.getBrandId().getId() == brand.getId()) {
					listStore.add(store);
				}
			}

			// collection
			List<Collection> listCollection = new ArrayList<>();
			List<Collection> dummy2 = new ArrayList<>();
			dummy2 = clf.findAll();
			for (Collection cl : dummy2) {
				if (cl.getBrandId().getId() == brand.getId()) {
					listCollection.add(cl);
				}
			}

			brand.setCollectionList(listCollection);
			brand.setStoreList(listStore);
			listAllBrand.add(brand);
		}

		return listAllBrand;
	}

	public List<Product> getListProductByStatus(String status) throws Exception {

		List<Product> listAllProduct = new ArrayList<>();
		List<Product> listBrandProduct = new ArrayList<>();
		List<CollectionDetail> listC = new ArrayList<>();
		List<Feedback> listF = new ArrayList<>();
		List<OrderDetail> listOD = new ArrayList<>();

		listAllProduct = pf.findAll();
		listC = cf.findAll();
		listF = ff.findAll();
		listOD = of.findAll();

		for (Product product : listAllProduct) {
			if (status.equalsIgnoreCase(product.getStatus())) {
				// kiem tra co phai master khong
				if (product.getIsMaster() == true) {
					List<CollectionDetail> list1 = new ArrayList<>();
					List<Feedback> list2 = new ArrayList<>();
					List<OrderDetail> list3 = new ArrayList<>();
					List<Product> list4 = new ArrayList<>();

					// lay variants
					for (Product dummy : listAllProduct) {
						if (product.getId() == dummy.getParentId().getId()) {
							list4.add(dummy);
						}
					}
					for (CollectionDetail dummy : listC) {
						if (product.getId() == dummy.getProduct().getId()) {
							list1.add(dummy);
						}
					}
					for (Feedback dummy : listF) {
						// master
						if (product.getId() == dummy.getProductId().getId()) {
							list2.add(dummy);
						}
						// var
						for (Product var : list4) {
							if (var.getId() == dummy.getProductId().getId()) {
								list2.add(dummy);
							}
						}
					}
					for (OrderDetail dummy : listOD) {
						// master
						if (product.getId() == dummy.getProduct().getId()) {
							list3.add(dummy);
						}
						// var
						for (Product var : list4) {
							if (var.getId() == dummy.getProduct().getId()) {
								list3.add(dummy);
							}
						}
						product.setCollectionDetailList(list1);
						product.setFeedbackList(list2);
						product.setOrderDetailList(list3);
						product.setProductList(list4);
					}
					listBrandProduct.add(product);
				}
			}
		}
		return listBrandProduct;
	}

	public List<Product> getListBrandProduct(int brandid) throws Exception {
		List<Product> listAllProduct = new ArrayList<>();
		List<Product> listBrandProduct = new ArrayList<>();
		List<CollectionDetail> listC = new ArrayList<>();
		List<Feedback> listF = new ArrayList<>();
		List<OrderDetail> listOD = new ArrayList<>();

		listAllProduct = pf.findAll();
		listC = cf.findAll();
		listF = ff.findAll();
		listOD = of.findAll();

		for (Product product : listAllProduct) {
			if (product.getBrandId().getId() == brandid) {
				// kiem tra co phai master khong
				if (product.getIsMaster() == true) {
					List<CollectionDetail> list1 = new ArrayList<>();
					List<Feedback> list2 = new ArrayList<>();
					List<OrderDetail> list3 = new ArrayList<>();
					List<Product> list4 = new ArrayList<>();

					// lay variants
					for (Product dummy : listAllProduct) {
						if (product.getId() == dummy.getParentId().getId()) {
							list4.add(dummy);
						}
					}
					for (CollectionDetail dummy : listC) {
						if (product.getId() == dummy.getProduct().getId()) {
							list1.add(dummy);
						}
					}
					for (Feedback dummy : listF) {
						// master
						if (product.getId() == dummy.getProductId().getId()) {
							list2.add(dummy);
						}
						// var
						for (Product var : list4) {
							if (var.getId() == dummy.getProductId().getId()) {
								list2.add(dummy);
							}
						}
					}
					for (OrderDetail dummy : listOD) {
						// master
						if (product.getId() == dummy.getProduct().getId()) {
							list3.add(dummy);
						}
						// var
						for (Product var : list4) {
							if (var.getId() == dummy.getProduct().getId()) {
								list3.add(dummy);
							}
						}
						product.setCollectionDetailList(list1);
						product.setFeedbackList(list2);
						product.setOrderDetailList(list3);
						product.setProductList(list4);
					}
					listBrandProduct.add(product);
				}
			}
		}
		return listBrandProduct;
	}

	public List<Product> getListProduct() throws Exception {
		List<Product> listAll = pf.findAll();
		List<Product> listProduct = new ArrayList<>();
		for (Product product : listAll) {
			if (product.getIsMaster() == true)
				listProduct.add(product);
		}
		return listProduct;
	}

	public List<Product> getListProductByCate(int cateid) throws Exception {
		List<Product> listAllProduct = getListProduct();
		List<Product> listProduct = new ArrayList<>();

		for (Product product : listAllProduct) {
			List<Category> listCate = new ArrayList<>();
			if (product.getCateId().getId() == cateid)
				listProduct.add(product);
		}
		return listProduct;
	}

	public Brand getBrand(int brandid) throws SQLException {
		Brand brand = bf.find(brandid);
		return brand;
	}

	public Category getCategory(int cateid) throws SQLException {
		Category cate = ctf.find(cateid);
		return cate;
	}
	
	public List<Category> getListCate () throws SQLException{
		List<Category> list = ctf.findAll();
		return list;
	}

	public Cart addToCart(int productid, int quantity, Cart cart) throws SQLException {
		if (cart == null) {
			cart = new Cart();
		}
		cart.add(productid, quantity);
		return cart;
	}

}
