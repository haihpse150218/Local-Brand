<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %> 

<!-- Shop info Start -->
<div class="container-fluid bg-secondary mb-3">
	<div
		class="d-flex flex-column align-items-center justify-content-center"
		style="background-image: url('https://mdbootstrap.com/img/Photos/Others/images/76.jpg'); min-height: 300px">
		<h1 class="font-weight-semi-bold text-uppercase mb-3">Ten Shop</h1>
		<div class="d-inline-flex">
			<p class="m-0">
				<a href="">Home</a>
			</p>
			<p class="m-0 px-2">-</p>
			<p class="m-0">Ten category</p>
		</div>
	</div>
</div>
<!-- Shop info End -->

<!-- Collection start -->
<div class="text-center ">
	<h2 class="section-title px-5">
		<span class="px-2">Hot Collections</span>
	</h2>
</div>
<div id="header-carousel" class="carousel slide" data-ride="carousel">
	<div class="carousel-inner">
		<div class="carousel-item active">
			<!-- Collection 1 Start -->
			<div class="container-fluid offer">
				<div class="row px-xl-5">
					<div class="col-md-6 pb-4">
						<div
							class="position-relative bg-secondary text-center text-md-right text-white mb-2 py-5 px-5">
							<!--img e                                 <img src="img/offer-1.png" alt=""> -->
							<div class="position-relative" style="z-index: 1;">
								<h5 class="text-uppercase text-primary mb-3">20% off the
									all order</h5>
								<h1 class="mb-4 font-weight-semi-bold">Spring Collection</h1>
								<a href="" class="btn btn-outline-primary py-md-2 px-md-3">Shop
									Now</a>
							</div>
						</div>
					</div>
					<div class="col-md-6 pb-4">
						<div
							class="position-relative bg-secondary text-center text-md-left text-white mb-2 py-5 px-5">
							<!-- 							<img src="img/offer-2.png" alt=""> -->
							<div class="position-relative" style="z-index: 1;">
								<h5 class="text-uppercase text-primary mb-3">20% off the
									all order</h5>
								<h1 class="mb-4 font-weight-semi-bold">Winter Collection</h1>
								<a href="" class="btn btn-outline-primary py-md-2 px-md-3">Shop
									Now</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Collection 1 Start-->
		</div>
	</div>
	<a class="carousel-control-prev" href="#header-carousel"
		data-slide="prev">
		<div class="btn btn-dark" style="width: 45px; height: 45px;">
			<span class="carousel-control-prev-icon mb-n2"></span>
		</div>
	</a> <a class="carousel-control-next" href="#header-carousel"
		data-slide="next">
		<div class="btn btn-dark" style="width: 45px; height: 45px;">
			<span class="carousel-control-next-icon mb-n2"></span>
		</div>
	</a>
</div>
<!-- Collection end -->


<!-- Shop Start -->
<div class="container-fluid ">
	<div class="row px-xl-5">
		<!-- Shop Sidebar Start -->
		<!-- filter by price -->
		<div class="col-lg-3 col-md-12">
			<!-- Price Start -->
			<div class="border-bottom mb-4 pb-4 mt-5">
				<h5 class="font-weight-semi-bold mb-4">Filter by price</h5>
				<form>
					<div
						class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
						<input type="checkbox" class="custom-control-input" checked
							id="price-all"> <label class="custom-control-label"
							for="price-all">All Price</label> <span
							class="badge border font-weight-normal">1000</span>
					</div>
					<div
						class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
						<input type="checkbox" class="custom-control-input" id="price-1">
						<label class="custom-control-label" for="price-1">$0 -
							$100</label> <span class="badge border font-weight-normal">150</span>
					</div>
					<div
						class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
						<input type="checkbox" class="custom-control-input" id="price-2">
						<label class="custom-control-label" for="price-2">$100 -
							$200</label> <span class="badge border font-weight-normal">295</span>
					</div>
					<div
						class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
						<input type="checkbox" class="custom-control-input" id="price-3">
						<label class="custom-control-label" for="price-3">$200 -
							$300</label> <span class="badge border font-weight-normal">246</span>
					</div>
					<div
						class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
						<input type="checkbox" class="custom-control-input" id="price-4">
						<label class="custom-control-label" for="price-4">$300 -
							$400</label> <span class="badge border font-weight-normal">145</span>
					</div>
					<div
						class="custom-control custom-checkbox d-flex align-items-center justify-content-between">
						<input type="checkbox" class="custom-control-input" id="price-5">
						<label class="custom-control-label" for="price-5">$400 -
							$500</label> <span class="badge border font-weight-normal">168</span>
					</div>
				</form>
			</div>
			<!-- Price End -->
		</div>
		<!-- Shop Sidebar End -->


		<!-- Shop Product Start -->
		<div class="col-lg-9 col-md-12">
			<div class="row pb-3">
				<!-- nav sort -->
				<div class="col-12 pb-1">
					<div class="d-flex align-items-center justify-content-between mb-4">
						<form action="">
							<div class="input-group">
								<input type="text" class="form-control"
									placeholder="Search by name">
								<div class="input-group-append">
									<span class="input-group-text bg-transparent text-primary">
										<i class="fa fa-search"></i>
									</span>
								</div>
							</div>
						</form>
						<div class="col-lg-3 d-none d-lg-block">
							<a
								class="btn shadow-none d-flex align-items-center justify-content-between text-white w-100"
								data-toggle="collapse" href="#navbar-vertical"
								style="height: 50px; margin-top: -1px;">
								<div class="input-group">
									<input type="text" onkeyup="fillerFunction()" id="input-cate"
										class="form-control" placeholder="Categories">
								</div> <i class="fa fa-angle-down text-dark"></i>
							</a>
							<nav
								class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0 bg-light"
								id="navbar-vertical"
								style="width: calc(100% - 30px); z-index: 1;">
								<div id="cate-list" class="navbar-nav w-100 overflow-hidden">
									<a href="" class="nav-item nav-link">Shirts</a> <a href=""
										class="nav-item nav-link">Jeans</a> <a href=""
										class="nav-item nav-link">Swimwear</a> <a href=""
										class="nav-item nav-link">Sleepwear</a> <a href=""
										class="nav-item nav-link">Sportswear</a> <a href=""
										class="nav-item nav-link">Jumpsuits</a> <a href=""
										class="nav-item nav-link">Blazers</a> <a href=""
										class="nav-item nav-link">Jackets</a> <a href=""
										class="nav-item nav-link">Shoes</a>
								</div>
							</nav>
						</div>
						<div class="dropdown ml-4">
							<button class="btn border dropdown-toggle" type="button"
								id="triggerId" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">Sort by</button>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="triggerId">
								<a class="dropdown-item" href="#">Latest</a> <a
									class="dropdown-item" href="#">Popularity</a> <a
									class="dropdown-item" href="#">Best Rating</a>
							</div>
						</div>
					</div>
				</div>
				<!-- end nav sort -->

				<c:forEach var="product" items="${list}">
					<div class="col-12 col-lg-4 col-md-6 ">
						<form action="<c:url value="/cart/add.do" />">
							<div class="col-sm-12 pb-1">
								<div class="card product-item border-0 mb-4">
									<div
										class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
										<img  class="img-fluid w-100" src="${product.imgMaster}" alt="img"> 
									</div>
									<div
										class="card-body border-left border-right text-center p-0 pt-4 pb-3">
										<h6 class="text-truncate mb-3">${product.name}</h6>
										<div class="d-flex justify-content-center">
											<h6>${product.price * (1+product.discount)}</h6>
											<h6 class="text-muted ml-2">
												<del>${product.price}</del>
											</h6>
										</div>
									</div>
									<div
										class="card-footer d-flex justify-content-between bg-light border">
										<a href="" class="btn btn-sm text-dark p-0"><i
											class="fas fa-eye text-primary mr-1"></i>View Detail</a> <a
											href="" class="btn btn-sm text-dark p-0"><i
											class="fas fa-shopping-cart text-primary mr-1"></i>Add To
											Cart</a>
									</div>
								</div>
							</div>
						</form>
					</div>
				</c:forEach>
				<!-- Phan trang -->
				<div class="col-12 pb-1">
					<nav aria-label="Page navigation">
						<ul class="pagination justify-content-center mb-3">
							<li class="page-item ${brandhomePage==1?'disabled':''}"><a
								class="page-link  " href="index.do?op=FirstPage"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
							<li class="page-item  ${brandhomePage==1?'disabled':''}"><a
								class="page-link" href="index.do?op=PreviousPage"
								aria-label="Next"> <span aria-hidden="true"><</span>
							</a></li>
							<li
								class="page-item  ${brandhomePage==listNumberBox.get(0)?'active':''}">
								<a class="page-link"
								href="index.do?op=GotoPage&gotoPage=${listNumberBox.get(0)}">
									${listNumberBox.get(0)} </a>
							</li>
							<li
								class="page-item  ${brandhomePage==listNumberBox.get(1)?'active':''}">
								<a class="page-link"
								href="index.do?op=GotoPage&gotoPage=${listNumberBox.get(1)}">
									${listNumberBox.get(1)} </a>
							</li>

							<li
								class="page-item  ${brandhomePage==listNumberBox.get(2)?'active':''}">
								<a class="page-link"
								href="index.do?op=GotoPage&gotoPage=${listNumberBox.get(2)}">
									${listNumberBox.get(2)} </a>
							</li>

							<li
								class="page-item ${brandhomePage==totalBrandhomePage?'disabled':''}">
								<!-- disabled --> <a class="page-link "
								href="index.do?op=NextPage" aria-label="Next"> <span
									aria-hidden="true">></span>
							</a>
							</li>
							<li
								class="page-item ${brandhomePage==totalBrandhomePage?'disabled':''}">
								<!-- disabled --> <a class="page-link "
								href="index.do?op=LastPage" aria-label="Next"> <span
									aria-hidden="true">&raquo;</span>
							</a>
							</li>
						</ul>
					</nav>
				</div>
				<!-- end phan trang -->
			</div>
		</div>
		<!-- Shop Product End -->
	</div>
</div>
<!-- Shop End -->


<!-- Footer Shop Start -->
<div class="container-fluid bg-secondary text-dark">
	<div class="row px-xl-5 pt-5">
		<div class="col-lg-4 col-md-12 mb-55 pr-3 pr-xl-5">

			<a href="" class="text-decoration-none">
				<h1 class="mb-4 display-5 font-weight-semi-bold">Ten Shop</h1>
			</a>
			<p>Mong rang se dem lai cho quy khach trai nghiem tot nhat</p>
			<p class="mb-2">
				<i class="fa fa-map-marker-alt text-primary mr-3"></i>123 Duong 13,
				Khu Pho 4, Thu Duc, TP.HCM
			</p>
			<p class="mb-2">
				<i class="fa fa-map-marker-alt text-primary mr-3"></i>Lo 37 Khu CNC,
				Quan 9, TP.HCM
			</p>

			<p class="mb-2">
				<i class="fa fa-envelope text-primary mr-3"></i>info@example.com
			</p>
			<p class="mb-0">
				<i class="fa fa-phone-alt text-primary mr-3"></i>+012 345 67890
			</p>

		</div>
		<div class="col-lg-8 col-md-12">
			<div class=" mb-5">
				<div id="map" style="height: 250px;"></div>
			</div>
		</div>
	</div>
</div>
<!-- Footer Shop End -->

<!-- Back to Top -->
<a href="# " class="btn btn-primary back-to-top "><i
	class="fa fa-angle-double-up "></i></a>


<!-- Map Javascrip start -->
<script>
	// chinh sua view o day 
	var map = L.map('map').setView([ 10.8432898, 106.79821 ], 13);
	// vi tri cac chi nhanh o day
	L.marker([ 10.8432898, 106.79821 ]).addTo(map);

	L
			.tileLayer(
					'https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}',
					{
						attribution : 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
						maxZoom : 18,
						id : 'mapbox/streets-v11',
						tileSize : 512,
						zoomOffset : -1,
						accessToken : 'pk.eyJ1Ijoibmd1eWVubmFtMDIwNjIwMSIsImEiOiJjbDM0YTl4N3oybnEyM2pvNXl0eXJpOWs5In0.Otjl-Mb7GrEYE5kXxW4qAQ'
					}).addTo(map);
	var x = document.getElementById("demo");

	// function nay lay location cua user
	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition, showError);
		} else {
			x.innerHTML = "Geolocation is not supported by this browser.";
		}
	}

	function showPosition(position) {
		var marker = L.marker(
				[ position.coords.latitude, position.coords.longitude ]).addTo(
				map);
	}

	function showError(error) {
		switch (error.code) {
		case error.PERMISSION_DENIED:
			x.innerHTML = "User denied the request for Geolocation."
			break;
		case error.POSITION_UNAVAILABLE:
			x.innerHTML = "Location information is unavailable."
			break;
		case error.TIMEOUT:
			x.innerHTML = "The request to get user location timed out."
			break;
		case error.UNKNOWN_ERROR:
			x.innerHTML = "An unknown error occurred."
			break;
		}
	}
	getLocation();
</script>
<!-- Map Javascript end -->

<!-- Template Javascript -->
<script>
	$('#myModal').on('shown.bs.modal', function() {
		$('#myInput').trigger('focus')
	})

	var fillerFunction = function() {
		var input = document.getElementById("input-cate").value;
		var cateList = document.getElementById("cate-list")
				.getElementsByTagName('a');
		for (var i = 0; i < cateList.length; i++) {
			cateList[i].style.display = "";
		}
		if (input.length != 0) {
			for (var i = 0; i < cateList.length; i++) {
				var cateDetail = cateList[i].textContent
						|| cateList[i].innerText;
				// alert(cateDetail.match(input));
				if (cateDetail.toLowerCase().match(input) == null)
					cateList[i].style.display = "none";
			}
		}
	}
</script>