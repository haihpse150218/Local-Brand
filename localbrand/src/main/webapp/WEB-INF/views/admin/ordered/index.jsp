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
							<li class="breadcrumb-item active" aria-current="page">E-Commerce
								Ordered</li>
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
					<form action="/localbrand/admin/ordered/search.do" method="get" class="col-4">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Search for orders">
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
								aria-expanded="false">Fillter by</button>
							<div class="dropdown-menu dropdown-menu-right "
								aria-labelledby="triggerId">
								<a class="dropdown-item" href="#">InTransit</a> <a
									class="dropdown-item" href="#">Delivered</a> <a
									class="dropdown-item" href="#">Canceled</a>
							</div>
						</div>
					</div>
					<div class="dropdown float-right">
						<button class="btn bg-white dropdown-toggle " type="button"
							id="triggerId" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Sort by</button>
						<div class="dropdown-menu dropdown-menu-right "
							aria-labelledby="triggerId">
							<a class="dropdown-item" href="#">Latest</a> <a
								class="dropdown-item" href="#">Status</a> <a
								class="dropdown-item" href="#">Total price</a>
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
								<th class="border-0">Total Quantity</th>
								<th class="border-0">Total Price</th>
								<th class="border-0">Order Time</th>
								<th class="border-0">Customer</th>
								<th class="border-0">Status</th>
								<th class="border-0">Action</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>id000001</td>
								<td>20</td>
								<td>$80.00</td>
								<td>27-08-2018 01:22:12</td>
								<td>Patricia J. King</td>
								<td><span class="badge-dot badge-brand mr-1"></span>InTransit
								</td>
								<td><a href="#" class="btn btn-outline-light">View
										Details</a></td>
							</tr>
							<tr>
								<td>2</td>
								<td>id000002</td>
								<td>12</td>
								<td>$180.00</td>
								<td>25-08-2018 21:12:56</td>
								<td>Rachel J. Wicker</td>
								<td><span class="badge-dot badge-success mr-1"></span>Delivered
								</td>
								<td><a href="#" class="btn btn-outline-light">View
										Details</a></td>
							</tr>
							<tr>
								<td>3</td>
								<td>id000003</td>
								<td>23</td>
								<td>$820.00</td>
								<td>24-08-2018 14:12:77</td>
								<td>Michael K. Ledford</td>
								<td><span class="badge-dot badge-success mr-1"></span>Delivered
								</td>
								<td><a href="#" class="btn btn-outline-light">View
										Details</a></td>
							</tr>
							<tr>
								<td>4</td>
								<td>id000004</td>
								<td>34</td>
								<td>$340.00</td>
								<td>23-08-2018 09:12:35</td>
								<td>Michael K. Ledford</td>
								<td><span class="badge-dot badge-success mr-1"></span>Delivered
								</td>
								<td><a href="#" class="btn btn-outline-light">View
										Details</a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


