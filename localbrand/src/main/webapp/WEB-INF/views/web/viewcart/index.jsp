<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<body>

	<!-- Page Header Start -->
	<div class="container-fluid bg-secondary mb-5">
		<div
			class="d-flex flex-column align-items-center justify-content-center"
			style="min-height: 300px">
			<h1 class="font-weight-semi-bold text-uppercase mb-3">Shopping
				Cart</h1>
		</div>
	</div>
	<!-- Page Header End -->


	<!-- Cart Start -->
	<div class="container-fluid pt-5">
		<div class="row px-xl-5">
			<div class="col-lg-8 table-responsive mb-5">
				<table class="table table-bordered text-center mb-0">
					<thead class="bg-secondary text-dark">
						<tr>
							<th>Product Name</th>
							<th>Price Per Item</th>
							<th>Quantity</th>
							<th>Total</th>
							<th>Update</th>
							<th>Remove</th>
						</tr>
					</thead>
					<tbody class="align-middle">
						<c:forEach var="key"
							items="${sessionScope.cart.getMap().keySet()}">
							<c:if test="${cart.getMap().get(key).getQuantity()>0}">
								<tr>
									<form action="/localbrand/web/cart/updatequantity.do">
									<td class="align-middle">${cart.getMap().get(key).getProduct().getName()}</td>
									<td class="align-middle"><fmt:setLocale value="vi_VN" />
										<fmt:formatNumber
											value="${cart.getMap().get(key).getProduct().getPrice()*(1-cart.getMap().get(key).getProduct().getDiscount())}"
											type="currency" /></td>
									<td class="align-middle">
										<div class="input-group quantity mx-auto" style="width: 80px;">

											<input type="number"
												class="form-control bg-secondary text-center input-number"
												value="${cart.getMap().get(key).getQuantity()}"
												name="updquantity">

										</div>
									</td>
									<td class="align-middle"><fmt:setLocale value="vi_VN" />
										<fmt:formatNumber
											value="${cart.getMap().get(key).getProduct().getPrice()*(1-cart.getMap().get(key).getProduct().getDiscount())
														*cart.getMap().get(key).getQuantity()}"
											type="currency" /></td>
									<td class="align-middle"><input type="hidden"
										name="productid"
										value="${cart.getMap().get(key).getProduct().getId()}">
										<div class="input-group-append -flex justify-content-center">
											<button class="btn btn-primary ">Save</button>
										</div>
										</form>
										<form action="/localbrand/web/cart/updatequantity.do"></td>
									<td class="align-middle"><input type="hidden"
										name="productid"
										value="${cart.getMap().get(key).getProduct().getId()}">
										<input type="hidden" name="updquantity" value="0">
										<button class="btn btn-sm btn-primary">
											<i class="fa fa-times"></i>
										</button></td>
									</form>
								</tr>
							</c:if>
						</c:forEach>


						<%-- 
						<tr>
							<td colspan="5">
								<!-- <div> -->
								<div class="input-group-append -flex justify-content-center">
									<button class="btn btn-primary ">Save change</button>
								</div>
							</td>
						</tr>
						--%>
					</tbody>
				</table>
			</div>
			<div class="col-lg-4 ">
				<%--
                <form class="mb-5 " action=" ">
                    <div class="input-group ">
                        <input type="text " class="form-control p-4 " placeholder="Coupon Code ">
                        <div class="input-group-append ">
                            <button class="btn btn-primary ">Apply Coupon</button>
                        </div>
                    </div>
                </form>
                --%>
				<div class="card border-secondary mb-5 ">
					<div class="card-header bg-secondary border-0 ">
						<h4 class="font-weight-semi-bold m-0 ">Cart Summary</h4>
					</div>
					<div class="card-body">
						<div class="d-flex justify-content-between  mb-3 pt-1">
							<h6 class="font-weight-medium">Total Cost</h6>
							<h6 class="font-weight-medium">
								<fmt:setLocale value="vi_VN" />
								<fmt:formatNumber value="${requestScope.totalCart}"
									type="currency" />
							</h6>
						</div>
						<div class="d-flex justify-content-between">
							<h6 class="font-weight-medium">Shipping Fee</h6>
							<h6 class="font-weight-medium">
								<fmt:setLocale value="vi_VN" />
								<fmt:formatNumber value="${requestScope.feeShip}"
									type="currency" />
							</h6>
						</div>
						<div class="d-flex justify-content-between">
							<h6 class="font-weight-medium">Membership Discount
								(${sessionScope.user.rankId.rank})</h6>
							<h6 class="font-weight-medium">
								<c:if test="${not empty user}">
									<fmt:formatNumber type="percent"
										value="${sessionScope.user.rankId.discount}" />
								</c:if>
								<c:if test="${empty user}">
									<fmt:formatNumber type="percent" value="0" />
								</c:if>
							</h6>
						</div>
						<div class="d-flex justify-content-between">
							<h6 class="font-weight-medium"></h6>
							<h6 class="font-weight-medium">
								<fmt:setLocale value="vi_VN" />
								<fmt:formatNumber
									value="-${requestScope.totalCart + requestScope.feeShip - requestScope.totalPrice}"
									type="currency" />
							</h6>
						</div>
					</div>
					<div class="card-footer border-secondary bg-transparent ">
						<div class="d-flex justify-content-between mt-2 ">
							<h5 class="font-weight-bold ">Total</h5>
							<h5 class="font-weight-bold ">
								<fmt:setLocale value="vi_VN" />
								<fmt:formatNumber value="${requestScope.totalPrice}"
									type="currency" />
							</h5>
						</div>
						<form action="/localbrand/web/cart/viewcheckout.do">
							<button class="btn btn-block btn-primary my-3 py-3 ">Proceed
								To Checkout</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Cart End -->



	<!-- Back to Top -->
	<a href="# " class="btn btn-primary back-to-top "><i
		class="fa fa-angle-double-up "></i></a>
	<script>
		function buttonClickDecrease() {
			document.getElementById('quantity').value--;
		}
		function buttonClickIncrease() {
			document.getElementById('quantity').value++;
		}
	</script>
</body>