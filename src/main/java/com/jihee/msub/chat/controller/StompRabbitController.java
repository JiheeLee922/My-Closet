package com.jihee.msub.chat.controller;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.jihee.msub.chat.dto.ChatDTO;

import lombok.RequiredArgsConstructor;


/** StompChatController와 비교하면서 공부하기!*/
@Controller
@RequiredArgsConstructor
public class StompRabbitController {
	
	private final RabbitTemplate template;  // 전에는 SimpleMessagingTemplate 사용했다.
	private final static String CHAT_EXCHANGE_NAME = "chat.exchange";
	private final static String CHAT_TOPIC = "amq.topic";
	private final static String CHAT_QUEUE_NAME = "chat.queue";
	private static final String ROUTING_KEY = "room.";
	
	
	@MessageMapping("chat.enter.{chatRoomId}")
	public void enter(ChatDTO chat, @DestinationVariable String chatRoomId) {
		//@DestinationVariable 은 Web에서 사용할 때 @PathVariable과 같은거. @MessageMapping일땐 이걸 쓴다.
		
		chat.setMessage("입장하셨습니다.");
		chat.setRegDate(LocalDateTime.now()); 
		
		template.convertAndSend(CHAT_TOPIC, ROUTING_KEY+ chatRoomId, chat); //Topic Destination
		//template.convertAndSend( ROUTING_KEY+ chatRoomId, chat); //Queue Destination
		//template.convertAndSend(CHAT_EXCHANGE_NAME, ROUTING_KEY+ chatRoomId, chat); //Exchange Destination
		
	}
	
	@MessageMapping("chat.message.{chatRoomId}")
	public void send(ChatDTO chat, @DestinationVariable String chatRoomId) {
		chat.setRegDate(LocalDateTime.now());
		
		template.convertAndSend(CHAT_TOPIC, ROUTING_KEY + chatRoomId, chat);
	}
	
	
	/** receive()는 단순히 큐에 들어온 메세지를 소비만 한다.( 디버그 용도)
	 * RabbitListener 어노테이션은 chat.queue 라는 Queue를 구독해 그 큐에 들어온 메세지를 소비하는 소비자가 되겠다는 어노테이션.*/
	@RabbitListener(queues = CHAT_QUEUE_NAME)
	public void receive(ChatDTO chat) {
		System.out.println("received : "+chat.getMessage());
	}
	
}
