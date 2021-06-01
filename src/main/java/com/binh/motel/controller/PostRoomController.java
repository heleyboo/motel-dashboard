package com.binh.motel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.binh.motel.dto.CategoryDto;
import com.binh.motel.dto.MotelRoomDto;
import com.binh.motel.entity.Category;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.service.CategoryService;
import com.binh.motel.service.MotelRoomService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/administrator/room")
public class PostRoomController {
	@Autowired
	private MotelRoomService service;

	
	
	@GetMapping("/list")
	public String listMotelRooms(Model model) {
		List<MotelRoom> motelRooms = service.getAll();
		model.addAttribute("motelRooms", motelRooms);
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
