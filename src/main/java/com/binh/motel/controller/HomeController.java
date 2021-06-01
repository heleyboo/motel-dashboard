package com.binh.motel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.dto.RoomFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.service.MotelRoomService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired MotelRoomService roomService;
	
	@GetMapping
	public String home(Model model) {
		
		RoomFilter filter = new RoomFilter("0", "24", "");
		PageResponse<MotelRoom> paged = roomService.searchRooms(filter);
		model.addAttribute("paged", paged);
		return "frontend/room/home";
	}
}
