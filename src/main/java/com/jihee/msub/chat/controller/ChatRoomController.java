package com.jihee.msub.chat.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jihee.msub.chat.domain.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value =  "/chat")
public class ChatRoomController {

	private final ChatRoomRepository repository;
	
	
	@GetMapping("/rooms")
    public String getRooms(){
        return "chat/rooms";
    }

    @GetMapping(value = "/room")
    public String getRoom(Long chatRoomId, String nickname, Model model){

        model.addAttribute("chatRoomId", chatRoomId);
        model.addAttribute("nickname", nickname);

        return "chat/room";
    }
	    
//	@GetMapping("/rooms")
//	public ModelAndView rooms() {
//		
//		ModelAndView mv = new ModelAndView("/chat/rooms");
//		mv.addObject("list", repository.findAllRooms());
//		
//		return mv;
//	}
//	
//	@PostMapping("/room")
//	public String create(@RequestParam String name, RedirectAttributes rttr) {
//		rttr.addFlashAttribute("roomName", repository.createChatRoomDTO(name));
//		
//		return "redirect:/chat/rooms";
//	}
//	
//	
//	@GetMapping("/room")
//	public void getRoom(String roomId, Model model, Principal principal) {
//		model.addAttribute("room", repository.findRoomById(roomId));
//		model.addAttribute("username", principal.getName());
//	}
}
