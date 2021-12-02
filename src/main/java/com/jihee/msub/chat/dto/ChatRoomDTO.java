package com.jihee.msub.chat.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import com.jihee.msub.chat.domain.entity.ChatRoomEntity;
import com.jihee.msub.member.dto.MemberDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDTO {

	private Long id ;
	private String roomName;
	private MemberDTO member;
	
	private Set<WebSocketSession> sessions = new HashSet<>();
	//webSocketSession은 Spring에서 WebSocket Connection이 맺어진 세션
	
//	public static ChatRoomDTO create(String name) {
//		ChatRoomDTO room = new ChatRoomDTO();
//		
//		room.id = UUID.randomUUID().toString();
//		room.roomName = name;
//		return room;
//	}
	
	
	public ChatRoomEntity toEntity() {
		if(this.member == null) {
			ChatRoomEntity entity = ChatRoomEntity.builder()
					.id(id)
					.room_name(roomName)
					.build();
			return entity;
			
		}else {
			ChatRoomEntity entity = ChatRoomEntity.builder()
					.id(id)
					.room_name(roomName)
					.member(member.toEntity())
					.build();
			return entity;
		}
				
	}

	@Builder
	public ChatRoomDTO(Long id, String roomName, MemberDTO member) {
		this.id = id;
		this.roomName = roomName;
		this.member = member;
	}

	public ChatRoomDTO() {
		super();
	}
	
	
	
}
