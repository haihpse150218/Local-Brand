<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="container-fluid dashboard-content ">
	<div class="row">
		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			<div class="page-header">
				<h2 class="pageheader-title">E-commerce Ordered</h2>
				<p class="pageheader-text">Management Ordered in your brand.</p>
				<div class="page-breadcrumb">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#"
								class="breadcrumb-link">E-commerce</a></li>
							<li class="breadcrumb-item active" aria-current="page">
							E-Commerce Ordered
							</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<div class="col-12">
		<div class="card">
			<div class=" card-header">
				<h5 class="">Recent Orders</h5>
				<div class="row">
					<form action="/localbrand/admin/ordered/search.do" method="get"
						class="col-4">
						<div class="input-group">
							<input type="text" name="searchCustomerName" value="${searchCustomerName}" class="form-control"
								placeholder="Search By Customer Name">
							<div class="input-group-append">
								<button class="input-group-text bg-transparent text-primary">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</form>
					<div class="col-6">
						<div class="dropdown float-right">
							<button class="btn bg-white dropdown-toggle " type="button"
								id="triggerId" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">Filter by</button>
							<div class="dropdown-menu dropdown-menu-right "
								aria-labelledby="triggerId">
								<a class="dropdown-item" href="/localbrand/admin/ordered/fillter.do?op=intransit">InTransit</a> <a
									class="dropdown-item" href="/localbrand/admin/ordered/fillter.do?op=delivered">Delivered</a> <a
									class="dropdown-item" href="/localbrand/admin/ordered/fillter.do?op=canceled">Canceled</a>
							</div>
						</div>
					</div>
					<div class="dropdown float-right">
						<button class="btn bg-white dropdown-toggle " type="button"
							id="triggerId" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Sort by</button>
						<div class="dropdown-menu dropdown-menu-right "
							aria-labelledby="triggerId">
							<a class="dropdown-item" href="/localbrand/admin/ordered/sort.do?op=latest">Latest</a> <a
								class="dropdown-item" href="/localbrand/admin/ordered/sort.do?op=status">Status</a> <a
								class="dropdown-item" href="/localbrand/admin/ordered/sort.do?op=price">Total price</a>
						</div>
					</div>
				</div>
			</div>
			<div class="card-body p-0">
				<div class="table-responsive">
					<table class="table">
						<thead class="bg-light">
							<tr class="border-0">
								<th class="border-0">#</th>
								<th class="border-0">Order ID</th>
								<th class="border-0">Total Price</th>
								<th class="border-0">Order Time</th>
								<th class="border-0">Customer Name</th>
								<th class="border-0">Customer Phone</th>
								<th class="border-0">Customer Address</th>
								<th class="border-0">Customer Email</th>
								<th class="border-0">Status</th>
								<th class="border-0">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="order" items="${listOrder}" varStatus="loop">
								<tr>
									<td>${loop.count}</td>
									<td>${order.id}</td>
									<td>
									<fmt:setLocale value="vi_VN" /> <fmt:formatNumber
													value="${order.total}" type="currency" />
									</td>
									<td><fmt:formatDate value="${order.orderDate}"
											pattern="dd-MM-yyyy" /></td>
									<td>${order.customerId.name}</td>
									<td>${order.customerId.phone}</td>
									<td>${order.customerId.address}</td>
									<td>${order.customerId.email}</td>
									<td>
										<button class="btn btn-primary dropdown-toggle" 
											type="button" id="dropdownMenuButton" 
											data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
   	 										${order.status}
  										</button>
										<span class="dropdown-menu"
											aria-labelledby="triggerId">
											<a class="dropdown-item"
												href="/localbrand/admin/ordered/update.do?orderId=${order.id}&status=Received">Received</a>
											<a class="dropdown-item"
												href="/localbrand/admin/ordered/update.do?orderId=${order.id}&status=Shipping Failed">Shipping Failed</a> 
											<a class="dropdown-item"
												href="/localbrand/admin/ordered/update.do?orderId=${order.id}&status=Shipping">Shipping</a>
											<a class="dropdown-item"
												href="/localbrand/admin/ordered/update.do?orderId=${order.id}&status=Preparing">Preparing</a>
											<a class="dropdown-item"
												href="/localbrand/admin/ordered/update.do?orderId=${order.id}&status=Cancelled">Cancelled</a>
										</span>
									</td>
									<td><a href="#" class="btn btn-outline-light"
										data-toggle="modal" data-target=".orderid${order.id}">View
											Details</a></td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<!-- popup table orderDetail  -->
			<c:forEach var="order" items="${listOrder}" varStatus="loop">
				<div class="modal fade orderid${order.id}" tabindex="-1"
					role="dialog" aria-labelledby="myLargeModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
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
									<!-- vong for order detail-->
									<c:forEach var="orderDetail" items="${order.listOrderDetail}"
										varStatus="loop">
										<tr>
											<td>${loop.count}</td>
											<td>${order.id}</td>
											<td>${orderDetail.product.id}</td>
											<td>${orderDetail.product.name}</td>
											<td>${orderDetail.quantity}</td>
											<td><fmt:formatNumber type="percent"
													value="${orderDetail.discount}" /></td>
											<td><fmt:setLocale value="vi_VN" /> <fmt:formatNumber
													value="${orderDetail.price}" type="currency" /></td>
										</tr>
									</c:forEach>
									<!-- order detail end -->
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>
</div>

