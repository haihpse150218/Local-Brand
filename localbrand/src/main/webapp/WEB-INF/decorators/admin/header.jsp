<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="dashboard-header">
	<nav class="navbar navbar-expand-lg bg-white fixed-top">
		<a class="navbar-brand" href="/localbrand/admin">LocalBrand - ${sessionScope.admin.getBrandId().getName()}</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse " id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto navbar-right-top">
				<li class="nav-item dropdown nav-user"><a
					class="nav-link nav-user-img" href="#" id="navbarDropdownMenuLink2"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img
						src="${pageContext.request.contextPath}/teamplate/admin/assets/images/avatar-1.jpg" alt=""
						class="user-avatar-md rounded-circle"></a>
					<div class="dropdown-menu dropdown-menu-right nav-user-dropdown"
						aria-labelledby="navbarDropdownMenuLink2">
						<div class="nav-user-info">
							<h5 class="mb-0 text-white nav-user-name">${admin.name}</h5>
							<span class="status"></span><span class="ml-2">
							<c:if test="${admin.status==1}">
							Active
							</c:if>
							<c:if test="${admin.status==0}">
							Disabled
							</c:if>
							</span>
						</div>
						<a class="dropdown-item" href="#"><i class="fas fa-user mr-2"></i>Account</a>
						<a class="dropdown-item" href="#"><i class="fas fa-cog mr-2"></i>Setting</a>
						<a class="dropdown-item" href="/localbrand/admin/home/logout.do"><i
							class="fas fa-power-off mr-2"></i>Logout</a>
					</div></li>
					
			</ul>
		</div>
	</nav>
</div>