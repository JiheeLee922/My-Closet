package com.jihee.msub.chat.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.jihee.msub.chat.domain.entity.ChatEntity;
import com.jihee.msub.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
public class ChatDTO {

	private Long id;
    private ChatRoomDTO chatRoom;
    private MemberDTO member;
    
    private Long memberId;
    private Long chatRoomId;
    private String nickname;

    private String message;

    public ChatEntity toEntity() {
    	ChatEntity entity = ChatEntity.builder()
    			.id(id)
    			.chatRoom(chatRoom.toEntity())
    			.member(member.toEntity())
    			.message(message)
    			.build();
    			
    	return entity;		
    }

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
		this.member = new MemberDTO(memberId, null, null, null);
	}

	public void setChatRoomId(Long chatRoomId) {
		this.chatRoomId = chatRoomId;
		this.chatRoom = new ChatRoomDTO(chatRoomId, null, null);
	}
}
