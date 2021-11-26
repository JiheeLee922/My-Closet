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
		
		const messageContent = document.querySelector('#msg');
		const btnSend = document.querySelector('#button-send');
		
		const chatRoomId = "${chatRoomId}";
		const nickname = "${nickname}";
		
		const sockJs = new SockJS("/stomp/chat");
		const stomp = Stomp.over(sockJs);
		
		stomp.heartbeat.outgoing = 0 ; //rabbit에선 heartbeat 안먹힘
		stomp.heartbeat.incoming = 0 ;
		
		function onError(e){ console.log("STOMP ERROR", e);}
		function onDebug(e){ console.log("STOMP DEBUG", e);}
		
		stomp.debug = onDebug;
		
		//2. connection이 맺어지면 실행 - guest/guest는 rabbit 계정
		stomp.connect('guest', 'guest', function(frame){
			
			/* subscribe 설정에 따라 rabbit의 Exchange, Queue가 상당히 많이 바뀜 */
			stomp.subscribe('/topic/room.${chatRoomId}', function(content){
				/* 
				url별 차이
				Exchange Destination - /exchange/chat.exchange/room.${chatRoomId}: 
					chatRoomId가 1일 때 chat.exchange라는 exchange에 room.1이라는 패턴을 가진 Queue를 생성 후 바인딩하고 그 Queue를 구독한다.
				Queue Destination -  /queue/room.${chatRoomId} : 
					chatRoomId가 1일 때 room.1이라는 이름의 Queue를 생성하고 구독. RabbitMQ의 default exchage(AMQP Default)와 바인딩된다.
					바인딩 키는 queue이름과 동일
					이건 단체 채팅 안되고 여러명 있어도 한 명한테만 간다. AMQP Default의 type이 direct 라서
				Topic Destination - /topic/room.${chatRoomId} : 	
					topic/<name>의 형태이고, chatRoomId가 1일 때 'amq.topic'이라는 Rabbit이 준비해둔 Exchange에 바인딩되는데, 
					바인딩 되는 Queue는 임의적인 이름을 가지며, Binding_key는 room.1이다.
					exchange와 마찬가지로 클라이언트 당 1개의 Queue가 생긴다.
					이 때 생성되는 Queue는 auto_deleted하고, durable하며 이름은 subscription-xxx...와 같이 생성된다 
				Amq/queue Destination - /amq/queue/room.${chatRoomId} : 
					/amq/queue/<name> 의 형태 .  <name>이라는 queue가 존재해야만 한다 (존재하지 않을 시 예외 발생)
					미리 만들어둔 Queue를 사용하기 때문에 따로 바인딩을 생각할 필요는 없다
				*/
				
				const payload = JSON.parse(content.body);
				
				let writer = payload.nickname;
				let message = payload.message;
				let str = '';
				
				if(writer == nickname) {
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
			
				//밑의 인자는 Queue 생성 시 주는 옵션
	            //auto-delete : Consumer가 없으면 스스로 삭제되는 Queue
	            //durable : 서버와 연결이 끊겨도 메세지를 저장하고 있음
	            //exclusive : 동일한 이름의 Queue 생길 수 있음
			},{'auto-delete': true, 'durable':false, 'exclusive': false});
			
			
			//입장 메세지
			stomp.send('/pub/chat.enter.'+chatRoomId, {}, JSON.stringify({ 
				memberId: 1,
	            nickname: nickname
	        }));
			
		}, onError, '/');
		
		btnSend.addEventListener('click', (e) => {
			
			e.preventDefault();
			const message = messageContent.value;
			
			stomp.send('/pub/chat.message.'+chatRoomId, {}, JSON.stringify({
				message: message,
				memberId: 1,
	            nickname: nickname
	        }));
			
			messageContent.value = '';
		});
		
		
		document.addEventListener("keypress", function(e){
			if(e.keyCode == 13){ //enter press
				$("#button-send").click();
			}
		});
		
	});
	
	
</script>
<body>
    <div>
	    <div class="content">
	        <div class="container">
	            <div class="col-6">
	                <h1>● Room No. ${chatRoomId}</h1>
    				<h1>● Nickname : ${nickname}</h1>
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