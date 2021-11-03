package com.jihee.msub.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jihee.msub.login.dto.MemberDto;
import com.jihee.msub.login.service.MemberSerivce;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MemberController {

	private MemberSerivce memberService;
	
	@GetMapping("/")
	public String index() {
		return "/member/index";
	}
	
	
	@GetMapping("/user/signup")
	public String dispSignup() {
		return "/member/signup";
	}
	
	@PostMapping("/user/signup")
	public String execSignup( MemberDto memberDto) {
		memberService.joinUser(memberDto);
		return "redirect:/user/login";
	}
	
	@GetMapping("/user/login")
	public String dispLogin(HttpServletRequest request) 
	{
		String referrer = request.getHeader("Referer");
		request.getSession().setAttribute("prevPage", referrer);
		return "/member/login";
	}
	
	@GetMapping("/user/login/result")
	public String dispLoginResult() {
		return "/member/loginSuccess";
	}
	
	@GetMapping("/user/logout/result")
	public String dispLogout() {
		return "/member/logout";
	}
	
	@GetMapping("/user/denied")
	public String dispDenied() {
		return "/member/denied";
	}
	
	@GetMapping("/user/info")
	public String dispMyInfo() {
		return "/member/myinfo";
	}
	
	@GetMapping("/admin")
	public String dispAdmin() {
		return "/member/admin";
	}
	
}
