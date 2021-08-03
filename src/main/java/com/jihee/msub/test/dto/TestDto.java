package com.jihee.msub.test.dto;

import java.time.LocalDateTime;

import com.jihee.msub.test.domain.entity.TestEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TestDto {

	private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    
    public TestEntity toEntity() {
    	TestEntity testEntity = TestEntity.builder()
    			.id(id)
    			.writer(writer)
    			.title(title)
    			.content(content)
    			.build();
    	return testEntity;
    }
    
    @Builder
    public TestDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
    	this.id = id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;		
    }
}
