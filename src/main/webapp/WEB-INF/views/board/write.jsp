<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form  action="/board/post" method="post">
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        제목 : <input type="text" name="title"> <br>
        작성자 : <input type="text" name="writer"> <br>
        <textarea name="content"></textarea><br>

        <input type="submit" value="등록">
    </form>
</body>
</html>