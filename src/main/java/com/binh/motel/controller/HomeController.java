package com.binh.motel.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.dto.RoomFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.Category;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.Province;
import com.binh.motel.enums.RoomDirection;
import com.binh.motel.service.CategoryService;
import com.binh.motel.service.MotelRoomService;
import com.binh.motel.service.ProvinceService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired MotelRoomService roomService;
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private CategoryService cate;
	
//	@GetMapping
//	public String home(Model model) {
//		RoomFilter filter = new RoomFilter("0", "24", "");
//		PageResponse<MotelRoom> paged = roomService.searchRooms(filter);
//		model.addAttribute("paged", paged);
//		return "frontend/room/home";
//	}
	
	@GetMapping
	public String searchRooms(Model model, @ModelAttribute("filter") RoomFilter filter) {
		filter.setPageNum("0");
		filter.setPageSize("24");
		PageResponse<MotelRoom> paged = roomService.searchRooms(filter);
		List<Category> categorys = cate.getAll();
		List<Province> provinces = provinceService.getAll();
		model.addAttribute("categorys", categorys);
		model.addAttribute("provinces", provinces);
		model.addAttribute("paged", paged);
		return "frontend/room/home";
	}
	
	
}
