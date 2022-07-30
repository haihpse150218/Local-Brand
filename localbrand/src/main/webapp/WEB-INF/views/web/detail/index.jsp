<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="pChild" scope="request" value="${requestScope.pChild}" />
<c:set var="fDetail" scope="request" value="${requestScope.fDetail}" />
<c:set var="cusDetail" scope="request" value="${requestScope.cusDetail}" />

<c:set var="product" scope="request" value="${requestScope.pDetail}" />
<c:set var="brand" scope="request" value="${requestScope.bDetail}" />
<c:set var="listSize" scope="request" value="${requestScope.listSize}" />
<c:set var="listColor" scope="request" value="${requestScope.listColor}" />


<body>
	<!-- Page Header Start -->

	<div class="container-fluid bg-secondary mb-5">
		<div
			class="d-flex flex-column align-items-center justify-content-center"
			style="min-height: 300px">
			<h1 class="font-weight-semi-bold text-uppercase mb-3">${brand.name}</h1>
		</div>
	</div>

	<!-- Page Header End -->

	<!-- Shop Detail Start -->


	<div class="container-fluid py-5">
		<form action="/localbrand/web/detail/addtocart.do" method="GET">
			<div class="row px-xl-5">
				<div class="col-lg-5 pb-5">
					<div id="product-carousel" class="carousel slide"
						data-ride="carousel">
						<!-- neu co nhieu product con thi them vao day-->
						<div class="carousel-inner border">

							<c:if test="${empty pChild}">
								<div class="carousel-item active">
									<!--  <img class="w-100 h-100" src="/localbrand/assets/img/Product1/product1.jpg" alt="Image"> -->
									<img class="w-100 h-100" src="${product.imgMaster}" alt="Image">
								</div>
							</c:if>

							<c:if test="${not empty pChild}">
								<div class="carousel-item active">

									<!--  <img class="w-100 h-100" src="/localbrand/assets/img/Product1/product1.jpg" alt="Image"> -->
									<img class="w-100 h-100" src="${product.imgMaster}" alt="Image">
								</div>
								<c:forEach items="${pChild}" var="pChild">
									<c:if test="${not empty pChild.imgChild}">
										<div class="carousel-item">
											<!-- <img class="w-100 h-100" src="/localbrand/assets/img/Product1/product1.jpg" alt="Image"> -->
											<img class="w-100 h-100" src="${pChild.imgChild}" alt="Image">
										</div>
									</c:if>
								</c:forEach>
							</c:if>
						</div>
						<a class="carousel-control-prev" href="#product-carousel"
							data-slide="prev"> <i
							class="fa fa-2x fa-angle-left text-dark"></i>
						</a> <a class="carousel-control-next" href="#product-carousel"
							data-slide="next"> <i
							class="fa fa-2x fa-angle-right text-dark"></i>
						</a>
					</div>
				</div>

				<div class="col-lg-7 pb-5">
					<h3 class="font-weight-semi-bold">${product.name}</h3>
					<div class="d-flex mb-3">
						<div class="text-primary mr-2">
							<c:forEach begin="1" end="5" varStatus="loop">
								<c:if test="${loop.count<=product.stars}">
									<i class="fas fa-star"></i>
								</c:if>
								<c:if
									test="${loop.count>product.stars && product.stars+1>loop.count}">
									<i class="fas fa-star-half-alt"></i>
								</c:if>
								<c:if
									test="${loop.count>product.stars && product.stars+1<=loop.count}">
									<i class="far fa-star"></i>
								</c:if>
							</c:forEach>
						</div>
						<%-- <div class="text-primary ">
												<small class="fas fa-star"></small> <small
													class="fas fa-star"></small> <small class="fas fa-star"></small>
												<small class="fas fa-star-half-alt"></small> <small
													class="far fa-star"></small>
											</div>
											--%>
						<small class="pt-1">${product.stars}
							(${product.orderDetailList.size()} Products Sold)</small>
					</div>
					<c:if test="${product.discount == 0}">
						<h3 class="font-weight-semi-bold mb-4">
							<fmt:setLocale value="vi_VN" />
							<fmt:formatNumber value="${product.price}" type="currency" />
						</h3>
					</c:if>
					<c:if test="${product.discount > 0}">
						<div class="d-flex">
							<h3>
								<fmt:setLocale value="vi_VN" />
								<fmt:formatNumber value="${product.price* (1-product.discount)}"
									type="currency" />
							</h3>
							<h3 class="text-muted ml-2">
								<del>
									<fmt:setLocale value="vi_VN" />
									<fmt:formatNumber value="${product.price}" type="currency" />
								</del>
							</h3>
						</div>
					</c:if>
					<p class="mb-4">${product.description}.</p>
					<c:if test="${not empty product.size}">
						<div class="d-flex mb-3">
							<p class="text-dark font-weight-medium mb-0 mr-3">Sizes:</p>
							<c:if test="${not empty listSize}">
								<c:forEach items="${listSize}" var="listSize">
									<c:if test="${listSize == product.size}">
										<div class="custom-control custom-radio custom-control-inline">
											<input type="radio" class="custom-control-input"
												checked="checked" id="${listSize}" name="size"
												value="${listSize}"> <label
												class="custom-control-label" for="${listSize}">${listSize}</label>
										</div>
									</c:if>
									<c:if test="${listSize != product.size}">
										<div class="custom-control custom-radio custom-control-inline">
											<input type="radio" class="custom-control-input"
												id="${listSize}" name="size" value="${listSize}"> <label
												class="custom-control-label" for="${listSize}">${listSize}</label>
										</div>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${empty listSize}">

								<div class="custom-control custom-radio custom-control-inline">
									<input type="radio" class="custom-control-input"
										id="${product.size}" name="size" value="${product.size}"
										checked="checked"> <label class="custom-control-label"
										for="${product.size}">${product.size}</label>
								</div>
							</c:if>

						</div>
					</c:if>
					<div class="d-flex mb-4">
						<p class="text-dark font-weight-medium mb-0 mr-3">Colors:</p>

						<c:if test="${not empty listColor}">
							<c:forEach items="${listColor}" var="listColor">
								<c:if test="${listColor == product.color}">
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" class="custom-control-input"
											checked="checked" id="${listColor}" name="color"
											value="${listColor}"> <label
											class="custom-control-label" for="${listColor}">${listColor}</label>
									</div>
								</c:if>
								<c:if test="${listColor != product.color}">
									<div class="custom-control custom-radio custom-control-inline">
										<input type="radio" class="custom-control-input"
											id="${listColor}" name="color" value="${listColor}">
										<label class="custom-control-label" for="${listColor}">${listColor}</label>
									</div>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${empty pChild}">
							<div class="custom-control custom-radio custom-control-inline">
								<input type="radio" class="custom-control-input"
									id="${product.color}" name="color" value="${product.color}"
									checked="checked"> <label class="custom-control-label"
									for="${product.color}">${product.color}</label>
							</div>
						</c:if>

					</div>
					<div class="d-flex align-items-center mb-4 pt-2">
						<div class="input-group quantity mr-3" style="width: 130px;">
							<div class="input-group-btn">
								<button class="btn btn-primary btn-minus"
									onclick="buttonClickDecrease();" type="button">
									<i class="fa fa-minus"></i>
								</button>
							</div>
							<input type="text"
								class="form-control bg-secondary text-center input-number"
								value="1" min="1" max="10" name="quantity" id="quantity">
							<div class="input-group-btn">
								<button class="btn btn-primary btn-plus"
									onclick="buttonClickIncrease();" type="button">
									<i class="fa fa-plus"></i>
								</button>
							</div>
						</div>
						<!-- <c:if test="${empty pChild}">
                    
                    <a href="/localbrand/web/cart/index.do?productId=${product.id}&productPrice=${product.price}">
                    	<button class="btn btn-primary px-3"><i class="fa fa-shopping-cart mr-1"></i> Add To Cart</button>
                    </a>
                    </c:if>
                    <c:if test="${not empty pChild}">
                    <a href="/localbrand/web/cart/index.do?productId=${product.id}&productName=${product.name}&productSize=${product.size}&productColor=${product.color}&productPrice=${product.price}">
                    	<button class="btn btn-primary px-3"><i class="fa fa-shopping-cart mr-1"></i> Add To Cart</button>
                    </a>
                    </c:if> -->
						<input type="hidden" name="productid" value="${product.id}">
						<button class="btn btn-primary px-3">
							<i class="fa fa-shopping-cart mr-1"></i> Add To Cart
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>


	<input type="hidden" id="productId" name="productId"
		value="${product.id}">
	<div class="row px-xl-5">
		<div class="col">
			<div
				class="nav nav-tabs justify-content-center border-secondary mb-4">
				<a class="nav-item nav-link active" data-toggle="tab"
					href="#tab-pane-1">Description</a> <a class="nav-item nav-link"
					data-toggle="tab" href="#tab-pane-2">Reviews
					(${fn:length(fDetail)})</a>
			</div>
			<div class="tab-content">
				<div class="tab-pane fade show active" id="tab-pane-1">
					<h4 class="mb-3">Product Description</h4>
					<p>${product.description}</p>

				</div>
				<div class="tab-pane fade" id="tab-pane-2">
					<div class="row">
						<c:if test="${empty fDetail}">
							<div class="col-md-6">
								<h4 class="mb-4">0 review for "${product.name}"</h4>
								<div class="media mb-4">

									<div class="media-body">

										<p>No review</p>
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${not empty fDetail}">

							<div class="col-md-12">
								<h4 class="mb-12">${fn:length(fDetail)}reviewfor
									"${product.name}"</h4>
								<c:forEach items="${fDetail}" var="fDetail" varStatus="fCount">
									<c:forEach items="${cusDetail}" var="cusDetail"
										varStatus="cusCount">

										<c:if test="${fCount.count == cusCount.count}">

											<div class="media mb-4">
												<img src="${cusDetail.avatar}" alt="Image"
													class="img-fluid mr-3 mt-1" style="width: 45px;">
												<div class="media-body">
													<h6>${cusDetail.name}<small> - <i>${fDetail.feedbackTime}</i></small>
													</h6>
													<div class="text-primary mb-2">
														<c:forEach begin="1" end="5" varStatus="loop">
															<c:if test="${loop.count<=fDetail.voting}">
																<i class="fas fa-star"></i>
															</c:if>
															<c:if
																test="${loop.count>fDetail.voting && fDetail.voting+1>loop.count}">
																<i class="fas fa-star-half-alt"></i>
															</c:if>
															<c:if
																test="${loop.count>fDetail.voting && fDetail.voting+1<=loop.count}">
																<i class="far fa-star"></i>
															</c:if>
														</c:forEach>
														<font color="black"> ${fDetail.voting} </font>
													</div>
													<p>${fDetail.textComment}</p>

												</div>
											</div>

										</c:if>

									</c:forEach>
								</c:forEach>
							</div>

						</c:if>


					</div>
				</div>

			</div>
		</div>
	</div>


	<!--  -->

	<!-- Shop Detail End -->


	<!-- Products Start -->
	<!--  
	<div class="container-fluid py-5">
		<div class="text-center mb-4">
			<h2 class="section-title px-5">
				<span class="px-2">You May Also Like</span>
			</h2>
		</div>
		<div class="row px-xl-5">
			<div class="col">
				<div class="owl-carousel related-carousel">
					<div class="card product-item border-0">
						<div
							class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
							<img class="img-fluid w-100" src="" alt="">
						</div>
						<div
							class="card-body border-left border-right text-center p-0 pt-4 pb-3">
							<h6 class="text-truncate mb-3">Colorful Stylish Shirt</h6>
							<div class="d-flex justify-content-center">
								<h6>$123.00</h6>
								<h6 class="text-muted ml-2">
									<del>$123.00</del>
								</h6>
							</div>
						</div>
						<div
							class="card-footer d-flex justify-content-between bg-light border">
							<a href="" class="btn btn-sm text-dark p-0"><i
								class="fas fa-eye text-primary mr-1"></i>View Detail</a> <a href=""
								class="btn btn-sm text-dark p-0"><i
								class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
						</div>
					</div>
					<div class="card product-item border-0">
						<div
							class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
							<img class="img-fluid w-100" src="" alt="">
						</div>
						<div
							class="card-body border-left border-right text-center p-0 pt-4 pb-3">
							<h6 class="text-truncate mb-3">Colorful Stylish Shirt</h6>
							<div class="d-flex justify-content-center">
								<h6>$123.00</h6>
								<h6 class="text-muted ml-2">
									<del>$123.00</del>
								</h6>
							</div>
						</div>
						<div
							class="card-footer d-flex justify-content-between bg-light border">
							<a href="" class="btn btn-sm text-dark p-0"><i
								class="fas fa-eye text-primary mr-1"></i>View Detail</a> <a href=""
								class="btn btn-sm text-dark p-0"><i
								class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
						</div>
					</div>
					<div class="card product-item border-0">
						<div
							class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
							<img class="img-fluid w-100" src="" alt="">
						</div>
						<div
							class="card-body border-left border-right text-center p-0 pt-4 pb-3">
							<h6 class="text-truncate mb-3">Colorful Stylish Shirt</h6>
							<div class="d-flex justify-content-center">
								<h6>$123.00</h6>
								<h6 class="text-muted ml-2">
									<del>$123.00</del>
								</h6>
							</div>
						</div>
						<div
							class="card-footer d-flex justify-content-between bg-light border">
							<a href="" class="btn btn-sm text-dark p-0"><i
								class="fas fa-eye text-primary mr-1"></i>View Detail</a> <a href=""
								class="btn btn-sm text-dark p-0"><i
								class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
						</div>
					</div>
					<div class="card product-item border-0">
						<div
							class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
							<img class="img-fluid w-100" src="" alt="">
						</div>
						<div
							class="card-body border-left border-right text-center p-0 pt-4 pb-3">
							<h6 class="text-truncate mb-3">Colorful Stylish Shirt</h6>
							<div class="d-flex justify-content-center">
								<h6>$123.00</h6>
								<h6 class="text-muted ml-2">
									<del>$123.00</del>
								</h6>
							</div>
						</div>
						<div
							class="card-footer d-flex justify-content-between bg-light border">
							<a href="" class="btn btn-sm text-dark p-0"><i
								class="fas fa-eye text-primary mr-1"></i>View Detail</a> <a href=""
								class="btn btn-sm text-dark p-0"><i
								class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
						</div>
					</div>
					<div class="card product-item border-0">
						<div
							class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
							<img class="img-fluid w-100" src="" alt="">
						</div>
						<div
							class="card-body border-left border-right text-center p-0 pt-4 pb-3">
							<h6 class="text-truncate mb-3">Colorful Stylish Shirt</h6>
							<div class="d-flex justify-content-center">
								<h6>$123.00</h6>
								<h6 class="text-muted ml-2">
									<del>$123.00</del>
								</h6>
							</div>
						</div>
						<div
							class="card-footer d-flex justify-content-between bg-light border">
							<a href="" class="btn btn-sm text-dark p-0"><i
								class="fas fa-eye text-primary mr-1"></i>View Detail</a> <a href=""
								class="btn btn-sm text-dark p-0"><i
								class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	-->
	<!-- Products End -->
	<!-- Back to Top -->
	<!--<a href="#" class="btn btn-primary back-to-top"><i
		class="fa fa-angle-double-up"></i></a>
  -->

	<!-- JavaScript Libraries 
   
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    -->
	<!--<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script> -->
	<!-- Contact Javascript File 
    <script src="mail/jqBootstrapValidation.min.js"></script>
    <script src="mail/contact.js"></script>
	-->
	<!-- Template Javascript 
    <script src="js/main.js"></script>-->
	<script>
		function buttonClickDecrease() {
			document.getElementById('quantity').value--;
		}
		function buttonClickIncrease() {
			document.getElementById('quantity').value++;
		}
	</script>
</body>

</html>