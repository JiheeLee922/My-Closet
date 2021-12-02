package com.jihee.msub.chat.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jihee.msub.chat.dto.ChatRoomDTO;
import com.jihee.msub.chat.service.ChatService;
import com.jihee.msub.member.dto.MemberAdapter;
import com.jihee.msub.member.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value =  "/chat")
public class ChatRoomController {

	//private final ChatRoomRepositoryImpl repository;
	private final ChatService chatService;
	
	
//	@GetMapping("/rooms")
//    public String getRooms(){
//        return "chat/rooms";
//    }

    @GetMapping(value = "/room")
    public String getRoom(Long chatRoomId, Principal pricipal, Model model){

        model.addAttribute("chatRoomId", chatRoomId);
        model.addAttribute("nickname", pricipal.getName());

        return "chat/room";
    }
	    
	@GetMapping("/rooms")
	public ModelAndView rooms() {
		
		ModelAndView mv = new ModelAndView("/chat/rooms");
		mv.addObject("list", chatService.getChatRoomList());
		
		return mv;
	}
	
	@PostMapping("/room")
	public String create(@RequestParam String name, @AuthenticationPrincipal MemberAdapter member, RedirectAttributes rttr) {
		ChatRoomDTO dto = new ChatRoomDTO();
		dto.setRoomName(name);
		MemberDTO memdto = new MemberDTO();
		memdto.setId(member.getMember().getId());
		dto.setMember(memdto);
		
		rttr.addFlashAttribute("roomName",chatService.saveChatRoom(dto));
		
		return "redirect:/chat/rooms";
	}
//	
//	
//	@GetMapping("/room")
//	public void getRoom(String roomId, Model model, Principal principal) {
//		model.addAttribute("room", repository.findRoomById(roomId));
//		model.addAttribute("username", principal.getName());
//	}
}
