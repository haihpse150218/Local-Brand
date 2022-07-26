<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="">
 	<div class="dashboard-main-wrapper">
        <div class="dashboard-content container-fluid">
            <div class=" card-header">
                <h5 class="">Public Infomation</h5>
                <button class="btn bg-primary" type="button" onclick="return OpenModal(-1);" data-toggle="modal" data-target="#Information-Modal">
                        Change Information</i> 
                    </button>
            </div>
            <div class="mb-3 container-fluid">
                <div class="row h-100" style="background-image: url('https://mdbootstrap.com/img/Photos/Others/images/76.jpg');min-height: 200px">
                    <div class="col-4 align-items-center text-center p-3 py-5">
                        <img class="img-responsive" width="200px" height="200px" src="${requestScope.brand.getLogo() }">
                    </div>
                    <div class="col-8 d-flex flex-column align-items-center justify-content-center">
                        <h1 class="font-weight-semi-bold text-uppercase mb-3">${requestScope.brand.getName() }</h1>
                    </div>
                </div>
            </div>
            <div class="container-fluid text-dark" style="background-color: #ecafaa ">
                <div class="row px-xl-5 pt-5">
                    <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">

                        <a href="" class="text-decoration-none">
                            <h1 class="mb-4 display-5 font-weight-semi-bold">${requestScope.brand.getName()}</h1>
                        </a>
                        <p>${requestScope.brand.getDescription() }</p>

                    </div>
                    <div class="col-lg-8 col-md-12">
                        <div class=" mb-5">
                            <div id="map" style="height: 50px ;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <div class="modal fade bd-example-modal-lg" id="Information-Modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="window-Text">Edit Information</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="/localbrand/admin/information/update.do" id="Information-action" method="post">
                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group col-4">
                                <label for="Information-id" id="id-label" class="col-form-label">ID :</label>
                                <input type="text" name="txtId" class="form-control" id="Information-id" readonly="true"
                                	value="${requestScope.brand.getId()}">
                            </div>
                            <div class="form-group col-8">
                                <label for="Information-brandname" class="col-form-label">Brand Name :</label>
                                <input type="text" name="txtBrandName" class="form-control" id="Information-brandname" required="true"
                                	value="${requestScope.brand.getName()}">
                            </div>
                        </div>
                        <div class="row">
                            <div class='form-group col-6'>
                                <label class='col-form-label'>Logo :</label>
                                <input type='text' name='txtLogo' class='form-control' required="true"
                                	value="${requestScope.brand.getLogo()}">
                            </div>
                            <div class='form-group col-6'>
                                <label class='col-form-label'>Banner :</label>
                                <input type='text' name='txtBanner' class='form-control' required="true"
                                	value="${requestScope.brand.getBanner()}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Information-description">Description :</label>
                            <textarea name="txtDescription" class="form-control" id="Information-description" rows="3">${requestScope.brand.getDescription()}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="Information-status" class="col-form-label">Status :</label>
                            <select class="form-control" name="txtStatus" id="Information-status">
                                <option>Active</option>
                                <option>Closed</option>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>