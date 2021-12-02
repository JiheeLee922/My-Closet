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
@Table(name="chat_room")
public class ChatRoomEntity extends TimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50, nullable = false, name = "room_name")
	private String roomName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private MemberEntity member;

	@Builder
	public ChatRoomEntity(Long id, String room_name, MemberEntity member) {
		this.id = id;
		this.roomName = room_name;
		this.member = member;
	}
	
	
}
