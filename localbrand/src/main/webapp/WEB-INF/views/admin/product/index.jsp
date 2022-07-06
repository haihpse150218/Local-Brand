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
					<form action="" class="col-4">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Search by id">
							<div class="input-group-append">
								<button class="input-group-text bg-transparent text-primary">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</form>
					<div class="col-2">
						<button class="btn btn-primary" type="button"
							data-toggle="collapse" data-target=".multi-collapse"
							aria-expanded="false"
							aria-controls="multiCollapseExample1 multiCollapseExample2">See
							other</button>
					</div>
					<div class="col-4">
						<div class="dropdown float-right">
							<button class="btn bg-white dropdown-toggle " type="button"
								id="triggerId" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">Fillter by</button>
							<div class="dropdown-menu dropdown-menu-right "
								aria-labelledby="triggerId">
								<a class="dropdown-item" href="/localbrand/admin/product/fillter.do?op=all">All (Tat ca)</a> <a
									class="dropdown-item" href="/localbrand/admin/product/fillter.do?op=aWeek">1 Week (Tuan nay)</a>
							</div>
						</div>
					</div>
					<div class="dropdown float-right">

						<button class="btn bg-white dropdown-toggle " type="button"
							id="triggerId" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Sort by</button>
						<div class="dropdown-menu dropdown-menu-right "
							aria-labelledby="triggerId">
							<a class="dropdown-item" href="/localbrand/admin/product/sort.do?op=numberOrder">Number orders (Tong so don
								dat)</a> <a class="dropdown-item" href="/localbrand/admin/product/sort.do?op=turnover">Turnover (Tong don
								thanh cong)</a> <a class="dropdown-item" href="/localbrand/admin/product/sort.do?op=proceeds">Proceeds (Tong
								tien ban duoc)</a>
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
								<c:forEach var="product" items="${listProduct}" varStatus="loop">
									<tr>
										<td>${loop.count}</td>
										<td>${product.id}</td>
										<td><img class="img-fluid" src="${product.img}" alt="img">
										</td>
										<td>${product.name}</td>
										<td><fmt:formatNumber value='${product.discount}'
												type="percent" /></td>
										<td><fmt:formatNumber value='${product.price}'
												type="currency" /></td>

									</tr>

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
									<th class="border-0 ">Action</th>
								</tr>
							</thead>
							<tbody>
								<!-- vong for order detail-->
								<c:forEach var="product" items="${listProduct}" varStatus="loop">
									<tr>
										<td>${loop.count}</td>
										<td>${product.id}</td>
										<td>${product.name}</td>
										<td><fmt:formatNumber value='${product.proceeds}'
												type="currency" /></td>
										<td>${product.numberOrdered}</td>
										<td>${product.numberDelivered}</td>
										<td>${product.numberCancel}</td>
										<td>${product.name}</td>
										<td>
											<div class="dropdown">
												<button class="btn bg-white " type="button"
													data-toggle="dropdown" aria-haspopup="true"
													aria-expanded="false">...</button>
												<div class="dropdown-menu dropdown-menu-right ">
													<a class="dropdown-item" href="#">View Detail</a> <a
														class="dropdown-item" href="#">Edit Product</a> <a
														class="dropdown-item" href="#">Delete Product</a>
												</div>
											</div> <!-- <a href="# " class="btn btn-outline-light " data-toggle="modal " data-target=".orderid1 ">View Details</a> -->
										</td>
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
</div>