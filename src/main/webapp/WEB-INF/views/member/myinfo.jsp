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
    
    <div>
    	<h1>메일 발송</h1>

	    <form action="/mail" method="post">
	    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	        <input name="address" placeholder="이메일 주소"> <br>
	        <input name="title" placeholder="제목"> <br>
	        <textarea name="message" placeholder="메일 내용을 입력해주세요." cols="60" rows="20"></textarea>
	        <button>발송</button>
    	</form>
    </div>
</body>
</html>