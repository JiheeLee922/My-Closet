<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<h2 >${boardDto.title}</h2>
<p>작성일 : ${boardDto.createdDate}</p>

<p >${boardDto.content}</p>

<!-- 수정/삭제 -->
<div>
    <a href="/board/post/edit/${boardDto.id}">
        <button>수정</button>
    </a>

    <form id="delete-form" action="/board/post/${boardDto.id}" method="post">
        <input type="hidden" name="_method" value="delete"/>
        <button id="delete-btn">삭제</button>
    </form>
</div>

<!-- 변수 셋팅 -->
<script type="text/javascript">
    /*<![CDATA[*/
    var boardDto = /*[[${boardDto}]]*/ "";
    /*]]>*/
</script>

</body>
</html>