package com.jihee.msub.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jihee.msub.test.domain.entity.TestEntity;
import com.jihee.msub.test.domain.repository.TestRepository;
import com.jihee.msub.test.dto.TestDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TestService {
	private TestRepository testRepository;
	
	@Transactional
	public Long savePost(TestDto testDto) {
		return testRepository.save(testDto.toEntity()).getId();
	}
	
	@Transactional
	public List<TestDto> getBoardList(){
		List<TestEntity> testEntites = testRepository.findAll();
		List<TestDto> testDtoList = new ArrayList<>();
		
		testEntites.forEach(testEntity ->{
			TestDto testDto = TestDto.builder()
					.id(testEntity.getId())
					.title(testEntity.getTitle())
					.content(testEntity.getContent())
					.writer(testEntity.getWriter())
					.createdDate(testEntity.getCreatedDate())
					.build();
			
			testDtoList.add(testDto);
			
		});
		
		return testDtoList;
	}
	
	@Transactional
    public TestDto getPost(Long id) {
        Optional<TestEntity> boardEntityWrapper = testRepository.findById(id);
        TestEntity boardEntity = boardEntityWrapper.get();

        TestDto boardDTO = TestDto.builder()
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
        testRepository.deleteById(id);
    }
}
