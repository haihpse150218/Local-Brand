<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<body>
    <div class="dashboard-main-wrapper">
        <div class="dashboard-content">
                <!-- recent orders  -->
                <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-12">
                            <div class="page-header">
                                <p class="pageheader-text">Manager Your Collections.</p>
                                <div class="page-breadcrumb">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">E-commerce</a></li>
                                            <li class="breadcrumb-item active" aria-current="page">E-Commerce Collections Management</li>
                                        </ol>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="card">
                            <div class=" card-header">
                                <h5 class="">Collections Management</h5>
                                <div class="row">
                                    <form action="" class="col-4">
                                        <div class="input-group">
                                            <input type="text" class="form-control" placeholder="Search by id">
                                            <div class="input-group-append">
                                                <button class="input-group-text bg-transparent text-primary">
                                                    <i class="fa fa-search"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                    <div class="col-5">
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".create-product-modal">
                                        	<i class="fas fa-plus-square"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body p-0">
                                <div class="table-responsive">
                                    <div class="collapse multi-collapse show">
                                        <table class="table ">
                                            <thead class="bg-light ">
                                                <tr class="border-0 ">
                                                    <th class="border-0 ">#</th>
                                                    <th class="border-0 ">Collection ID</th>
                                                    <th class="border-0 ">Collection Name</th>
                                                    <th class="border-0 ">Description</th>
                                                    <th class="border-0 h-50">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!-- vong for order detail-->
                                                <c:forEach var="collection" items="${requestScope.listCollection }" varStatus="loop">
                                                
                                                	<tr>
                                                	    <td>${loop.count}</td>
                                                	    <td>${collection.getId()}</td>
                                               	    	<td>${collection.getName()}</td>
                                                   	 	<td>${collection.getDescription()}</td>
                                                    	<td>
                                                        	<a href="/localbrand/admin/collection/info.do?id=${collection.getId()}" class="btn btn-outline-light far fa-edit"></a>
                                                        	<a href="/localbrand/admin/collection/delete.do?id=${collection.getId()}" class="btn btn-outline-light fas fa-trash"
                                                        		onclick="javascript:return confirm('Delete collection ${collection.getName()}?')"></a>
                                                    	</td>
                                                	</tr>
                                                <!-- vong for order detail-->
                                                </c:forEach>
                                                <!-- order detail end -->
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                <!-- Modal Order Detail -->

                <div class="modal fade create-product-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="card">
                                <h5 class="card-header">Create Collection</h5>
                                <div class="card-body">
                                    <form action="/localbrand/admin/collection/create.do" method="post">
                                        <div class="form-group">
                                            <label class="col-form-label">Collection Name</label>
                                            <input name="txtName" type="text" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-form-label">Collection Banner</label>
                                            <input name="txtImageUrl" type="text" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea name="txtDescription" class="form-control" rows="3" required></textarea>
                                        </div>
                                        <p class="text-right">
                                            <button type="button" class="btn btn-space" data-dismiss="modal">Cancel</button>
                                            <button type="submit" class="btn btn-space btn-primary">Save</button>
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
    <!-- ============================================================== -->
    <!-- end main wrapper  -->
</body>