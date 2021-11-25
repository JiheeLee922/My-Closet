<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml" xmlns:sec="http://www.w3.org/1999/xhtml">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<style>
.alert{
	border: solid 1px #dde0c3;
    margin: 10px;
    padding: 15px;
    border-radius: 5px;
}
.alert-warning{
	background-color: white;
	float: left;
}
.alert-secondary{
	float:right;
    background-color: #fedd39;
}
#msgArea{
	background-color: #b9b9dc;
	display: grid;
	padding: 15px;
}
.btn-outline-secondary{
	border: 1px solid white;
    background-color: #d5d5d5;
    width: 60px;
    height: 100%;
}
</style>
<head>
    <meta charset="UTF-8">
    <title>채팅방</title>
</head>
<script type="text/javascript">

	let stomp = null;
	$(document).ready(function(){
		
		let roomName = "${room.name}";
		let roomId = "${room.roomId}";
		let username = "${username}";
		
		//1. SockJs를 내부에 들고있는 Stomp를 내어줌
		let sockJs = new SockJS("/stomp/chat");
		stomp = Stomp.over(sockJs);
		
		//2. connection이 맺어지면 실행
		stomp.connect({}, function(){
			
			//4. subscribe(path,callback) 으로 메세지 받을 수 잇다.
			stomp.subscribe( "/sub/chat/room/" + roomId, function(chat){
				
				let content = JSON.parse(chat.body);
				
				let writer = content.writer;
				let message = content.message;
				let str = '';
				
				if(writer == username) {
					str = "<div class='col-6'>";
                    str += "<div class='alert alert-secondary'>";
                    str += "<p>" + writer + " : " + message + "</p>";
                    str += "</div></div>";
				}else{
					str = "<div class='col-6'>";
                    str += "<div class='alert alert-warning'>";
                    str += "<p>" + writer + " : " + message + "</p>";
                    str += "</div></div>";
				}
				
				$("#msgArea").append(str);
			});
			
			//3. send(path, header, message) 로 메세지를 보낼 수 있음
			stomp.send('/pub/chat/enter', {}, JSON.stringify({roomId : roomId, writer: username}));
		});
		
		$("#button-send").on('click', function(e){
			let msg = document.getElementById("msg");
			
			stomp.send('/pub/chat/message', {}, JSON.stringify({roomId: roomId, message: msg.value, writer:username}));
			msg.value = '';
		});
		
		
		document.addEventListener("keypress", function(e){
			if(e.keyCode == 13){ //enter press
				$("#button-send").click();
			}
		});
		
	});
	
	function disconnect(){
		debugger;
		if(stomp !== null){
			stomp.disconnect();
		}
	}
	
</script>
<body>
    <div>
	    <div class="content">
	        <div class="container">
	            <div class="col-6">
	                <h1>● ${room.name}</h1>
	            </div>
	            <button class="btn btn-outline-secondary" type="button" onclick="disconnect()">나가기</button>
	            <div style="width: 720px;">
	                <div id="msgArea" class="col"></div>
	                <div class="col-6">
	                    <div class="input-group mb-3" style="height: 50px;">
	                        <input type="text" id="msg" class="form-control" style="border: 1px solid white; width: 90%;">
                            <button class="btn btn-outline-secondary" type="button" id="button-send">전송</button>
	                    </div>
	                </div>
	            </div>
	            <div class="col-6"></div>
	        </div>
	
	
	</div>
</body>
</html>