<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>
	
	<jsp:include page="/WEB-INF/decorators/admin/header.jsp" />
	<jsp:include page="/WEB-INF/views/${page}/${controller}/${action}.jsp" />
	<jsp:include page="/WEB-INF/decorators/admin/footer.jsp" />
</body>
</html>