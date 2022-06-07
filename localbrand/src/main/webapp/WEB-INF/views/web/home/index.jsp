<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<h1>${hello}</h1>
<h1>${clistPayment}</h1>
Test query Payment<br>
<c:forEach var="item" items="${listMembershipTier}">
	${item}<br>
</c:forEach>
<c:forEach var="item1" items="${listPayment}">
	${item1}<br>
</c:forEach>