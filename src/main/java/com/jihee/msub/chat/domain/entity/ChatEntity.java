package com.jihee.msub.chat.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jihee.msub.member.domain.entity.MemberEntity;
import com.jihee.msub.util.TimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="chat")
public class ChatEntity extends TimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 200, nullable = false)
	private String message;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ChatRoomEntity chatRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	private MemberEntity member;

	@Builder
	public ChatEntity(Long id, String message, MemberEntity member,  ChatRoomEntity chatRoom) {
		this.id = id;
		this.message = message;
		this.member = member;
		this.chatRoom = chatRoom;
	}
	
	
}
