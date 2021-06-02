package com.binh.motel.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.service.AuthenticationService;
import com.binh.motel.service.MotelRoomService;

@Controller
@RequestMapping("/profile/me")
public class FrontProfileController {
	
	@Autowired AuthenticationService authService;
	
	@Autowired MotelRoomService roomService;
	
	@GetMapping
	public String showProfile(Model model) {
		model.addAttribute("user", authService.currentUser());
		PageResponse<MotelRoom> paged = roomService.searchRoomsByUser(authService.currentUser());
		model.addAttribute("paged", paged);
		return "frontend/profile/me";
	}
}
