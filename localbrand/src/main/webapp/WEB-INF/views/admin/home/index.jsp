<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="container-fluid dashboard-content ">
	<!-- ============================================================== -->
	<!-- pageheader  -->
	<!-- ============================================================== -->
	<div class="row">
		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			<div class="page-header">
				<h2 class="pageheader-title">E-commerce Dashboard Home</h2>
				<p class="pageheader-text">Home page admin.</p>
				<div class="page-breadcrumb">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#"
								class="breadcrumb-link">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">E-Commerce
								Dashboard Home</li>
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
									<tr>
										<td colspan="9"><a href="/localbrand/admin/ordered/index.do"
											class="btn btn-outline-light float-right">Show All</a></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>


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
							<h1 class="mb-1">$12099</h1>
						</div>
						<div
							class="metric-label d-inline-block float-right text-success font-weight-bold">
							<span
								class="icon-circle-small icon-box-xs text-success bg-success-light"><i
								class="fa fa-fw fa-arrow-up"></i></span><span class="ml-1">5.86%</span>
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
							<h1 class="mb-1">1245</h1>
						</div>
						<div
							class="metric-label d-inline-block float-right text-success font-weight-bold">
							<span
								class="icon-circle-small icon-box-xs text-success bg-success-light"><i
								class="fa fa-fw fa-arrow-up"></i></span><span class="ml-1">10%</span>
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
							<h1 class="mb-1">1340</h1>
						</div>
						<div
							class="metric-label d-inline-block float-right text-danger font-weight-bold">
							<span
								class="icon-circle-small icon-box-xs text-danger bg-danger-light bg-danger-light "><i
								class="fa fa-fw fa-arrow-down"></i></span><span class="ml-1">4%</span>
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
<script>
        var chart = c3.generate({
            bindto: "#c3chart_category",
            data: {
                columns: [
                    ['Men', 100],
                    ['Women', 80],
                    ['Accessories', 50],
                    ['Children', 40],
                    ['Apperal', 20],

                ],
                type: 'donut',

                onclick: function(d, i) {
                    console.log("onclick", d, i);
                },
                onmouseover: function(d, i) {
                    console.log("onmouseover", d, i);
                },
                onmouseout: function(d, i) {
                    console.log("onmouseout", d, i);
                },

                colors: {
                    Men: '#5969ff',
                    Women: '#ff407b',
                    Accessories: '#25d5f2',
                    Children: '#ffc750',
                    Apperal: '#2ec551',
                }
            },
            donut: {
                label: {
                    show: false
                }
            },
        });
        Morris.Area({
            element: 'morris_totalrevenue',
            behaveLikeLine: true,
            data: [{
                x: '2018 Q1',
                y: 0,
            }, {
                x: '2018 Q2',
                y: 7500,
            }, {
                x: '2019 Q3',
                y: 15000,
            }, {
                x: '2019 Q4',
                y: 22500,
            }, {
                x: '2020 Q5',
                y: 30000,
            }, {
                x: '2020 Q6',
                y: 40000,
            }],
            xkey: 'x',
            ykeys: ['y'],
            labels: ['Y'],
            lineColors: ['#5969ff'],
            resize: true

        });
    </script>
