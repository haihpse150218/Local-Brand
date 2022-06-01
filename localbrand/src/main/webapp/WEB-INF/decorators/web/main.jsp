<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %> 

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>EShopper - Bootstrap Shop Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    
    <link href='<c:url value="teamplate/web//teamplate/web/img/favicon.ico" />' rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    
    <link href='<c:url value="teamplate/web/lib/owlcarousel/assets/owl.carousel.min.css" />' rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href='<c:url value="teamplate/web/css/style.css" />' rel="stylesheet">
</head>

<body>
    
    <jsp:include page="/WEB-INF/decorators/web/header.jsp" />
    
    <!-- Page Header Start -->
<!--     <div class="container-fluid bg-secondary mb-5">
        <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
            <h1 class="font-weight-semi-bold text-uppercase mb-3">Our Shop</h1>
            <div class="d-inline-flex">
                <p class="m-0"><a href="">Home</a></p>
                <p class="m-0 px-2">-</p>
                <p class="m-0">Shop</p>
            </div>
        </div>
    </div> -->
    <!-- Page Header End -->


    <!-- Shop Start -->
    <jsp:include page="/WEB-INF/views/${page}/${controller}/${action}.jsp" />
    <!-- Shop End -->


    <!-- Footer Start -->
   	<jsp:include page="/WEB-INF/decorators/web/footer.jsp" />
    <!-- Back to Top -->
    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    
    <script src='<c:url value="teamplate/web/lib/easing/easing.min.js" />'></script>
    
    <script src='<c:url value="teamplate/web/lib/owlcarousel/owl.carousel.min.js" />'></script>

    <!-- Contact Javascript File -->
    
    <script src='<c:url value="teamplate/web/mail/jqBootstrapValidation.min.js" />'></script>
    
    <script src='<c:url value="teamplate/web/mail/contact.js" />'></script>

    <!-- Template Javascript -->
    
    <script src='<c:url value="teamplate/js/main.js" />'></script>
</body>

</html>