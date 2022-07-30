<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<body>
     <div class="dashboard-main-wrapper">
        <div class="dashboard-content">
                    <div class="row">
                        <div class="col-12">
                            <div class="page-header">
                                <h2 class="pageheader-title">E-commerce Collection Management </h2>
                                <p class="pageheader-text">Manage Your Collection.</p>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">E-commerce Collection Management</a></li>
                                            <li class="breadcrumb-item active" aria-current="page">${collection.getName()}</li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <form action="/localbrand/admin/collection/update.do" method="post" class="col-12 mt-2">
                        	<div class="form-group">
                        		<input name="txtId" type="hidden" value="${collection.getId()}">
                        	</div>
                            <div class="form-group">
                                <label class="col-form-label">Collection Name</label>
                                <input name="txtName" type="text" class="form-control" value="${collection.getName()}">
                            </div>
                            <div class="form-group">
                                <label class="col-form-label">Collection Banner</label>
                                <input name="txtImageUrl" type="text" class="form-control" value="${collection.getImageUrl()}">
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="txtDescription" class="form-control" rows="3">${collection.getDescription()}</textarea>
                            </div>
                            <p class="text-right">
                                <button type="submit" class="btn btn-space btn-primary">Save</button>
                            </p>
                        </form>
                    </div>
                    <div class="card">
                        <div class=" card-header">
                            <h5 class="d-inline mr-3">Product Management</h5>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".create-product-modal"><i class="fas fa-plus-square"></i></button>

                            <div class="card-body p-0 mt-2">
                                <div class="table-responsive">
                                    <div class="collapse multi-collapse show">
                                        <table class="table ">
                                            <thead class="bg-light ">
                                                <tr class="border-0 ">
                                                    <th class="border-0 ">#</th>
                                                    <th class="border-0 ">Product ID</th>
                                                    <th class="border-0 ">Product Image</th>
                                                    <th class="border-0 ">Product Name</th>
                                                    <th class="border-0 ">Discount</th>
                                                    <th class="border-0 ">Price</th>
                                                    <th class="border-0 h-50">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach var="collectionDetail" items="${collection.getCollectionDetailList()}" varStatus="loop">
                                                <tr>
                                                    <td>${loop.count}</td>
                                                    <td>${collectionDetail.getProduct().getId()}</td>
                                                    <td><img style="height: 150px; width: 120px"
                                                    	src="${collectionDetail.getProduct().getImgMaster()}"></td>
                                                    <td>${collectionDetail.getProduct().getName()}</td>
                                                    <td>${collectionDetail.getProduct().getDiscount()}</td>
                                                    <td>${collectionDetail.getProduct().getPrice()}</td>
                                                    <td>
                                                        <a href="/localbrand/admin/collection/deleteDetail.do?collectionId=${collection.getId()}&productId=${collectionDetail.getProduct().getId()}" class="btn btn-outline-light fas fa-trash"></a>
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
                            <div class="card">
                                <h5 class="card-header">Add Product To Collection</h5>
                                <div class="card-body">
                                    <table class="table ">
                                        <thead class="bg-light ">
                                            <tr class="border-0 ">
                                                <th class="border-0 ">#</th>
                                                <th class="border-0 ">Product ID</th>
                                                <th class="border-0 ">Product Image</th>
                                                <th class="border-0 ">Product Name</th>
                                                <th class="border-0 ">Price</th>
                                                <th class="border-0 h-50">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="product" items="${requestScope.productsNotInCollection}" varStatus="loop">
                                            <tr>
                                                <td>${loop.count}</td>
                                                <td>${product.getId()}</td>
                                                <td><img style="height: 150px; width: 120px" 
                                                	src="${product.getImgMaster()}"></td>
                                                <td>${product.getName()}</td>
                                                <td>${product.getPrice()}</td>
                                                <td>
                                                    <a href="/localbrand/admin/collection/createDetail.do?collectionId=${collection.getId()}&productId=${product.getId()}" class="btn btn-outline-light fas fa-plus-circle" data-toggle="modal " data-target=".orderid1 "></a>
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
</body>