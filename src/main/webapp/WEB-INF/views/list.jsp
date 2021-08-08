<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/test.css" />
<title>Insert title here</title>
</head>
<body>

	<a href="/post">글쓰기</a>
	
	<c:choose>
	<c:when test="true">asdsa</c:when>
	<c:otherwise>asdasd</c:otherwise>
	
	</c:choose>
</body>
</html>