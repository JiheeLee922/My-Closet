package com.jihee.msub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
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
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { //implements WebSocketConfigurer { �������� STOMP �������� ����

	//SocketTextHandler socketTextHandler;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry.addEndpoint("/stomp/chat")
				.setAllowedOrigins("http://localhost:8080") 	// *�� �ϸ� 404�߻�..
				.withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		registry.setApplicationDestinationPrefixes("/pub");	//SEND��û ó��
		registry.enableSimpleBroker("/sub");	//�ش� ��η� SimpleBroker ���. SimpleBroker�� �ش��ϴ� ��θ� SUBSCRIBE�ϴ� client���� �޼��� ����
		//registry.enableStompBrokerRelay(null) �̰� �ܺ� Message Broker (RabbitMQ, ActiveMQ��)�� �޼��� ����.  
	}
	
	
	
	
	
	
//	@Override �������� STOMP �������� ����
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		registry.addHandler(socketTextHandler, "/chat") //chat���� ȣ���� ���� socketTextHandler ����
////				.setAllowedOrigins("*");
//				.withSockJS(); //SockJs �̿��Ϸ��� �߰� cf) https://dev-gorany.tistory.com/224
//	}

}
