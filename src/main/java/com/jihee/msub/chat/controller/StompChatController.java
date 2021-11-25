package com.jihee.msub.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.jihee.msub.chat.dto.ChatMessageDTO;

import lombok.RequiredArgsConstructor;

/** 해당 컨트롤러는 stomp까지만 사용. rabbit 적용 후엔 StompRabbitController 참고하기!!!!!!*/
@Deprecated
@Controller
@RequiredArgsConstructor
public class StompChatController {

	private final SimpMessagingTemplate template ; //특정 Broker로 메세지를 전달
	
	
	//client가 SEND 할 수 있는 경로
	//WebSocket Congig에서 설정한 setApplicationDestinationPrefixes 와 경로 병합됨 -> "/pub/chat/enter"
	@MessageMapping(value="/chat/enter")	//해당 어노를 통해 WebSocket으로 들어오는 메세지 발행 처리
	public void enter (ChatMessageDTO message) {
		message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
		template.convertAndSend("/sub/chat/room/" + message.getRoomId() , message);
	}
	
	@MessageMapping(value = "/chat/message")
	public void message(ChatMessageDTO message) {
		template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
	}
	
	
}
