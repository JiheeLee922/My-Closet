package com.jihee.msub.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jihee.msub.member.dto.MailDto;
import com.jihee.msub.member.dto.MemberDto;
import com.jihee.msub.member.service.MailService;
import com.jihee.msub.member.service.MemberSerivce;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MemberController {

	private MemberSerivce memberService;
	private MailService mailService;
	
	@GetMapping("/")
	public String index() {
		return "/member/index";
	}
	
	
	@GetMapping("/user/signup")
	public String dispSignup() {
		return "/member/signup";
	}
	
	@PostMapping("/user/signup")
	public String execSignup(@Valid MemberDto memberDto, Errors errors, Model model) {
		if(errors.hasErrors()) {
			//회원가입 유효성 실패 시, 입력 데이터 유지
			model.addAttribute("userDto", memberDto);
			
			//유효성 실패 핸들링
			Map<String, String> validatorResult = memberService.validateHandling(errors);
			for(String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			
			return "/member/signup";
		}
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
	
	
	
	@PostMapping("/mail")
    public void execMail(MailDto mailDto) {
        mailService.mailSend(mailDto);
    }
	
	
	@GetMapping("/chatRoom")
	public String chatView() {
		return "/member/chatting";
	}
}
