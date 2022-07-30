<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="container-fluid dashboard-content">
	<style>
td {
	padding: 3px !important;
	text-align: center;
}

th {
	text-align: center;
}
</style>
	<div class="row">
		<div class="col-12">
			<div class="page-header">
				<h2 class="pageheader-title">E-commerce Product Management</h2>
				<p class="pageheader-text">Manager Your Product.</p>
				<div class="page-breadcrumb">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#"
								class="breadcrumb-link">E-coommerce</a></li>
							<li class="breadcrumb-item active" aria-current="page">E-Commerce
								Product Management</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<div class="col-12">
		<div class="card">
			<div class=" card-header">
				<h5 class="">Product Management</h5>
				<div class="row">
					<form action="/localbrand/admin/product/search.do" class="col-4">
						<div class="input-group">
							<input type="text" name="searchByNameProduct" class="form-control"
								placeholder="Search by name">
							<div class="input-group-append">
								<button class="input-group-text bg-transparent text-primary">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</form>
					<div class="col-3">
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target=".create-product-modal">
							<i class="fas fa-plus-square"></i>
						</button>
						<button class="btn btn-primary" type="button"
							data-toggle="collapse" data-target=".multi-collapse"
							aria-expanded="false"
							aria-controls="multiCollapseExample1 multiCollapseExample2">See
							other</button>
					</div>

					<div class="dropdown float-right">
						<button class="btn bg-white dropdown-toggle " type="button"
							id="triggerId" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Sort by</button>
						<div class="dropdown-menu dropdown-menu-right "
							aria-labelledby="triggerId">
							<a class="dropdown-item"
								href="/localbrand/admin/product/sort.do?op=numberOrder">Number
								orders (Tong so don dat)</a> <a class="dropdown-item"
								href="/localbrand/admin/product/sort.do?op=turnover">Turnover
								(Tong don thanh cong)</a> <a class="dropdown-item"
								href="/localbrand/admin/product/sort.do?op=proceeds">Proceeds
								(Tong tien ban duoc)</a>
						</div>
					</div>
				</div>
			</div>

			<div class="card-body p-0">
				<div class="table-responsive">

					<div class="collapse multi-collapse show">
						<table class="table ">
							<thead class="bg-light ">
								<tr class="border-0 ">
									<th class="border-0 ">#</th>
									<th class="border-0 ">Product ID</th>
									<th class="border-0 ">Product Image</th>
									<th class="border-0 ">Product Name</th>
									<th class="border-0 ">Discount</th>
									<th class="border-0 ">Price</th>
									<th class="border-0 h-50">Action</th>
								</tr>
							</thead>
							<tbody>
								<!-- vong for order detail-->
								<c:set var="i" scope="page" value="${0}" />
								<c:forEach var="product" items="${listProduct}" varStatus="loop">
									<c:if test="${product.isMaster}">
										<tr>
											<td>${i=i+1}</td>
											<td>${product.id}</td>
											<td><img style="height: 150px; width: 120px"
												src="${product.img}" alt="img"></td>
											<td>${product.name}</td>
											<td><fmt:formatNumber value='${product.discount}'
													type="percent" /></td>


											<td><fmt:setLocale value="vi_VN" /> <fmt:formatNumber
													value='${product.price}' type="currency" /></td>
											<td>
												<div class="dropdown">
													<button class="btn bg-white " type="button"
														data-toggle="dropdown" aria-haspopup="true"
														aria-expanded="false">...</button>
													<div class="dropdown-menu dropdown-menu-right ">
														<a class="dropdown-item" href="">Edit Product</a> 
														<a class="dropdown-item" onclick="return confirm('Are you sure you want to continue')" href="/localbrand/admin/product/delete.do?productId=${product.id}">Delete Product</a>
													</div>
												</div> <!-- <a href="# " class="btn btn-outline-light " data-toggle="modal " data-target=".orderid1 ">View Details</a> -->
											</td>
										</tr>
									</c:if>
								</c:forEach>
								<!-- vong for order detail-->
								<!-- order detail end -->
							</tbody>
						</table>
					</div>
					<div class="collapse multi-collapse ">
						<table class="table ">
							<thead class="bg-light ">
								<tr class="border-0 ">
									<th class="border-0 ">#</th>
									<th class="border-0 ">Product ID</th>
									<th class="border-0 ">Product Name</th>
									<th class="border-0 ">Proceeds</th>
									<th class="border-0 ">Number Ordered</th>
									<th class="border-0 ">Number Delivered</th>
									<th class="border-0 ">Number Canceled</th>
								</tr>
							</thead>
							<tbody>
								<!-- vong for order detail-->
								<c:forEach var="product" items="${listProduct}" varStatus="loop">
									<tr>
										<td>${loop.count}</td>
										<td>${product.id}</td>
										<td>${product.name}</td>
										<td><fmt:setLocale value="vi_VN" />
											<fmt:formatNumber value='${product.proceeds}' type="currency" /></td>
										<td>${product.numberOrdered}</td>
										<td>${product.numberDelivered}</td>
										<td>${product.numberCancel}</td>

										
									</tr>
								</c:forEach>
								<!-- vong for order detail-->

								<!-- order detail end -->

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal Order Detail -->

	<div class="modal fade create-product-modal" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="card">
					<h5 class="card-header">Create Product</h5>
					<div class="card-body">
						<form method="get" action="/localbrand/admin/product/create.do">
							<div class="form-group">
								<label class="col-form-label">Product Name</label> <input
									name="txtName1" type="text" class="form-control">
							</div>

							<div class="form-row">
								<div class="col-md-3 mb-3">
									<label>Price</label> <input name="txtPrice1" id="product-price"
										type="number" class="form-control" placeholder="Numbers">
								</div>
								<div class="col-md-6 mb-6">
									<label>Category</label> <select name="txtCategory"
										class="form-control">
										<c:forEach var="option" items="${listCate}">
											<option value="${option.id}">${option.name}</option>
										</c:forEach>

									</select>
								</div>
								<div class="col-md-3 mb-3">
									<label>Status</label> <select name="txtStatus"
										class="form-control">
										<c:forEach var="option" items="${listStatus}">
											<option>${option}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group mb-3">
								<label class="col-form-label">Image</label> <input
									name="txtImage1" required type="text" class="form-control">
							</div>
							<div class="form-row">
								<div class="col-md-6 mb-3">
									<label>Size</label> <select name="txtSize1"
										class="form-control">
										<option>XS</option>
										<option>S</option>
										<option>M</option>
										<option>L</option>
										<option>XL</option>
										<option>XXL</option>
									</select>
								</div>
								<div class="col-md-6 mb-3">
									<label>Color</label> <input name="txtColor1" type="text"
										class="form-control" placeholder="State" required>
								</div>
							</div>
							<div class="form-group">
								<label>Description</label>
								<textarea name="txtDescription" class="form-control" rows="3"></textarea>
							</div>

							<div id="dependence-product"></div>

							<div class="row">
								<div class="col-sm-6 pb-2 pb-sm-4 pb-lg-0 pr-0">
									<button type="button" onclick="return AddDependence();"
										class="btn btn-space btn-primary">More Dependence</button>
									<button type='button' onclick='return RemoveDependence();'
										id="controll-button" class='btn btn-space btn-secondary'
										hidden="true">Remove Dependence</button>
								</div>
								<div class="col-sm-6 pl-0">
									<p class="text-right">
										<button type="button" class="btn btn-space "
											data-dismiss="modal" aria-label="Close">Cancel</button>
										<button type="submit" class="btn btn-space btn-primary">Submit</button>
									</p>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var count = 1;
		var RemoveDependence = function() {
			document.getElementById("dependence" + count + "").remove();
			count--;
			if (count == 1)
				document.getElementById("controll-button").setAttribute(
						'hidden', '');
			return false;
		};
		var AddDependence = function() {
			count++;
			var price = document.getElementById("product-price").value;
			document.getElementById("dependence-product").innerHTML += "<div id='dependence" + count + "'>"
					+ "<h5 class='card-header'>Create Dependence "
					+ (count - 1)
					+ "</h5>"
					+ "<div class='card-body'>"
					+ "<div class='form-row'>"
					+ "<div class='col-md-3 mb-3'>"
					+ "<label>Price</label>"
					+ "<input type='number' name='txtPrice" + count + "' class='form-control' value='" + price + "' placeholder='Price'>"
					/* while (request.getParameter("txtPrice" + (++i) )!= null) */

					+ "</div>"
					+ "<div class='col-md-6 mb-3'>"
					+ "<label >Size</label>"
					+ "<select name='txtSize" + count + "' class='form-control'>"
					+ "<option>XS</option>"
					+ "<option>S</option>"
					+ "<option>M</option>"
					+ "<option>L</option>"
					+ "<option>XL</option>"
					+ "<option>XXL</option>"
					+ "</select>"
					+ "</div>"
					+ "<div class='col-md-3 mb-3'>"
					+ "<label for='validationTooltip04'>Color</label>"
					+ "<input type='text' name='txtColor" + count + "' class='form-control' placeholder='Red...' required>"
					+ "</div>"
					+ "</div>"
					+ "<div class='form-group mb-3'>"
					+ "<label class='col-form-label'>Image</label> "
					+ "<input type='text' name='txtImage" + count + "' class='form-control' id='customFile' >"
					+ "</div></div></div>";

			document.getElementById("controll-button")
					.removeAttribute('hidden');
			return false;
		}
	</script>
</div>