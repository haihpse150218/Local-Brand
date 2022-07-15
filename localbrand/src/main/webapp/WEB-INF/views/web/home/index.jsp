<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>


<!-- Shop Start -->
<div class="container-fluid ">
	<div class="row px-xl-5">
		<!-- Shop Sidebar Start -->
		<div class="col-lg-3 col-md-12">

			<!-- Vendor Start -->
			<div class="container-fluid">
				<div class="input-group mb-3">
					<input id="input-filter" onkeyup="filterBrandFunction()"
						type="text" class="form-control" placeholder="Search by name">
					<div class="input-group-append">
						<span class="input-group-text bg-transparent text-primary">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<div class="panel-collapse "
					style="height: 1000px; overflow-x: hidden; overflow-y: scroll;">
					<table class="table">
						<tbody>
						<!-- list brand start -->
							<c:forEach var="brand" items="${sessionScope.listAllBrand}">
							<tr>
								<div class="row border shop-left">
									<a class="col-6 navbar-nav ml-auto" href="/localbrand/web/home/viewlistbrandproduct.do?brandid=${brand.id}"><img
										style="height: 140px; width: 140px;" src="${brand.getLogo()}"
										alt=""></a>
									<div class="col-6">
										<p class="pt-3 shop-name">${brand.getName()}</p>
										<p>
										<div class="text-primary ">
											<small class="fas fa-star"></small> <small
												class="fas fa-star"></small> <small class="fas fa-star"></small>
											<small class="fas fa-star-half-alt"></small> <small
												class="far fa-star"></small>
										</div>
										</p>
										<a href="/localbrand/web/brandhome/index.do?id=${brand.id}">View shop</a>
									</div>
								</div>
							</tr>
							</c:forEach>
							<!-- list brand end-->
						</tbody>
					</table>
				</div>

			</div>
			<!-- Vendor End -->


		</div>
		<!-- Shop Sidebar End -->


		<!-- Shop Product Start -->
		<div class="col-lg-9 col-md-12">
			<div class="text-center mb-4">
				<h2 class="section-title px-5">
					<span class="px-2">Top Trending Products</span>
				</h2>
			</div>


			<div class="row pb-3">
			
			<!-- phan trang ne -->
			<c:forEach var="product" items="${list}">
					<div class="col-12 col-lg-4 col-md-6 ">
						<form action="<c:url value="/addToCart.do" />">
						<input type="hidden" name="productid">
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
										<a href="/localbrand/web/detail/index.do?productId=${product.id}" class="btn btn-sm text-dark p-0" name="viewdetail"><i
											class="fas fa-eye text-primary mr-1"></i>View Detail</a> <a
											href="/localbrand/web/home/addtocart.do?productid=${product.id}&quantity=1" class="btn btn-sm text-dark p-0"><i
											class="fas fa-shopping-cart text-primary mr-1"></i>Add To
											Cart</a>
									</div>
								</div>
							</div>
						</form>
					</div>
				</c:forEach>
			</div>



			<!-- Phan trang -->
			<div class="col-12 pb-1">
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center mb-3">
						<li class="page-item ${homePage==1?'disabled':''}"><a
							class="page-link  " href="index.do?op=FirstPage"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
						<li class="page-item  ${homePage==1?'disabled':''}"><a
							class="page-link" href="index.do?op=PreviousPage"
							aria-label="Next"> <span aria-hidden="true"><</span>
						</a></li>
						<li
							class="page-item  ${homePage==listNumberBox.get(0)?'active':''}">
							<a class="page-link"
							href="index.do?op=GotoPage&gotoPage=${listNumberBox.get(0)}">
								${listNumberBox.get(0)} </a>
						</li>
						<li
							class="page-item  ${homePage==listNumberBox.get(1)?'active':''}"
							style="${totalHomePage <listNumberBox.get(1)?" display:none":""}">
							<a class="page-link"
							href="index.do?op=GotoPage&gotoPage=${listNumberBox.get(1)}">
								${listNumberBox.get(1)} </a>
						</li>

						<li
							class="page-item  ${homePage==listNumberBox.get(2)?'active':''}"
							style="${totalHomePage <listNumberBox.get(2)?" display:none":""}">
							<a class="page-link"
							href="index.do?op=GotoPage&gotoPage=${listNumberBox.get(2)}">
								${listNumberBox.get(2)} </a>
						</li>

						<li
							class="page-item ${homePage==totalHomePage?'disabled':''}">
							<!-- disabled --> <a class="page-link "
							href="index.do?op=NextPage" aria-label="Next"> <span
								aria-hidden="true">></span>
						</a>
						</li>
						<li
							class="page-item ${homePage==totalHomePage?'disabled':''}">
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
<script>
	var filterBrandFunction = function() {
		var input = document.getElementById("input-filter").value;
		var List = document.getElementsByClassName("shop-left");
		for (var i = 0; i < List.length; i++) {
			List[i].style.display = "";
		}
		if (input.length != 0) {
			for (var i = 0; i < List.length; i++) {
				var Detail = document.getElementsByClassName("shop-name")[i].textContent
						|| document.getElementsByClassName("shop-name")[i].innerText;
				console.log(1)
				// alert(Detail.match(input));
				if (Detail.toLowerCase().match(input) == null)
					List[i].style.display = "none";
			}
		}
	}
</script>