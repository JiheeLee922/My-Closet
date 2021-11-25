package com.jihee.msub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.jihee.msub.util.SocketTextHandler;

import lombok.AllArgsConstructor;


@Configuration
@EnableWebSocketMessageBroker
@AllArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { //implements WebSocketConfigurer { 기존에서 STOMP 쓰기위해 변경

	//SocketTextHandler socketTextHandler;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry.addEndpoint("/stomp/chat")
				.setAllowedOrigins("http://localhost:8080") 	// *로 하면 404발생..
				.withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		registry.setPathMatcher(new AntPathMatcher(".")); //url을 chat/room/3 -> chat.room.3으로 참조하기 위한 설정
		
		registry.setApplicationDestinationPrefixes("/pub");	//SEND요청 처리
		
		//registry.enableSimpleBroker("/sub");	//해당 경로로 SimpleBroker 등록. SimpleBroker는 해당하는 경로를 SUBSCRIBE하는 client에게 메세지 전달
		registry.enableStompBrokerRelay("/queue", "/topic", "/exchange", "/amq/queue"); //이건 외부 Message Broker (RabbitMQ, ActiveMQ등)에 메세지 전달.  
	}
	
	
	
	
	
	
//	@Override 기존에서 STOMP 쓰기위해 변경
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		registry.addHandler(socketTextHandler, "/chat") //chat으로 호출이 오면 socketTextHandler 실행
////				.setAllowedOrigins("*");
//				.withSockJS(); //SockJs 이용하려고 추가 cf) https://dev-gorany.tistory.com/224
//	}

}
