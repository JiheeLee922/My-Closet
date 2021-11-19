<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml" xmlns:sec="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>메인</title>
</head>
<body>
    <h1>메인 페이지</h1>
    <hr>
    <sec:authorize access="isAnonymous()">
	    <a  href="/user/login">로그인</a>
	    <a  href="/user/signup">회원가입</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
	    <a  href="/user/logout">로그아웃</a>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_MEMBER')">
	    <a  href="/user/info">내정보</a>
	    <a  href="/board/list">게시판</a>
	    <a  href="/chat/rooms">채팅하기</a>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
	    <a  href="/admin">어드민</a>
    </sec:authorize>
</body>
</html>