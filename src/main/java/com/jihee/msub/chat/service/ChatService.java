package com.jihee.msub.chat.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jihee.msub.board.domain.entity.BoardEntity;
import com.jihee.msub.board.dto.BoardDTO;
import com.jihee.msub.chat.domain.entity.ChatEntity;
import com.jihee.msub.chat.domain.entity.ChatRoomEntity;
import com.jihee.msub.chat.domain.repository.ChatRepository;
import com.jihee.msub.chat.domain.repository.ChatRoomRepository;
import com.jihee.msub.chat.dto.ChatDTO;
import com.jihee.msub.chat.dto.ChatRoomDTO;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ChatService {

	private ChatRoomRepository chatRoomRepository;
	private ChatRepository chatRepository;
	
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
	
	public void saveChatMessage(ChatDTO dto) {
		chatRepository.save(dto.toEntity());
	}
	
	public List<ChatDTO> getChatList(Long chatRoomId){
		ChatRoomEntity cre = new ChatRoomEntity(chatRoomId, null, null);
		List<ChatEntity> entities = chatRepository.findByChatRoom(cre);
		List<ChatDTO> dtoList = new ArrayList<>();
		for(ChatEntity entity : entities) {
			dtoList.add(convertEntityToDtoChat(entity));
		}
		
		return dtoList;
	}
	
	
	private ChatRoomDTO convertEntityToDto(ChatRoomEntity entity) {
    	return ChatRoomDTO.builder()
    			.id(entity.getId())
    			.roomName(entity.getRoomName())
    			.build();
    }
	private ChatDTO convertEntityToDtoChat(ChatEntity entity) {
		return ChatDTO.builder()
				.id(entity.getId())
				.message(entity.getMessage())
				.memberId(entity.getMember().getId()) 
				.chatRoomId(entity.getChatRoom().getId()) 
				.build();
	}
}
