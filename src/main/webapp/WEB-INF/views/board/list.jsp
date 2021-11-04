<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="/css/board.css">
</head>

<script type="text/javascript">
function pageSearch(pageNum){
	
	const keyword = document.getElementById("keyword").value;
	location.href = "/board/list?page="+pageNum+"&keyword="+keyword;
}
</script>
<body>

<a href="/board/post"><spring:message code='board.write_post' /></a>
<a href="/">메인</a>
<hr>
<table>
    <thead>
    <tr>
        <th class="one wide">번호</th>
        <th class="ten wide">글제목</th>
        <th class="two wide">작성자</th>
        <th class="three wide">작성일</th>
    </tr>
    </thead>

    <tbody>
    <!-- CONTENTS !-->
    <c:forEach items="${boardList}" var="board">
	    <tr>
	        <td>
	            <span>${board.id}</span>
	        </td>
	        <td>
	            <a href="/board/post/${board.id}">
	                <span>${board.title}</span>
	            </a>
	        </td>
	        <td>
	            <span >${board.writer}</span>
	        </td>
	        <td>
	            <span>${board.createdDate}</span>
	        </td>
	    </tr>
    </c:forEach>
    </tbody>
</table>

<div>
	<c:forEach items="${pageList }" var="pageNum" >
    <span>
        <a href="javascript:pageSearch(${pageNum})">${pageNum}</a>
    </span>
	</c:forEach>
</div>

<hr>
<!-- 검색 form -->
<form action="/board/list" method="GET">
    <div>
        <input name="keyword" id="keyword" type="text" placeholder="검색어를 입력해주세요" value="${keyword}">
    </div>

    <button>검색하기</button>
</form>

</body>
</html>