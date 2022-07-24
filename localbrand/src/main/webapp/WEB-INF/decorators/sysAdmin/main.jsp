<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/bootstrap/css/bootstrap.min.css">
<link
	href="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/fonts/circular-std/style.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/libs/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/charts/chartist-bundle/chartist.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/charts/morris-bundle/morris.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/fonts/material-design-iconic-font/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/charts/c3charts/c3.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/fonts/flag-icon-css/flag-icon.min.css">
<title>Concept - Bootstrap 4 sysAdmin Dashboard Template</title>
</head>

<body>
	<!-- Optional JavaScript -->
	<!-- jquery 3.3.1 -->
	<script
		src="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/jquery/jquery-3.3.1.min.js"></script>
	<!-- bootstap bundle js -->
	<script
		src="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
	<!-- slimscroll js -->
	<script
		src="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/slimscroll/jquery.slimscroll.js"></script>
	<!-- main js -->
	<script
		src="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/libs/js/main-js.js"></script>


	<!-- chart chartist js -->
	<script
		src="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/charts/chartist-bundle/chartist.min.js"></script>
	<!-- sparkline js -->
	<script
		src="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/charts/sparkline/jquery.sparkline.js"></script>

	<!-- morris js -->
	<script
		src="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/charts/morris-bundle/raphael.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/charts/morris-bundle/morris.js"></script>
	<!-- chart c3 js -->
	<script
		src="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/charts/c3charts/c3.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/charts/c3charts/d3-5.4.0.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/teamplate/sysAdmin/assets/vendor/charts/c3charts/C3chartjs.js"></script>

	<div
		class="${!controller.equals('/login')?'':''}">
		<c:if test="${!controller.equals('/login')}">
			<jsp:include page="/WEB-INF/decorators/sysAdmin/header.jsp" />

		</c:if>
		<div class="${!controller.equals('/login')?'dashboard-wrapper':''}">
			<div class="${!controller.equals('/login')?'dashboard-ecommerce':''}">
				<jsp:include
					page="/WEB-INF/views/${page}/${controller}/${action}.jsp" />

				<jsp:include page="/WEB-INF/decorators/sysAdmin/footer.jsp" />
			</div>
		</div>
	</div>
</body>

</html>