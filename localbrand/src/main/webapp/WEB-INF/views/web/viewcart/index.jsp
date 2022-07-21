<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
view cart page </br>
member discount ${sessionScope.user.getRankId().getDiscount()} </br>
total price ${requestScope.totalPrice} </br>
<c:forEach var="cart" items="${requestScope.listCartByBrand}">

<c:forEach var="key" items="${cart.getMap().keySet()}">
${cart.getMap().get(key).getProduct().getId()}
${cart.getMap().get(key).getProduct().getBrandId().getName()} </br>
</c:forEach> 

</c:forEach>
<a href="/localbrand/web/cart/updateinfo.do?email=testemail&name=testname&phone=testphone&address=testaddress"> update info ne</a>
</br>
<a href="/localbrand/web/cart/checkout.do"> checkout ne</a>