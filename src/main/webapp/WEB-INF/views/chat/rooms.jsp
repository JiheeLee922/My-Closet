<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml" xmlns:sec="http://www.w3.org/1999/xhtml">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<head>
    <meta charset="UTF-8">
    <title>채팅방 목록</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		
		let roomName = "${roomName}";
		if(roomName != null && roomName != "")
			alert(roomName + "방이 개설되었습니다.");
		
		$(".btn-create").on("click",function(e){
			e.preventDefault();
			
			let name = $("input[name='name']").val();
			if(name == "")
				alert("채팅방 이름을 입력해주세요.");
			else
				$("form").submit();
		});
		
	});
</script>
<body>
    <div>
	    <div class="content">
	        <div class="container">
	            <div>
	                <ul>
						<c:forEach items="${list}" var="room">
		                    <li><a href="/chat/room?roomId=${room.roomId}">● ${room.name}</a></li>
						</c:forEach>	                
	                </ul>
	            </div>
	        </div>
	        <form action="/chat/room" method="post">
	        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	            <input type="text" name="name" class="form-control">
	            <button class="btn btn-create">개설하기</button>
	        </form>
	    </div>
	</div>
</body>
</html>