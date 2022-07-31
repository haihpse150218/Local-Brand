<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
 <div class="dashboard-main-wrapper">
        <div class="dashboard-content">
            <div class="dashboard-ecommerce">
                <div class="container-fluid dashboard-content">
                    <div class="row">
                        <div class="col-12">
                            <div class="page-header">
                                <h2 class="pageheader-title">E-commerce Product Management </h2>
                                <p class="pageheader-text">Manage Your Product.</p>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">E-coommerce Product Management</a></li>
                                            <li class="breadcrumb-item active" aria-current="page">${requestScope.product.getName()}</li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <h5 class="card-header">Information Of Product</h5>
                        <div class="card-body">
                            <form action="/localbrand/admin/product/edit.do" method="post">
                            	<div class="form-group">
                                    <input name="txtId" type="hidden" class="form-control"
                                    	value="${requestScope.product.getId()}">
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Product Name</label>
                                    <input name="txtName" type="text" class="form-control"
                                    	value="${requestScope.product.getName()}">
                                </div>
                                <div class="form-row">
                                    <div class="col-md-3 mb-3">
                                        <label>Price</label>
                                        <input name="txtPrice" id="product-price" type="number" class="form-control" placeholder="Numbers"
                                        	value="${requestScope.product.getPrice()}">
                                    </div>
                                    <div class="col-md-6 mb-6">
                                        <label>Category</label>
                                        <select name="txtCategory" class="form-control" value="">
                                            <c:forEach var="category" items="${requestScope.categoryList}" varStatus="loop">
                                            	<option ${(requestScope.product.getBrandId().getId() == category.getBrandCategoryPK().getBrandId())
                                            			&& (requestScope.product.getCateId().getId()==category.getBrandCategoryPK().getCateId())
                                            			?'selected':''}
                                            		value="${category.getBrandCategoryPK().getCateId()}">
                                            		${category.getName()}
                                            	</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label>Status</label>
                                        <select name="txtStatus" class="form-control">
                                        	<option ${requestScope.product.getStatus()=='New'?'selected':''}>New</option>
                                            <option ${requestScope.product.getStatus()=='Active'?'selected':''}>Active</option>
                                            <option ${requestScope.product.getStatus()=='Disable'?'selected':''}>Disable</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                	<label class="col-form-label">Image</label>
                                    <input name="txtImgMaster" type="text" class="form-control" required
                                    	value="${requestScope.product.getImgMaster()}">
                                </div>

                                <div class="form-row">
                                    <div class="col-md-6 mb-3">
                                        <label>Size</label>
                                        <select name="txtSize" class="form-control">
                                            <option ${requestScope.product.getSize()=='XS'?'selected':''}>XS</option>
                                            <option ${requestScope.product.getSize()=='S'?'selected':''}>S</option>
                                            <option ${requestScope.product.getSize()=='M'?'selected':''}>M</option>
                                            <option ${requestScope.product.getSize()=='L'?'selected':''}>L</option>
                                            <option ${requestScope.product.getSize()=='XL'?'selected':''}>XL</option>
                                            <option ${requestScope.product.getSize()=='XXL'?'selected':''}>XXL</option>
                                        </select>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label>Color</label>
                                        <input name="txtColor" type="text" class="form-control" placeholder="State" required
                                        	value="${requestScope.product.getColor()}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="txtDescription" class="form-control" rows="3">${requestScope.product.getDescription()}</textarea>
                                </div>
                                <p class="text-right">
                                    <button type="submit" class="btn btn-space btn-primary">Submit</button>
                                </p>
                            </form>
                        </div>
                    </div>
                    <div class="card">
                        <div class=" card-header">
                            <h5 class="">Dependence Management</h5>
                            <div class="mb-2">
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".create-product-modal">Add Dependence</button>
                            </div>
                            <div class="card-body p-0">
                                <div class="table-responsive">
                                    <div class="collapse multi-collapse show">
                                        <table class="table ">
                                            <thead class="bg-light ">
                                                <tr class="border-0 ">
                                                    <th class="border-0 ">#</th>
                                                    <th class="border-0 ">Product ID</th>
                                                    <th class="border-0 ">Product Image</th>
                                                    <th class="border-0 ">Color</th>
                                                    <th class="border-0 ">Size</th>
                                                    <th class="border-0 ">Price</th>
                                                    <th class="border-0 h-50">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach var="product" items="${requestScope.dependencies}" varStatus="loop">
                                                <tr>
                                                    <td>${loop.count}</td>
                                                    <td>${product.getId()}</td>
                                                    <td><img style="height: 150px; width: 120px"
                                                    	src="${product.getImgChild()}"></td>
                                                    <td>${product.getColor()}</td>
                                                    <td>${product.getSize()}</td>
                                                    <td>${product.getPrice()}</td>
                                                    <td>
                                                        <a href="/localbrand/admin/product/delete.do?productId=${product.getId()}" class="btn btn-outline-light fas fa-trash"></a>
                                                    </td>
                                                </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade create-product-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <h5 class="card-header">Create Dependence</h5>
                            <div class="card-body">
                                <form action="/localbrand/admin/product/addDependency.do" method="post">
                                    <div class='form-row'>
                                        <div class='col-md-3 mb-3'>
                                            <label>Price</label>
                                            <input type='number' name='txtPrice' class='form-control' placeholder='Price'>
                                        </div>
                                        <div class='col-md-6 mb-3'>
                                            <label>Size</label>
                                            <select name='txtSize' class='form-control' required>
                                            	<option>XS</option>
                                                <option>S</option>
                                                <option>M</option>
                                                <option>L</option>
                                                <option>XL</option>
                                                <option>XXL</option>
                                            </select>
                                        </div>
                                        <div class='col-md-3 mb-3'>
                                            <label for='validationTooltip04'>Color</label>
                                            <input type='text' name='txtColor' class='form-control' placeholder='Red...' required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                    	<input name="txtParentId" type="hidden" class="form-control" value="${requestScope.product.getId()}">
                                	</div>
                                   	<div class="form-group">
                                		<label class="col-form-label">Image</label>
                                    	<input name="txtImgChild" type="text" class="form-control">
                                	</div>
                                    <p class="text-right">
                                        <button type="submit" class="btn btn-space btn-primary">Submit</button>
                                    </p>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>

            </div>
            <!-- ============================================================== -->
            <!-- footer -->
            <!-- ============================================================== -->

            <!-- ============================================================== -->
            <!-- end footer -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- end wrapper  -->
        <!-- ============================================================== -->
    </div>