package com.jihee.msub.chat.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jihee.msub.board.domain.entity.BoardEntity;
import com.jihee.msub.board.dto.BoardDTO;
import com.jihee.msub.chat.domain.entity.ChatRoomEntity;
import com.jihee.msub.chat.domain.repository.ChatRoomRepository;
import com.jihee.msub.chat.dto.ChatRoomDTO;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ChatService {

	private ChatRoomRepository chatRoomRepository;
	
	public String saveChatRoom(ChatRoomDTO dto) {
		return chatRoomRepository.save(dto.toEntity()).getRoomName();
	}
	
	public List<ChatRoomDTO> getChatRoomList(){
		List<ChatRoomEntity> list = chatRoomRepository.findAll();
		List<ChatRoomDTO> dtoList = new ArrayList<>();
		for(ChatRoomEntity entity : list) {
			dtoList.add(convertEntityToDto(entity));
		}
		
		return dtoList;
	}
	
	 private ChatRoomDTO convertEntityToDto(ChatRoomEntity entity) {
    	return ChatRoomDTO.builder()
    			.id(entity.getId())
    			.roomName(entity.getRoomName())
    			.build();
    }
}
