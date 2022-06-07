<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<h1>${hello}</h1>
Test Category<br>
<c:forEach var="item" items="${listCategory}">
	${item}<br>
</c:forEach>
<h1>Test MembershipTier find(5)</h1>
${mem.rank}

