package com.jihee.msub.util;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketTextHandler extends TextWebSocketHandler{

	HashMap<String, WebSocketSession> sessions = new HashMap<>();

	
	//client에서 메시지가 서버로 전송될 때 실행되는 함수
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		
		sendMessage(new TextMessage(payload));
		
	}

	
	//세션 생성될 때 시작되는 함수
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		sessions.put(session.getId(), session);
		
		//sendMessage(new TextMessage(session.getId()+"님이 들어오셨습니다. "));
	}
	
	
	//세션 끝날 때 실행되는 함수
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session.getId());
		//sendMessage(new TextMessage(session.getId()+"님이 나가셨습니다. "));
		
		super.afterConnectionClosed(session, status);
	}
	
	
	
	private void sendMessage(WebSocketMessage<?> message) {
		try {
			for(String key: sessions.keySet()) {
				WebSocketSession s = sessions.get(key);
				s.sendMessage(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
