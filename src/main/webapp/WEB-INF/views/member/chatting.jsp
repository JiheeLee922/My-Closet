<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<style>
	*{
		margin:0;
		padding:0;
	}
	.container{
		width: 500px;
		margin: 0 auto;
		padding: 25px
	}
	.container h1{
		text-align: left;
		padding: 5px 5px 5px 15px;
		color: #FFBB00;
		border-left: 3px solid #FFBB00;
		margin-bottom: 20px;
	}
	.chating{
		background-color: #000;
		width: 500px;
		height: 500px;
		overflow: auto;
	}
	.chating p{
		color: #fff;
		text-align: left;
	}
	input{
		width: 330px;
		height: 25px;
	}
	#yourMsg{
		display: none;
	}
</style>
<script type="text/javascript">
	let ws ; 
	
	function connectWs() {
		$("#chat").html('');
		ws = new WebSocket('ws://' + location.host + '/chat');
		
		ws.onopen = function(data){
			//소켓이 열리면 초기화 세팅하기
			$("#yourName").hide();
			$("#yourMsg").show();
			$("#endBtn").show();
		}
		
		ws.onmessage = function(data){
			$("<p>" + data.data + "</p>").prependTo('#chating');
		}
		
		document.addEventListener("keypress", function(e){
			if(e.keyCode == 13){ //enter press
				send();
			}
		});
		
		
	}
	
	function send(){
		var uN = $("#userName").val();
		var msg = $("#chatting").val();
		if(msg == '' ){
			return;
		}
		ws.send(uN+" : "+msg);
		$('#chatting').val("");
	}
	
	function disconnectWs(){
		ws.close();
		ws = null;
		$("#yourName").show();
		$("#yourMsg").hide();
		$("#endBtn").hide();
	}
	
	function chatName(){
		var userName = $("#userName").val();
		if(userName == null || userName.trim() == ""){
			alert("사용자 이름을 입력해주세요.");
			$("#userName").focus();
		}else{
			connectWs();
			
		}
	}
	
</script>
<body>
	<div id="container" class="container">
		<h1>채팅</h1>
		<button onclick="disconnectWs()" id="endBtn" style="display:none;"> 채팅 끝내기</button>
		<div id="chating" class="chating">
		</div>
		
		<div id="yourName">
			<table class="inputTable">
				<tr>
					<th>사용자명</th>
					<th><input type="text" name="userName" id="userName"></th>
					<th><button onclick="chatName()" id="startBtn">채팅 시작</button></th>
				</tr>
			</table>
		</div>
		<div id="yourMsg">
			<table class="inputTable">
				<tr>
					<th>메시지</th>
					<th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
					<th><button onclick="send()" id="sendBtn">보내기</button></th>
				</tr>
			</table>
		</div>
	</div>

</body>
</html>