<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.gradient-custom {
	/* fallback for old browsers */
	background: #6a11cb;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1),
		rgba(37, 117, 252, 1));
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to right, rgba(106, 17, 203, 1),
		rgba(37, 117, 252, 1))
}
</style>
<section class="vh-100 gradient-custom">
	<div class="container py-4 h-100">
		<div class="row d-flex justify-content-center align-items-center ">
			<div class="col-12 col-md-8 col-lg-6 col-xl-5">
				<div class="card bg-dark text-white" style="border-radius: 1rem;">
					<div class="card-body p-5 text-center">
						<div class="mb-md-5 mt-md-4 pb-3">

							<h2 class="fw-bold mb-2 text-uppercase text-primary">Login</h2>
							<p class="text-white-50 mb-5">Please enter your login and
								password!</p>
							<form method="post" action="/localbrand/admin/login/login.do">
								<div class="form-outline form-white mb-4">
									<input name="username" value="${username==null?'':username}" type="txtUserName" placeholder="User Name"
										class="form-control form-control-lg" />
								</div>

								<div class="form-outline form-white mb-4">
									<input name="password" type="password" placeholder="Password"
										class="form-control form-control-lg" />
								</div>
								<button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>
								
							</form>
							<div style="font-style: italic" class="mt-3 text-danger">${mess}</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>