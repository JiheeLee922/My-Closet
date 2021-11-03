<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<style>
.error_msg{
	color: red;
    font-size: small;
}
</style>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>회원가입 페이지</title>
</head>
<body>
    <h1>회원 가입</h1>
    <hr>

    <form action="/user/signup" method="post">
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    	<div>
	        <input type="text" name="email" value="${userDto.email}" placeholder="이메일을 입력해주세요">
	        <span class="error_msg">${valid_email}</span>
        </div>
        <div>
	        <input type="text" name="nickname" value="${userDto.nickname}" placeholder="닉네임을 입력해주세요">
	        <span class="error_msg">${valid_nickname}</span>
        </div>
        <div>
	        <input type="password" name="password" value="${userDto.password}" placeholder="비밀번호">
	        <span class="error_msg">${valid_password}</span>
        </div>
        <button type="submit">가입하기</button>
    </form>
</body>
</html>