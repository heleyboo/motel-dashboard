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
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.Province;
import com.binh.motel.enums.RoomDirection;
import com.binh.motel.service.PostRoomService;
import com.binh.motel.service.ProvinceService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/dang-tin")
public class FrontCreateRoomController {

	@Autowired
	private ProvinceService provinceService;
	
	
	@Autowired
	private PostRoomService post;

	@GetMapping
	public String showForm(Model model) {
		MotelRoomDto motelRoomDto = new MotelRoomDto();
//		List<MotelRoom> motelRoomDto = post.getAll();
		List<Province> provinces = provinceService.getAll();
		List<RoomDirection> directions = Arrays.asList(RoomDirection.values());
		model.addAttribute("provinces", provinces);
		model.addAttribute("motelRoomDto", motelRoomDto);
		model.addAttribute("directions", directions);
		return "frontend/room/create";
	}

	@PostMapping
	public String create(@Valid MotelRoomDto motelRoomDto, BindingResult bindingResult, Model model) throws NotFoundException {
		if (bindingResult.hasErrors()) {
			List<Province> provinces = provinceService.getAll();
			List<RoomDirection> directions = Arrays.asList(RoomDirection.values());
			model.addAttribute("provinces", provinces);
			model.addAttribute("motelRoomDto", motelRoomDto);
			model.addAttribute("directions", directions);
			return "frontend/room/create";
		}

		post.save(motelRoomDto);
		return "redirect:/dang-tin";

	}
}
