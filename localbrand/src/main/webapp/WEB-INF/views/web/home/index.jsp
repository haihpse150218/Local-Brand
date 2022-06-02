<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<h1>${hello}</h1>
Test query membershipTier<br>
<c:forEach var="item" items="${listMembershipTier}">
	${item}<br>
</c:forEach>