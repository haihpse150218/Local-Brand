<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
order page
<c:forEach var="order" items="${requestScope.LIST_ORDER}">
order id ne : ${order.id } </br>
<a href="/localbrand/web/order/setstatusorder.do?orderstatus=test&orderid=${order.id}"> link update status ne </a> </br>
</c:forEach>
