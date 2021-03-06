<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="nav-left-sidebar sidebar-dark">
	<div class="menu-list">
		<nav class="navbar navbar-expand-lg navbar-light">
			<a class="d-xl-none d-lg-none" href="/localbrand/admin/home/index.do">Dashboard</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav flex-column">
					<li class="nav-divider">Menu</li>
					<li class="nav-item "><a class="nav-link active"
						href="/localbrand/admin/home/index.do"><i
							class="fa fa-fw fa-user-circle"></i>Home</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/localbrand/admin/chart/index.do" data-toggle="collapse"
						aria-expanded="false" data-target="#submenu-3"
						aria-controls="submenu-3"><i class="fas fa-fw fa-chart-pie"></i>Shop
							Management</a>
						<div id="submenu-3" class="collapse submenu" style="">
							<ul class="nav flex-column">
								<li class="nav-item"><a class="nav-link"
									href="/localbrand/admin/chart/index.do">Chart</a></li>
								<li class="nav-item"><a class="nav-link"
									href="/localbrand/admin/ordered/index.do">Ordered</a></li>
							</ul>
						</div></li>
					<li class="nav-item"><a class="nav-link"
						href="/localbrand/admin/product/index.do"><i
							class="fas fa-fw fa-columns"></i>Product Management</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/localbrand/admin/collection/index.do"><i
							class="fa fa-fw fa-rocket"></i>Collection Management</a></li>

					<li class="nav-item "><a class="nav-link" href="#"
						data-toggle="collapse" aria-expanded="false"
						data-target="#submenu-4" aria-controls="submenu-4"><i
							class="fab fa-fw fa-wpforms"></i>Store Management</a>
						<div id="submenu-4" class="collapse submenu" style="">
							<ul class="nav flex-column">
								<li class="nav-item"><a class="nav-link"
									href="/localbrand/admin/member/index.do">Employee
										Management</a></li>
								<li class="nav-item"><a class="nav-link"
									href="/localbrand/admin/information/index.do">Public
										Information</a></li>
							</ul>
						</div></li>
				</ul>
			</div>
		</nav>
	</div>
</div>