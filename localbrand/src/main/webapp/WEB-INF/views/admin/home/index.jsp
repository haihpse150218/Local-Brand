<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="dashboard-wrapper">
	<div class="dashboard-ecommerce">
		<div class="container-fluid dashboard-content ">
			<!-- ============================================================== -->
			<!-- pageheader  -->
			<!-- ============================================================== -->
			<div class="row">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="page-header">
						<h2 class="pageheader-title">E-commerce Dashboard Template</h2>
						<p class="pageheader-text">Nulla euismod urna eros, sit amet
							scelerisque torton lectus vel mauris facilisis faucibus at enim
							quis massa lobortis rutrum.</p>
						<div class="page-breadcrumb">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="#"
										class="breadcrumb-link">Dashboard</a></li>
									<li class="breadcrumb-item active" aria-current="page">E-Commerce
										Dashboard Template</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
			</div>
			<!-- ============================================================== -->
			<!-- end pageheader  -->
			<!-- ============================================================== -->
			<div class="ecommerce-widget">


				<div class="row">
					<!-- ============================================================== -->

					<!-- ============================================================== -->

					<!-- recent orders  -->
					<!-- ============================================================== -->
					<div class="col-12">
						<div class="card">
							<h5 class="card-header">Recent Orders</h5>
							<div class="card-body p-0">
								<div class="table-responsive">
									<table class="table">
										<thead class="bg-light">
											<tr class="border-0">
												<th class="border-0">#</th>
												<th class="border-0">Order Id</th>
												<th class="border-0">Quantity</th>
												<th class="border-0">Total</th>
												<th class="border-0">Order Time</th>
												<th class="border-0">Customer</th>
												<th class="border-0">Status</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="order" items="${listOrder}" varStatus="loop">
												<tr>
													<td>${loop.count}</td>
													<td>${order.id}</td>
													<td>${order.quantity}</td>
													<td>${order.total}</td>
													<td><fmt:formatDate value="${order.orderDate}"
															pattern="dd-MM-yyyy" /></td>
													<td>${order.customerId.name}</td>
													<td>${order.status}</td>
												</tr>
												<tr>
											</c:forEach>
											<tr>
												<td colspan="9"><a href="#"
													class="btn btn-outline-light float-right">View Details</a></td>
											</tr>
											<c:if test="">

											</c:if>


										</tbody>
									</table>
									<table class="table">
										<thead class="bg-light">
											<tr class="border-0">
												<th class="border-0">#</th>
												<th class="border-0">Order Id</th>
												<th class="border-0">Product Id</th>
												<th class="border-0">Product Name</th>
												<th class="border-0">Quantity</th>
												<th class="border-0">Discount</th>
												<th class="border-0">Price</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach var="order" items="${listOrder}" varStatus="loop">
												<c:forEach var="orderDetail"
													items="${order.listOrderDetail}" varStatus="loop">
													<tr>
														<td>${loop.count}</td>
														<td>${order.id}</td>
														<td>${orderDetail.product.id}</td>
														<td>${orderDetail.product.name}</td>
														<td>${orderDetail.quantity}</td>

														<td><fmt:formatNumber type="percent"
																value="${orderDetail.discount}" /></td>
														<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
																value="${orderDetail.price}" type="currency" /></td>
													</tr>

												</c:forEach>
											</c:forEach>
											<tr>
												<td colspan="9"><a href="#"
													class="btn btn-outline-light float-right">Off Details</a></td>
											</tr>
											<c:if test="">

											</c:if>


										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<!-- ============================================================== -->
					<!-- end recent orders  -->


					<!-- ============================================================== -->
					<!-- ============================================================== -->



					<!-- ============================================================== -->
				</div>

				<div class="row">
					<!-- ============================================================== -->
					<!-- sales  -->
					<!-- ============================================================== -->
					<div class="col-xl-4 col-lg-4 col-md-12 col-sm-12 col-12">
						<div class="card border-3 border-top border-top-primary">
							<div class="card-body">
								<h5 class="text-muted">Sales</h5>
								<div class="metric-value d-inline-block">
									<h1 class="mb-1">
										<fmt:setLocale value="en_US" />
										<fmt:formatNumber value="${salesThisWeek}" type="currency" />
									</h1>
								</div>
								<div
									class="metric-label d-inline-block float-right ${growth>=0?'text-success':'text-danger'} font-weight-bold">
									<span
										class="icon-circle-small icon-box-xs ${growth>=0?'text-success bg-success-light':'text-danger bg-danger-light'}"><i
										class="fa fa-fw ${growth>=0?'fa-arrow-up':'fa-arrow-down'} "></i></span><span class="ml-1">${growth}%</span>
								</div>
							</div>
						</div>
					</div>
					<!-- ============================================================== -->
					<!-- end sales  -->
					<!-- ============================================================== -->
					<!-- ============================================================== -->
					<!-- new customer  -->
					<!-- ============================================================== -->
					<div class="col-xl-4 col-lg-4 col-md-12 col-sm-12 col-12">
						<div class="card border-3 border-top border-top-primary">
							<div class="card-body">
								<h5 class="text-muted">New Customer</h5>
								<div class="metric-value d-inline-block">
									<h1 class="mb-1">${newMembers}</h1>
								</div>
								<div
									class="metric-label d-inline-block float-right ${growthNewMembers >=0?'text-success':'text-danger'} font-weight-bold">
									<span
										class="icon-circle-small icon-box-xs ${growthNewMembers>=0?'text-success bg-success-light':'text-danger bg-danger-light'}"><i
										class="fa fa-fw ${growthNewMembers>=0?'fa-arrow-up':'fa-arrow-down'}"></i></span><span class="ml-1">${growthNewMembers}%</span>
								</div>
							</div>
						</div>
					</div>
					<!-- ============================================================== -->
					<!-- end new customer  -->
					<!-- ============================================================== -->
					<!-- ============================================================== -->

					<!-- ============================================================== -->
					<!-- ============================================================== -->
					<!-- total orders  -->
					<!-- ============================================================== -->
					<div class="col-xl-4 col-lg-4 col-md-12 col-sm-12 col-12">
						<div class="card border-3 border-top border-top-primary">
							<div class="card-body">
								<h5 class="text-muted">Total Orders</h5>
								<div class="metric-value d-inline-block">
									<h1 class="mb-1">${totalOrderThisWeek}</h1>
								</div>
								<div
									class="metric-label d-inline-block float-right ${growthOrder>=0?'text-success':'text-danger'}  font-weight-bold">
									<span
										class="icon-circle-small icon-box-xs ${growthOrder>=0?'text-success bg-success-light':'text-danger bg-danger-light'}  "><i
										class="fa fa-fw ${growthOrder>=0?'fa-arrow-up':'fa-arrow-down'} "></i></span><span class="ml-1">${growthOrder}%</span>
								</div>
							</div>
						</div>
					</div>
					<!-- ============================================================== -->
					<!-- end total orders  -->
					<!-- ============================================================== -->
				</div>
				<div class="row">
					<!-- ============================================================== -->
					<!-- total revenue  -->
					<!-- ============================================================== -->


					<!-- ============================================================== -->
					<!-- ============================================================== -->
					<!-- category revenue  -->
					<!-- ============================================================== -->
					<div class="col-xl-5 col-lg-5 col-md-12 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">Revenue by Category</h5>
							<div class="card-body">
								<div id="c3chart_category" style="height: 420px;"></div>
							</div>
						</div>
					</div>
					<!-- ============================================================== -->
					<!-- end category revenue  -->
					<!-- ============================================================== -->

					<div class="col-xl-7 col-lg-7 col-md-12 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">Total Revenue</h5>
							<div class="card-body">
								<div id="morris_totalrevenue"></div>
							</div>
							<div class="card-footer">
								<p class="display-7 font-weight-bold">
									<span class="text-primary d-inline-block">$26,000</span><span
										class="text-success float-right">+9.45%</span>
								</p>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- ============================================================== -->
	<!-- footer -->
	<!-- ============================================================== -->
	<div class="footer">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
					Copyright © 2018 Concept. All rights reserved. Dashboard by <a
						href="">Colorlib</a>.
				</div>
				<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
					<div class="text-md-right footer-links d-none d-sm-block">
						<a href="javascript: void(0);">About</a> <a
							href="javascript: void(0);">Support</a> <a
							href="javascript: void(0);">Contact Us</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ============================================================== -->
	<!-- end footer -->
	<!-- ============================================================== -->
</div>