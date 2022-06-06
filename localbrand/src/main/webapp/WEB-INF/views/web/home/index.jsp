<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<h1>${hello}</h1>
Test Category<br>
<c:forEach var="item" items="${listCategory}">
	${item}<br>
	${item.getName()}<br>
	${item.getGender() }<br><br>
</c:forEach>

<%--${listCategory} --%>