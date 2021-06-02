package com.binh.motel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.service.CommentService;
import com.binh.motel.service.MotelRoomService;
import com.binh.motel.service.UserService; 


import javassist.NotFoundException;

@Controller
@RequestMapping("/administrator/dashboard")
public class DashboardController {
	@Autowired
	private MotelRoomService postrepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping
	public String dashboard(Model model) throws NotFoundException  {
		model.addAttribute("roomsCount", postrepo.countRooms());
		model.addAttribute("userCount", userService.countUsers());
		model.addAttribute("commentCount", commentService.countComment());
		return "admin/home";
	}
}
