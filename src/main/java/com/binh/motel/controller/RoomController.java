package com.binh.motel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.dto.RoomFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.service.MotelRoomService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/administrator/room")
public class RoomController {
	@Autowired
	private MotelRoomService service;

	
	
	@GetMapping("/list")
	public String searchRooms(Model model, @ModelAttribute("filter") RoomFilter filter) {
		PageResponse<MotelRoom> paged = service.searchRooms(filter);
		model.addAttribute("paged", paged);
		return "admin/postroom/list";
	}
	
	@GetMapping("/{code}")
	public String editMotelRoom(@PathVariable int id, Model model) throws NotFoundException {
		MotelRoom motelRoom = service.getMotelRoomById(id);
		model.addAttribute("motelRoom", motelRoom);
		return "admin/postroom/list";
	}
	
	@RequestMapping("/approve/{roomId}")
	public String approveRoom(@PathVariable("roomId") int id) throws NotFoundException {
		service.approveRoom(id);
		return "redirect:/administrator/room/list";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteRoom(@PathVariable("id") int id) throws NotFoundException {
	    service.deleteRoom(id);
	    return "redirect:/administrator/room/list";       
	}
}
