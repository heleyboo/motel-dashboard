package com.binh.motel.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.entity.MotelRoom;
import com.binh.motel.service.MotelRoomService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/room")
public class FrontDetailRoomController {
	
	@Autowired MotelRoomService roomService;
	
	@GetMapping("{slug}.html")
	public String roomDetails(@PathVariable("slug") String slug, Model model) throws NotFoundException {
		// Nếu file bị lỗi font tiếng việt kiểm tra lại xem có phải là UTF8 hay chưa
		MotelRoom room = roomService.findBySlugOrId(slug);
		model.addAttribute("room", room);
		return "frontend/room/details";
	}
}
