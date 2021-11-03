package com.jihee.msub.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jihee.msub.board.domain.entity.BoardEntity;
import com.jihee.msub.board.domain.repository.BoardRepository;
import com.jihee.msub.board.dto.BoardDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {
	private BoardRepository boardRepository;
	
	@Transactional
	public Long savePost(BoardDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getId();
	}
	
	@Transactional
	public List<BoardDto> getBoardList(){
		List<BoardEntity> boardEntites = boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		boardEntites.forEach(boardEntity ->{
			BoardDto boardDto = BoardDto.builder()
					.id(boardEntity.getId())
					.title(boardEntity.getTitle())
					.content(boardEntity.getContent())
					.writer(boardEntity.getWriter())
					.createdDate(boardEntity.getCreatedDate())
					.build();
			
			boardDtoList.add(boardDto);
			
		});
		
		return boardDtoList;
	}
	
	@Transactional
    public BoardDto getPost(Long id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDto boardDTO = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();

        return boardDTO;
    }


    @Transactional
    public void deletePost(Long id) {
    	boardRepository.deleteById(id);
    }
}
