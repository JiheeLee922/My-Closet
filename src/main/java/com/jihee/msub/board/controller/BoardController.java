package com.jihee.msub.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jihee.msub.board.dto.BoardDto;
import com.jihee.msub.board.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@Controller
@Api(value="BoardController V1")
@AllArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {
	
	private BoardService boardService;
	
	@ApiOperation(value = "게시판 리스트", notes = "게시판 목록입니다.")
	@GetMapping("/list")
	public String list(Model model, @ApiParam(value = "게시판페이지번호", required = true, example = "1") @RequestParam(value = "page", defaultValue = "1") Integer pageNum , @RequestParam(value = "keyword", required = false) String keyword) {
		List<BoardDto> boardList = new ArrayList<>();
		boardList = boardService.getBoardList(pageNum, keyword);
		Integer[] pageList = boardService.getPageList(pageNum,keyword);
		
		model.addAttribute("boardList",boardList);
		model.addAttribute("pageList",pageList);
		model.addAttribute("keyword",keyword);
		return "board/list";
	}
	
	@GetMapping("/post")
    public String write() {
        return "board/write"; 
    }

	@PostMapping("/post")
	public String write(BoardDto boardDto) {
		boardService.savePost(boardDto);
		return "redirect:/board/list"; 
	}
	
	@GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/update";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDTO) {
    	boardService.savePost(boardDTO);

        return "redirect:/board/list";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
    	boardService.deletePost(no);

        return "redirect:/board/list";
    }
}
