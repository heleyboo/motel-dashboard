package com.binh.motel.controller.frontend;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.dto.MotelRoomDto;
import com.binh.motel.entity.Province;
import com.binh.motel.enums.RoomDirection;
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
		List<RoomDirection> directions = Arrays.asList(RoomDirection.values());
		model.addAttribute("provinces", provinces);
		model.addAttribute("motelRoomDto", motelRoomDto);
		model.addAttribute("directions", directions);
		return "frontend/room/create";
	}

	@PostMapping
	public String create(@Valid MotelRoomDto motelRoomDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<Province> provinces = provinceService.getAll();
			List<RoomDirection> directions = Arrays.asList(RoomDirection.values());
			model.addAttribute("provinces", provinces);
			model.addAttribute("motelRoomDto", motelRoomDto);
			model.addAttribute("directions", directions);
			return "frontend/room/create";
		}

		// roomService.saveRoom()
		return "redirect:/dang-tin";

	}
}
