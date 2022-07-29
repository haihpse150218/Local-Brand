<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:set var="order" scope="request" value="${requestScope.LIST_ORDER}" />
<c:set var="orderid" scope="request" value="${requestScope.orderid}" />
<body>



    <!-- Page Header Start -->
    <div class="container-fluid bg-secondary mb-5">
        <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
            <h1 class="font-weight-semi-bold text-uppercase mb-3">Ordered</h1>
            <div class="d-inline-flex">
                <p class="m-0"><a href="">Home</a></p>
                <p class="m-0 px-2">-</p>
                <p class="m-0">Detail Order</p>
            </div>
        </div>
    </div>
    <!-- Page Header End -->


    <!-- Cart Start -->
    <div class="container-fluid pt-5">
        <div class="row px-xl-5">
            <div class="col-lg-12 table-responsive mb-5">
            <c:forEach var="order" items="${requestScope.LIST_ORDER}" varStatus="loop"> 
            	<c:forEach var="orderDetail" items="${order.orderDetailList}" varStatus="loop"> 
            	<c:if test="${orderid == order.id}">
                <table class="table table-bordered text-center mb-0">
                    <thead class="bg-secondary text-dark">
                        <tr>
                            <th>Chi tiet don hang ${orderDetail.product.name}</th>
                        </tr>
                    </thead>
                    <tbody class="align-middle">
                        <tr>
                        </tr>
                        <!-- For product trong don -->
                        <td class="align-middle">
                            <div class="row px-xl-5">
                                <div class="col-lg-4 pb-5">
                                    <div id="product-carousel" class="carousel slide" data-ride="carousel">
                                        <div class="carousel-inner border">
                                        <c:if test="${not empty orderDetail.product.imgMaster}">
                                            <div class="carousel-item active">
                                                <img class="" src="${orderDetail.product.imgMaster}" style="height: 200px!important;" alt="Image">
                                            </div>
                                         </c:if>
                                         <c:if test="${empty orderDetail.product.imgMaster}">
                                            <div class="carousel-item active">
                                                <img class="" src="${orderDetail.product.imgChild}" style="height: 200px!important;" alt="Image">
                                            </div>
                                         </c:if>    
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-8 pb-5">
                                    <h4 class="font-weight-semi-bold">${orderDetail.product.name}</h4>
                                    <div class="d-flex justify-content-center mb-2">
                                        <p class="text-dark font-weight-medium mb-0 mr-3">Price:</p>
                                        <div class="custom-control custom-control-inline">
                                            <label><fmt:setLocale value="vi_VN" /> <fmt:formatNumber
												value="${orderDetail.price}" type="currency" /></label>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-center mb-2">
                                        <p class="text-dark font-weight-medium mb-0 mr-3">Sizes:</p>
                                        <div class="custom-control custom-control-inline">
                                            <label>${orderDetail.product.size}</label>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-center mb-2">
                                        <p class="text-dark font-weight-medium mb-0 mr-3">Colors:</p>
                                        <div class="custom-control custom-control-inline">
                                            <label>${orderDetail.product.color}</label>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-center mb-2">
                                        <p class="text-dark font-weight-medium mb-0 mr-3">Quantity:</p>
                                        <div class="custom-control custom-control-inline">
                                            <label>${orderDetail.quantity}</label>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </td>
                        </tr>
                        <!-- End for-->
                        <tr>
                            <td>
                                <div class="d-flex my-3 justify-content-center">
                                    <h class="mb-0 mr-2">Your Rating * </h>
                                    <div class="text-primary">
                                        <small onmouseover="return starHover(1);" onclick="return voting(1);" class="far fa-star star"></small>
                                        <small onmouseover="return starHover(2);" onclick="return voting(2);" class="far fa-star star"></small>
                                        <small onmouseover="return starHover(3);" onclick="return voting(3);" class="far fa-star star"></small>
                                        <small onmouseover="return starHover(4);" onclick="return voting(4);" class="far fa-star star"></small>
                                        <small onmouseover="return starHover(5);" onclick="return voting(5);" class="far fa-star star"></small>
                                    </div>
                                </div>
                                <form action="/localbrand/web/order/createfb.do">
                                    <div class="form-group">
                                        <label for="message">Your Review *</label>
                                        <textarea id="message" cols="30" rows="5" class="form-control" name="txtComment"></textarea>
                                        <!--  <input hidden="hidden" type="text" name="txtStar" class="form-control" id="star-input">-->
                                        <input type="hidden" name="txtStar" id="star-input" value="">
                                        <input type="hidden" name="productid" value="${orderDetail.product.id}">
                                        <input type="hidden" name="orderid" value="${order.id}">
                                    </div>
                                    <div class="form-group mb-0">
                                        <input type="submit" value="Review" class="btn btn-primary px-3">
                                    </div>
                                    <div style="font-style: italic" class="mt-3 text-danger">${mess}</div>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </table>
                </c:if>
                </c:forEach>
                </c:forEach>
            </div>
        </div>
    </div>

    <script>
        var starHover = function(value) {
            var star = document.getElementsByClassName("star");
            for (var i = 0; i < 5; i++) {
                star[i].classList.remove("fas");
                star[i].classList.add("far");
            }
            for (var i = 0; i < value; i++) {
                star[i].classList.remove("far");
                star[i].classList.add("fas");
            }
            document.getElementById("star-input").value = value;
            return false;
        }
        var voting = function(value) {
            var star = document.getElementsByClassName("star");
            starHover = false;
            for (var i = 0; i < 5; i++) {
                star[i].classList.remove("fas");
                star[i].classList.add("far");
            }
            for (var i = 0; i < value; i++) {
                star[i].classList.remove("far");
                star[i].classList.add("fas");
            }
            document.getElementById("star-input").value = value;
            return false;
        }
    </script>



    <!-- Back to Top
    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

-->
    <!-- JavaScript Libraries 
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
-->
    <!-- Contact Javascript File 
    <script src="mail/jqBootstrapValidation.min.js"></script>
    <script src="mail/contact.js"></script>
-->
    <!-- Template Javascript 
    <script src="js/main.js"></script> 
    -->
</body>

</html>