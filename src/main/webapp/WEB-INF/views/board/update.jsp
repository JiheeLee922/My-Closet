<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/board/post/edit/${boardDto.id}" method="post">
    <input type="hidden" name="_method" value="put"/>
    <input type="hidden" name="id" value="${boardDto.id}"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    제목 : <input type="text" name="title" value="${boardDto.title}"> <br>
    작성자 : <input type="text" name="writer" value="${boardDto.writer}"> <br>
    <textarea name="content" >${boardDto.content}</textarea><br>

    <input type="submit" value="수정">
</form>
</body>
</html>