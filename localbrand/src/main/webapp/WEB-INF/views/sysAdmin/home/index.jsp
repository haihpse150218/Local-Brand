<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var = "brandList" scope = "request" value = "${requestScope.brandList}"/>
<c:set var = "brandAcc" scope = "request" value = "${requestScope.currentInput}"/>
<c:set var = "brandAccountList" scope = "request" value = "${requestScope.brandAccountList}"/>
	<div class="dashboard-ecommerce">
		<div class="container-fluid dashboard-content ">
			<!-- ============================================================== -->
			<!-- pageheader  -->
			<!-- ============================================================== -->
			<div class="row">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="page-header">
						<h2 class="pageheader-title">E-commerce Dashboard Template</h2>
						<p class="pageheader-text">Nulla euismod urna eros, sit amet
							scelerisque torton lectus vel mauris facilisis faucibus at enim
							quis massa lobortis rutrum.</p>
						<div class="page-breadcrumb">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									
								</ol>
							</nav>
						</div>
					</div>
				</div>
			</div>
			<!-- ============================================================== -->
			<!-- end pageheader  -->
			<!-- ============================================================== -->
			
			<div class="btn-toolbar mb-1 ">
				<a href="https://login.mailchimp.com/?_ga=2.198371503.1267660876.1658636805-1543649915.1657084854" class="open-button btn btn-primary">Send Contact Email</a>			
			</div>
			<button class="open-button btn btn-primary" onclick="openForm1()">Create new brand</button>
				<div class="form-popup" id="myForm1">
  				<form action="/localbrand/sysAdmin/brand/create.do"" class="form-container" method="POST">

    				<label for="name"><b> Brand Name</b></label>
    				<input type="text" placeholder="Enter Brand Name" name="brandName" required>

    				<label for="description"><b>Description</b></label>
    				<input type="text" placeholder="Enter Description" name="description" required>
    				
    				<label for="logo"><b>Logo</b></label>
    				<input type="text" placeholder="Enter Logo Image Link" name="logo" required>
    				
    				<label for="banner"><b>Banner</b></label>
    				<input type="text" placeholder="Enter Banner Image Link" name="banner" required>
    				
    				<%--<label for="stars"><b>Stars</b></label>
    				<input type="number" step="0.1" placeholder="Enter Stars (default : 3.5)" name="stars" value="3.5">
    				--%>
					<input type="hidden" name="stars" value="3.5">
					<label for="status"><b>Status</b></label>
    					<select name="status" id="status">
 							<option value="Active" >Active</option>
  							<option value="Disabled" >Disabled</option>
						</select>
    				<button type="submit" class="btn">Create</button>
    				<button type="button" class="btn cancel" onclick="closeForm1()">Close</button>
  				</form>
				</div>
			  <table class ="table" border="0">
                    <thead>
                        <tr>
                            <th>Brand ID</th>
                            <th>Brand Name</th>
                            <th>Logo</th>
                            <th>Banner</th>
                            <th>Description</th>
                            <th>Create Date</th>
                            <th>Stars</th>
                            <th>Status</th>
                            <th></th>
                        </tr>
                    </thead>
                    
                      <c:forEach items="${brandList}" var="list">
                        <form action="/localbrand/sysAdmin/brand/update.do" method="GET">
                        <tbody>
                            <tr> 
                                <td>${list.id}
                                    <input type="hidden" name="brandId" value="${list.id}"/>
                                </td>
                                <td>${list.name}</td>
                                <td><img style="max-width: 100px; max-height: 100px" src="${list.logo}"/></td>
                                <td><img style="max-width: 100px; max-height: 100px" src="${list.banner}"/></td>
                                <td>${list.description}</td>
                                <td><fmt:formatDate value="${list.createDate}"
											pattern="dd/MM/yyyy" /></td>
                                <td>${list.stars}</td>
                                <td><c:if test="${list.status == 'Active'}">
                                <select name="status" id="status">
 									 	<option value="Active" selected>Active</option>
  										<option value="Disabled">Disabled</option>
								</select>
								</c:if>
								<c:if test="${list.status == 'Disabled'}">
                                <select name="status" id="status">
 									 	<option value="Active" >Active</option>
  										<option value="Disabled" selected>Disabled</option>
								</select>
								</c:if>
								</td>
                                <td><input type="submit" class="btn btn-primary" name="action" value="Update"/></td>                               
                            </tr>
                        </tbody>
                        </form>
                      </c:forEach>
                
            </table>
            <button class="open-button btn btn-primary" onclick="openForm2()">Create new brand account</button>

				<div class="form-popup" id="myForm2">
  				<form action="/localbrand/sysAdmin/brandAccount/create.do"" class="form-container" method="POST">

    				<label for="name"><b>Name</b></label>
    				<input type="text" placeholder="Enter Name" name="name" value="${brandAcc.name}" required>

    				<label for="username"><b>Username</b></label>
    				<input type="text" placeholder="Enter Username" name="username" value="${brandAcc.username}" required>
    				
    				<label for="password"><b>Password</b></label>
    				<input type="password" placeholder="Enter Password" name="password" value="${brandAcc.password}" required>
    				
    				<label for="cPassword"><b>Confirm Password</b></label>
    				<input type="password" placeholder="Confirm Password" name="cPassword" required>
    				<input type="hidden" name="role" value="True">
    				<%--
    				<label for="role"><b>Role</b></label>
    					<select name="role" id="role">
 							<option value="True" >Admin</option>
  							<option value="False" selected>Employee</option>
						</select>--%>
    				<br>
    				<label for="brandId"><b>Brand Name:</b></label>
    				<select id="brandId" name="brandId">
    					<c:forEach items="${brandList}" var="list">
    					<c:if test="${list.status =='Active'}">
    						<option value="${list.id}">${list.name}</option>
    					</c:if>
    					</c:forEach>		
					</select>
    				<br>
    				<label for="status"><b>Status</b></label>
    					<select name="status" id="status">
 							<option value="1" >Active</option>
  							<option value="0" >Disabled</option>
						</select>
					<label style="font-style: italic" class="mt-3 text-danger">${mess}</label>	
    				<button type="submit" class="btn">Create</button>
    				<button type="button" class="btn cancel" onclick="closeForm2()">Close</button>
  				</form>
  				</div>
            <table class ="table" border="0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Role</th>
                            <th>Brand ID</th>
                            <th>Brand Name</th>
                            <th>Status</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${brandAccountList}" var="list">
                        <form action="/localbrand/sysAdmin/brandAccount/update.do" method="GET">
                            <tr> 
                                <td>${list.id}
                                    <input type="hidden" name="brandAccountId" value="${list.id}"/>
                                </td>
                                <td>${list.name}</td>
                                <td>${list.username}</td>
                                <td>${list.password}</td>
                                <c:if test="${list.role eq true}">
                                	<td>Admin</td>
                                </c:if>
                                <c:if test="${list.role eq false}">
                                	<td>Employee</td>
                                </c:if>
                                <td>${list.brandId.id}</td> 
                                 <td>${list.brandId.name}</td> 
                                <td>
                                <c:if test="${list.status == 2}">
                                <select name="status" id="status">
 									 	<option value="1" >Active</option>
  										<option value="0">Disabled</option>
  										<option value="2" selected>Removed</option>
								</select>
								</c:if>
                                <c:if test="${list.status == 1}">
                                <select name="status" id="status">
 									 	<option value="1" selected>Active</option>
  										<option value="0">Disabled</option>
  										<option value="2">Removed</option>
								</select>
								</c:if>
								<c:if test="${list.status == 0}">
                                <select name="status" id="status">
 									 	<option value="1" >Active</option>
  										<option value="0" selected>Disable</option>
  										<option value="2">Removed</option>
								</select>
								</c:if>
								</td>
                                <td><input type="submit" class="btn btn-primary" name="action" value="Update"/></td>
                                <td><a href="/localbrand/sysAdmin/brandAccount/update.do?reset=true&brandAccountId=${list.id}&password=${list.password}" class="btn btn-primary">Reset password</a></td>                               
                            </tr>
                        </form>
                      </c:forEach>
                </tbody>
            </table>
			
</div>
</div>
<c:if test="${not empty mess}">
<style>
#myForm2 {
  display: block;
  position: fixed;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}
</style>
</c:if>
<c:if test="${empty mess}">
<style>
#myForm2 {
  display: none;
  position: fixed;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}
</style>
</c:if>

<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

/* Button used to open the contact form - fixed at the bottom of the page */
.open-button {
  background-color: #1E90FF;
  color: white;
  border: none;
}

/* The popup form - hidden by default */
#myForm1 {
  display: none;
  position: fixed;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}

/* Add styles to the form container */
.form-container {
  max-width: 300px;
  padding: 10px;
  background-color: white;
}

/* Full-width input fields */
.form-container input[type=text], .form-container input[type=password], .form-container input[type=number] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
}

/* When the inputs get focus, do something */
.form-container input[type=text]:focus, .form-container input[type=password]:focus, .form-container input[type=number]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit/login button */
.form-container .btn {
  background-color: #04AA6D;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom:10px;
  opacity: 0.8;
}

/* Add a red background color to the cancel button */
.form-container .cancel {
  background-color: red;
}

/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}
</style>
<script>
function openForm1() {
  	document.getElementById("myForm1").style.display = "block";
  	document.getElementById("myForm2").style.display = "none";
}

function closeForm1() {
  	document.getElementById("myForm1").style.display = "none";
}
function openForm2() {
	document.getElementById("myForm2").style.display = "block";
	document.getElementById("myForm1").style.display = "none";
}

function closeForm2() {
	  document.getElementById("myForm2").style.display = "none";
}
</script>


