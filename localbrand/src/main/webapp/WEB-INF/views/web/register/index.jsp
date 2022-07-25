<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!-- Regislation Start -->
   <form action="/localbrand/web/register/register.do" method="post">
        <div class="row px-xl-5 mt-3">
            <div class="col-lg-8 col-md-6 col-sm-5  d-flex justify-content-center align-items-center" style="background-image: url('https://mdbootstrap.com/img/Photos/Others/images/76.jpg');">
                <div class="">
                    <h1 class="m-0 display-5 font-weight-semi-bold">Come with us <i class="fa fa-heart"></i></h1>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-7 rounded">
                <div class="mb-4 pl-3">
                    <h4 class="font-weight-semi-bold mb-4 d-flex justify-content-center align-items-center">Registration</h4>
                    <div class="row">
                        <div class="col-md-12 co form-group ">
                            <label>Full Name</label>
                            <input class="form-control" name="txtName" value="${requestScope.txtName}" type="text" placeholder="Enter your Name">
                        </div>
                        <div class="col-md-12 co form-group ">
                            <label>Username</label>
                            <input class="form-control" name="txtUserName" value="${requestScope.txtUserName}" type="text" placeholder="Enter your Username">
                        </div>
                        <div class="col-md-12 form-group">
                            <label>E-mail</label>
                            <label style="color: red; font-size: 13px;">${requestScope.ERROR_email}</label>
                            <input class="form-control" name="txtEmail" value="${requestScope.txtEmail}" type="text" placeholder="example@email.com" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <label>Password</label>
                            <input class="form-control" name="txtPassword" value="" type="password" placeholder="Enter your password" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <label>Confirm Password</label>
                            <input class="form-control" name="txtPasswordCf" value="" type="password" placeholder="Enter your password" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <label>Mobile No</label>
                            <label style="color: red; font-size: 13px;">${requestScope.ERROR_phone}</label>
                            <input class="form-control" name="txtPhone" value="${requestScope.txtPhone}" type="text" placeholder="0123 456 789">
                        </div>
                        <div class="col-md-7 form-group">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" onclick="checkCheckbox();" name="condition" class="custom-control-input" id="acceptbtn">
                                <label class="custom-control-label" for="acceptbtn">I accept all conditions</label>
                            </div>
                        </div>

                        <div class="col-md-5 form-group">
                            <button type="button" class=" nav-link btn p-0" data-toggle="modal" data-target="#condition">
                            View conditions
                        </button>
                            <a href=""></a>
                        </div>
                        <div class="col-md-12 card-footer bg-transparent">
                            <button type="button" class="nav-item nav-link btn" data-toggle="modal" data-target="#loginModal">
                                Already have an account?
                            </button>
                            <!-- <a href="">Already have an account?</a> -->
                            <button id="createbtn" disabled name="action" value="sigin" class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3">Creater an account</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <!-- Checkout End -->

    <!-- Modal condition -->
    <div class="modal fade" id="condition" tabindex="-1" role="dialog" aria-labelledby="conditionTitle" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content rounded">
                <div class="modal-header">
                    <h5 class="modal-title" id="conditionTitle">Our conditions</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
                </div>
                <div class="modal-body">
                    1- Nam dep trai <br> 2- Nam dep trai
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        function checkCheckbox() {
            var btncheck = document.getElementById("acceptbtn");
            var btncreate = document.getElementById("createbtn");  
            if (btncheck.checked == true ) {
                return document.getElementById("createbtn").disabled = false;
            }
            else {
                return document.getElementById("createbtn").disabled = true;
            }
        }  
    </script>
    <!-- Back to Top -->