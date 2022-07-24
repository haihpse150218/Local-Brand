<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="container-fluid">
	<div class="col-12 px-xl-5">
		<div class="dropdown float-right">
			<button class="btn bg-white dropdown-toggle " type="button"
				id="triggerId" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">Filter order status by :
				${requestScope.orderstatus}</button>
			<div class="dropdown-menu dropdown-menu-right "
				aria-labelledby="triggerId">
				<a class="dropdown-item"
					href="/localbrand/web/order/index.do?orderstatus=">ALL</a> <a
					class="dropdown-item"
					href="/localbrand/web/order/index.do?orderstatus=Received">Received</a>
				<a class="dropdown-item"
					href="/localbrand/web/order/index.do?orderstatus=Shipping Failed">Shipping
					Failed</a> <a class="dropdown-item"
					href="/localbrand/web/order/index.do?orderstatus=Shipping">Shipping</a>
				<a class="dropdown-item"
					href="/localbrand/web/order/index.do?orderstatus=Preparing">Preparing</a>
				<a class="dropdown-item"
					href="/localbrand/web/order/index.do?orderstatus=Canceled">Canceled</a>
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
							<th class="border-0">Order Date</th>
							<th class="border-0">Status</th>
							<th class="border-0">Order Action</th>
							<th class="border-0">Order Details</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" items="${requestScope.LIST_ORDER}"
							varStatus="loop">
							<tr>
								<td>${loop.count}</td>
								<td>${order.id}</td>
								<td><fmt:setLocale value="vi_VN" /> <fmt:formatNumber
										value="${order.total}" type="currency" /></td>
								<td><fmt:formatDate value="${order.orderDate}"
										pattern="dd-MM-yyyy" /></td>
								<td>${order.status}</td>
								<td><c:if test="${order.status == 'Preparing'}">
										<a
											href="/localbrand/web/order/setstatusorder.do?updorderstatus=Canceled&orderid=${order.id}&orderstatus="
											class="nav-item nav-link">Cancel order</a>
									</c:if> <c:if test="${order.status == 'Shipping'}">
										<a
											href="/localbrand/web/order/setstatusorder.do?updorderstatus=Received&orderid=${order.id}&orderstatus="
											class="nav-item nav-link">I received order successfully!</a>
									</c:if> <c:if test="${order.status == 'Received'}">
									feedback trong order details
									</c:if></td>
								<td><a href="#" class="btn btn-outline-dark"
									data-toggle="modal" data-target=".orderid${order.id}">View
										Details</a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<!-- popup table orderDetail  -->
		<c:forEach var="order" items="${requestScope.LIST_ORDER}"
			varStatus="loop">
			<div class="modal fade orderid${order.id}" tabindex="-1"
				role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<table class="table">
							<thead class="bg-light">
								<tr class="border-0">
									<th class="border-0">#</th>
									<th class="border-0">Order Id</th>
									<th class="border-0">Product Name</th>
									<th class="border-0">Product Brand</th>
									<th class="border-0">Quantity</th>
									<th class="border-0">Discount</th>
									<th class="border-0">Price</th>
								</tr>
							</thead>
							<tbody>
								<!-- vong for order detail-->
								<c:forEach var="orderDetail" items="${order.orderDetailList}"
									varStatus="loop">
									<tr>
										<td>${loop.count}</td>
										<td>${order.id}</td>
										<td>${orderDetail.product.name}</td>
										<td>${orderDetail.product.brandId.name}</td>
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