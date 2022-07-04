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
							<!-- Shop 1 left start-->
							<tr>
								<div class="row border shop-left">
									<a class="col-6 navbar-nav ml-auto" href="#"><img
										style="height: 140px; width: 140px;" src=""
										alt=""></a>
									<div class="col-6">
										<p class="pt-3 shop-name">Ten shop</p>
										<p>
										<div class="text-primary ">
											<small class="fas fa-star"></small> <small
												class="fas fa-star"></small> <small class="fas fa-star"></small>
											<small class="fas fa-star-half-alt"></small> <small
												class="far fa-star"></small>
										</div>
										</p>
										<a href="#">View shop</a>
									</div>
								</div>
							</tr>
							<!-- Shop 1 end-->
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
					<span class="px-2">Trandy Products</span>
				</h2>
			</div>


			<div class="row pb-3">
				<c:forEach var="product" items="${listTrendProduct}">
					<div class="col-12 col-lg-4 col-md-6 ">
						<form action="<c:url value="/cart/add.do" />">
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
			</div>



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