package com.jihee.msub.chat.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jihee.msub.chat.domain.entity.ChatEntity;
import com.jihee.msub.chat.domain.entity.ChatRoomEntity;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

	List<ChatEntity> findByChatRoom(ChatRoomEntity chatRoom);
}
