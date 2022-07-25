<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

	<!-- Shop info Start -->
	<div class="container-fluid bg-secondary mb-3">
		<div
			class="d-flex flex-column align-items-center justify-content-center"
			style="background-image: url('https://mdbootstrap.com/img/Photos/Others/images/76.jpg'); min-height: 300px">
			<h1 class="font-weight-semi-bold text-uppercase mb-3">${requestScope.brand.getName() }</h1>
			<h2 class="font-weight-semi-bold text-uppercase mb-3">LATEST PRODUCTS</h2>
		</div>
	</div>
	<!-- Shop info End -->

	<!-- Shop Start -->
	<div class="container-fluid ">
		<div class="row px-xl-5">
			


			<!-- Shop Product Start -->
			<div class="col-lg-12 col-md-12">
				<div class="row pb-3">

					<c:forEach var="product" items="${list}">
						<div class="col-12 col-lg-3 col-md-4 ">
							<div class="col-sm-12 pb-1">
								<div class="card product-item border-0 mb-4">
									<div
										class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
										<img class="img-fluid w-100" src="${product.imgMaster}"
											alt="img">
									</div>
									<div
										class="card-body border-left border-right text-center p-0 pt-4 pb-3">
										<h6 class="text-truncate mb-3">${product.name}</h6>
										<div class="d-flex justify-content-center">
											<h6>${product.price * (1-product.discount)}</h6>
											<c:if test="${product.discount != 0}">
											<h6 class="text-muted ml-2">
												<del>${product.price}</del>
											</h6>
											</c:if>
										</div>
									</div>
									<div
										class="card-footer d-flex justify-content-between bg-light border">
										<a href="/localbrand/web/detail/index.do?productid=${product.id}" class="mx-auto btn btn-sm text-dark p-0" target="_blank"><i
											class="fas fa-eye text-primary mr-1"></i>View Detail</a> 
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					<!-- Phan trang -->
					<div class="col-12 pb-1">
						<nav aria-label="Page navigation">
							<ul class="pagination justify-content-center mb-3">
								<li class="page-item ${brandhomePage==1?'disabled':''}"><a
									class="page-link  " href="latest.do?id=${brandId}&op=FirstPage"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
								<li class="page-item  ${brandhomePage==1?'disabled':''}"><a
									class="page-link" href="latest.do?id=${brandId}&op=PreviousPage"
									aria-label="Next"> <span aria-hidden="true"><</span>
								</a></li>
								<li
									class="page-item  ${brandhomePage==listNumberBox.get(0)?'active':''}">
									<a class="page-link"
									href="latest.do?id=${brandId}&op=GotoPage&gotoPage=${listNumberBox.get(0)}">
										${listNumberBox.get(0)} </a>
								</li>
								<li
									class="page-item  ${brandhomePage==listNumberBox.get(1)?'active':''}"
									style="${totalBrandhomePage <listNumberBox.get(1)?" display:none":""}">
									<a class="page-link"
									href="latest.do?id=${brandId}&op=GotoPage&gotoPage=${listNumberBox.get(1)}">
										${listNumberBox.get(1)} </a>
								</li>

								<li
									class="page-item  ${brandhomePage==listNumberBox.get(2)?'active':''}"
									style="${totalBrandhomePage <listNumberBox.get(2)?" display:none":""}">
									<a class="page-link"
									href="latest.do?id=${brandId}&op=GotoPage&gotoPage=${listNumberBox.get(2)}">
										${listNumberBox.get(2)} </a>
								</li>

								<li
									class="page-item ${brandhomePage==totalBrandhomePage?'disabled':''}">
									<!-- disabled --> <a class="page-link "
									href="latest.do?id=${brandId}&op=NextPage" aria-label="Next">
										<span aria-hidden="true">></span>
								</a>
								</li>
								<li
									class="page-item ${brandhomePage==totalBrandhomePage?'disabled':''}">
									<!-- disabled --> <a class="page-link "
									href="latest.do?id=${brandId}&op=LastPage" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
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
				<h1 class="mb-4 display-5 font-weight-semi-bold">${requestScope.brand.getName() }</h1>
			</a>
			<p>${requestScope.brand.getDescription() }</p>
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