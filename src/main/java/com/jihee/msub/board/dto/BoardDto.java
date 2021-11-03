package com.jihee.msub.board.dto;

import java.time.LocalDateTime;

import com.jihee.msub.board.domain.entity.BoardEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

	private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    
    public BoardEntity toEntity() {
    	BoardEntity boardEntity = BoardEntity.builder()
    			.id(id)
    			.writer(writer)
    			.title(title)
    			.content(content)
    			.build();
    	return boardEntity;
    }
    
    @Builder
    public BoardDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
    	this.id = id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;		
    }
}
