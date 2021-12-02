package com.jihee.msub.chat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jihee.msub.chat.domain.entity.ChatRoomEntity;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long>{

}
