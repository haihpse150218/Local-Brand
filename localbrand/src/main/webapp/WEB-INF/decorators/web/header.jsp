<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp" %> 


<!-- Topbar Start -->
    <div class="container-fluid">
        <div class="row bg-secondary py-2 px-xl-5">
            <div class="col-lg-6 d-none d-lg-block">
                <div class="d-inline-flex align-items-center">
                    <a class="text-dark" href="">FAQs</a>
                    <span class="text-muted px-2">|</span>
                    <a class="text-dark" href="http://eepurl.com/hy-j-r">Contact</a>
                    <span class="text-muted px-2">|</span>
                    <a class="text-dark" href="/localbrand/admin">Collaborators ?</a>
                </div>
            </div>
            <div class="col-lg-6 text-center text-lg-right">
                <div class="d-inline-flex align-items-center">
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-linkedin-in"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-instagram"></i>
                    </a>
                    <a class="text-dark pl-2" href="">
                        <i class="fab fa-youtube"></i>
                    </a>
                </div>
            </div>
        </div>
        <div class="row align-items-center py-3 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="/localbrand/web/home/index.do" class="text-decoration-none">
                    <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E-localbrand</span></h1>
                </a>
            </div>
            <div class="col-lg-6 col-6 text-left">
                <form action="">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search for products">
                        <div class="input-group-append">
                            <span class="input-group-text bg-transparent text-primary">
                                <i class="fa fa-search"></i>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-3 col-6 text-right">
                <a href="" class="btn border">
                    <i class="fas fa-heart text-primary"></i>
                    <span class="badge">0</span>
                </a>
                <a href="/localbrand/web/cart/index.do" class="btn border">
                    <i class="fas fa-shopping-cart text-primary"></i>
                    <span class="badge">${sessionScope.cartQuantity}</span>
                </a>
            </div>
        </div>
    </div>
    <!-- Topbar End -->


    <!-- Navbar Start -->
    <div class="container-fluid">
        <div class="row border-top px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                    <h6 class="m-0">Categories</h6>
                    <i class="fa fa-angle-down text-dark"></i>
                </a>
                <nav class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0 bg-light" id="navbar-vertical" style="width: calc(100% - 30px); z-index: 1;">
                    <div class="navbar-nav w-100 overflow-hidden" >
                      <div class="panel-collapse "
					style="height: 450px; overflow-x: hidden; overflow-y: scroll;">
                        <c:forEach var="cate" items="${sessionScope.category}">
                        <a href="/localbrand/web/home/viewlistproductbycate.do?cateid=${cate.id}" class="nav-item nav-link">${cate.name}</a>
                        </c:forEach>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="col-lg-9">
                <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                    <a href="/localbrand/web/home/index.do" class="text-decoration-none d-block d-lg-none">
                        <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                    </a>
                    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="navbar-nav mr-auto py-0">
                            <c:if test="${sessionScope.brandId != null}">
                            <a 
                            	href="/localbrand/web/brandhome/index.do" 
                            	class="nav-item nav-link">Brand Home</a>
                            	</c:if>
                            <c:if test="${requestScope.collectionId != null}">
                            	<a href=""
                            		class="nav-item nav-link active">Collections</a>
                            </c:if>
                            <c:if test="${sessionScope.brandId != null}">
                            	<a href="/localbrand/web/brandhome/latest.do"
                            		class="nav-item nav-link">Latest Products</a>
                            	<a href="/localbrand/web/brandhome/ranking.do" 
                            		class="nav-item nav-link">Ranking</a>
                            </c:if>
                        </div>
                        <c:choose>
                        	<c:when test="${sessionScope.user == null}">
                        		<div class="navbar-nav ml-auto py-0">
                            		<button type="button" class="nav-item nav-link btn" data-toggle="modal" data-target="#loginModal">
                             	   		Login
                           	 		</button>
                            		<a href="/localbrand/web/register/index.do" class="nav-item nav-link">Register</a>
                        		</div>
                        	</c:when>
                        	<c:otherwise>
                        		<div class="navbar-nav ml-auto py-0">
                        			<a href="/localbrand/web/order/index.do?orderstatus=" class="nav-item nav-link">"${sessionScope.user.getName()}"</a>
                        			<a href="/localbrand/web/home/logout.do" class="nav-item nav-link">Log out</a>
                        		</div>
                        	</c:otherwise>
                        </c:choose>
                    </div>
                </nav>
            </div>
        </div>
    </div>
    <!-- Navbar End -->


    <!-- Login Modal -->
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content" style="background-image: url('https://mdbootstrap.com/img/Photos/Others/images/76.jpg');">
                <div class="modal-header">
                    <h5 class="modal-title m-auto" id="exampleModalLongTitle">Welcome</h5>
                </div>
                <form action="/localbrand/web/home/login.do" method="POST">
                <div class="modal-body">
                    <div class="d-flex justify-content-between">
                        <div class="row">
                            <div class="col-md-12 form-group">
                                <label>Username</label>
                                <input class="form-control" type="text" name="txtusername" value="" placeholder="Username" required>
                            </div>
                            <div class="col-md-12 form-group">
                                <label>Password</label>
                                <input class="form-control" type="password" name="txtpassword" value="" placeholder="Password" required>
                            </div>
                            <div class="col-md-12 form-group">
                                <a href="">Forgot password?</a>
                            </div>
                            <div class="col-md-12 form-group">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" name="savelogin" class="custom-control-input" id="newaccount">
                                    <label class="custom-control-label" for="newaccount">Save me</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer border-secondary bg-transparent">
                        <button name="action" value="login" class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3">Login</button>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
<script>
        $('#myModal').on('shown.bs.modal', function() {
            $('#myInput').trigger('focus')
        })
    </script>
