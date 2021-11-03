<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>내정보</title>
</head>
<body>
    <h1>내정보 확인 페이지입니다.</h1>
    <hr>
    <sec:authentication property="name"/>님 환영합니다~
</body>
</html>