package com.binh.motel.controller.frontend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.dto.MotelRoomDto;
import com.binh.motel.entity.Province;
import com.binh.motel.service.ProvinceService;

@Controller
@RequestMapping("/dang-tin")
public class FrontCreateRoomController {
	
	@Autowired
	private ProvinceService provinceService;
	
	@GetMapping
	public String showForm(Model model) {
		MotelRoomDto motelRoomDto = new MotelRoomDto();
		List<Province> provinces = provinceService.getAll();
		model.addAttribute("provinces", provinces);
		model.addAttribute("motelRoomDto", motelRoomDto);
		return "frontend/room/create";
	}
}
