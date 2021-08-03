package com.jihee.msub.test.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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
}
