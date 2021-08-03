package com.jihee.msub.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jihee.msub.test.dto.TestDto;
import com.jihee.msub.test.service.TestService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TestController {
	
	private TestService testService;
	
	@GetMapping("/")
	public String list() {
		return "test/list";
	}
	
	@GetMapping("/post")
    public String write() {
        return "test/write"; 
    }

	@PostMapping("/post")
	public String write(TestDto testDto) {
		testService.savePost(testDto);
		return "redirect:/"; 
	}
}
