<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<div class="container-fluid dashboard-content ">

	<style>
td {
	text-align: center;
}

th {
	text-align: center;
}
</style>
	<!-- ============================================================== -->
	<!-- pageheader  -->
	<!-- ============================================================== -->
	<div class="row">
		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			<div class="page-header">
				<h2 class="pageheader-title">E-commerce Employee Management</h2>
				<p class="pageheader-text">Manager your employyee.</p>
				<div class="page-breadcrumb">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#"
								class="breadcrumb-link">E-coommerce</a></li>
							<li class="breadcrumb-item active" aria-current="page">E-Commerce
								Employee Management</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<!-- ============================================================== -->
	<!-- end pageheader  -->

	<!-- recent orders  -->
	<!-- ============================================================== -->
	<div class="col-12">
		<div class="card">
			<div class=" card-header">
				<h5 class="">Employee</h5>
				<div class="row">
					<form action="" class="col-4">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Search by Name">
							<div class="input-group-append">
								<button class="input-group-text bg-transparent text-primary">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</form>
					<button class="btn bg-white" type="button"
						onclick="return OpenModal(-1);" data-toggle="modal"
						data-target="#Employee-Modal">
						<i class="fas fa-user-plus"></i>
					</button>
				</div>
			</div>
			<div class="card-body p-0">
				<div class="table-responsive">
					<table class="table employee-table">
						<thead class="bg-light">
							<tr class="border-0">
								<th class="border-0">#</th>
								<th class="border-0">ID</th>
								<th class="border-0">User Name</th>
								<th class="border-0">Name</th>
								<th class="border-0">Role</th>
								<th class="border-0">Status</th>
								<th class="border-0">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="member" items="${listMember}" varStatus="loop">
								<tr>
									<td>${loop.count}</td>
									<td>${member.id}</td>
									<td>${member.username}</td>
									<td>${member.name}</td>
									<td>${member.role?'Admin':'Employee'}</td>
									<td><span
										class="badge-dot ${member.status==1?'badge-success':'badge-danger'} mr-1"></span>
										${member.status==1?'active':'disable'}</td>
									<td><a href="#" class="btn btn-outline-light"
										onclick="return OpenModal(${member.id});" data-toggle="modal"
										data-target="#Employee-Modal"><i class="far fa-edit"></i></a>
										<a
										href="/localbrand/admin/member/resetpassword.do?memberId=${member.id}"
										class="btn btn-outline-light"> <i class="fas fa-key"></i>
									</a> <c:if test="${member.id != admin.id}">
											<a
												href="/localbrand/admin/member/remove.do?status=Remove&id=${member.id}"
												class="btn btn-outline-light"><i class="fas fa-trash"></i></a>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal Order Detail -->
	<!-- Chay vong for theo order -->

	<div class="modal fade" id="Employee-Modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="window-Text">Edit Information</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="/localbrand/admin/member/update.do"
					id="Employee-action" method="post">
					<div class="modal-body">
						<div class="form-group">
							<label for="Employee-id" id="id-label" class="col-form-label">ID
								:</label> <input type="text" name="txtId" class="form-control"
								id="Employee-id" readonly="true">
						</div>
						<div class="form-group">
							<label for="Employee-username" class="col-form-label">User
								Name :</label> <input type="text" name="txtUserName"
								class="form-control" id="Employee-username" required="true"
								disabled="true">
						</div>
						<div class="form-group">
							<label for="Employee-name" class="col-form-label">Name :</label>
							<input type="text" name="txtName" class="form-control"
								id="Employee-name" required="true">
						</div>
						<div class='form-group'>
							<label for='Employee-password' class='col-form-label'>Password
								:</label> <input type='text' name='txtPassword' class='form-control'
								value="****** " id='Employee-password' readonly required="true">
						</div>
						<div class="form-group">
							<label for="Employee-status" class="col-form-label">Status
								:</label> <select class="form-control" name="txtStatus"
								id="Employee-status">
								<option>Active</option>
								<option>Disable</option>
							</select>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<c:if test="${requestScope.mess!= null}">
		<script>
            alert("Error When Create new Employee: Username is exited!!! ");
            $(document).ready(function() {
                $('#Employee-Modal').modal('show');
            });
        </script>
	</c:if>
	<script>
        // kiem tra co thong bao warrning hay khong,neu co thi chay code nay

        // document.getElementById("Employee-Modal").classList.add("show");
        document.getElementById("Employee-Modal").removeAttribute('hidden');
        var OpenModal = function(value) {
            if (value == -1) {
                document.getElementById("id-label").setAttribute('hidden', 'true');
                document.getElementById("Employee-id").setAttribute('hidden', 'true');
                document.getElementById("Employee-id").value = "";
                document.getElementById("Employee-username").value = "";
                document.getElementById("Employee-username").removeAttribute('disabled');
                document.getElementById("Employee-name").value = "";
                document.getElementById("Employee-password").removeAttribute('readonly');
                document.getElementById("Employee-password").value = "";
                document.getElementById("Employee-status").getElementsByTagName("option")[0].selected = 'selected';
                document.getElementById("window-Text").innerHTML = "Creater Employee Acount";
                document.getElementById("Employee-action").action = "/localbrand/admin/member/insert.do";
            } else {
                document.getElementById("Employee-id").setAttribute('readonly', 'true');
                document.getElementById("Employee-id").removeAttribute('hidden');
                document.getElementById("id-label").removeAttribute('hidden');
                document.getElementById("Employee-password").setAttribute('readonly', 'true');
                var tr = document.getElementsByClassName("employee-table")[0].getElementsByTagName("tr");
                for (var i = 1; i < tr.length; i++) {
                    var id = tr[i].getElementsByTagName("td")[1];
                    var txtid = id.innerText;
                    if (value == parseInt(txtid)) {
                        var modalId = document.getElementById("Employee-id");
                        modalId.value = txtid;

                        var username = tr[i].getElementsByTagName("td")[2];
                        var txtusername = username.innerText;
                        var modalUserName = document.getElementById("Employee-username");
                        modalUserName.value = txtusername;
                        modalUserName.setAttribute('disabled', 'true');

                        var name = tr[i].getElementsByTagName("td")[3];
                        var txtname = name.textContent || name.innerText;
                        var modalName = document.getElementById("Employee-name");
                        modalName.value = txtname;

                        var status = tr[i].getElementsByTagName("td")[5];
                        var txtstatus = status.innerText;
                        var modalstatus = document.getElementById("Employee-status");
                        var status = modalstatus.getElementsByTagName("option");
                        for (var j = 0; j < status.length; j++) {
                            if (status[j].value === txtstatus) status[j].selected = 'selected';
                        }
                    }
                }
            }
            return false;
        }
    </script>
	<!-- Modal Order Detail -->
</div>