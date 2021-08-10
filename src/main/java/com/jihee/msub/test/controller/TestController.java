package com.jihee.msub.test.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jihee.msub.test.dto.TestDto;
import com.jihee.msub.test.service.TestService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value = "test")
public class TestController {
	
	private TestService testService;
	
	@GetMapping("/list")
	public String list(Model model ) {
		List<TestDto> boardList = testService.getBoardList();
		
		model.addAttribute("boardList",boardList);
		return "test/list";
	}
	
	@GetMapping("/post")
    public String write() {
        return "test/write"; 
    }

	@PostMapping("/post")
	public String write(TestDto testDto) {
		testService.savePost(testDto);
		return "redirect:/test/list"; 
	}
	
	@GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        TestDto boardDTO = testService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "test/detail";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        TestDto boardDTO = testService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "test/update";
    }

    @PutMapping("/post/edit/{no}")
    public String update(TestDto boardDTO) {
        testService.savePost(boardDTO);

        return "redirect:/test/list";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        testService.deletePost(no);

        return "redirect:/test/list";
    }
}
