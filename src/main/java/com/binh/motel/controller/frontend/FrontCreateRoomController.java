package com.binh.motel.controller.frontend;

import java.net.URL;
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
import com.binh.motel.entity.Category;
import com.binh.motel.entity.Province;
import com.binh.motel.enums.RoomDirection;
import com.binh.motel.service.AuthenticationService;
import com.binh.motel.service.CategoryService;
import com.binh.motel.service.MotelRoomService;
import com.binh.motel.service.ProvinceService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/dang-tin")
public class FrontCreateRoomController {

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private CategoryService cate;

	@Autowired
	private MotelRoomService post;

	@Autowired
	AuthenticationService authService;

	@GetMapping
	public String showForm(Model model) {
		authService.getUserDetails();
		MotelRoomDto motelRoomDto = new MotelRoomDto();
		List<Category> categorys = cate.getAll();
		List<Province> provinces = provinceService.getAll();
		List<RoomDirection> directions = Arrays.asList(RoomDirection.values());
		model.addAttribute("categorys", categorys);
		model.addAttribute("provinces", provinces);
		model.addAttribute("motelRoomDto", motelRoomDto);
		model.addAttribute("directions", directions);
		return "frontend/room/create";
	}

	@PostMapping
	public String create(@Valid MotelRoomDto motelRoomDto, BindingResult bindingResult, Model model)
			throws NotFoundException {

		if (bindingResult.hasErrors()) {
			List<Category> categorys = cate.getAll();
			List<Province> provinces = provinceService.getAll();
			List<RoomDirection> directions = Arrays.asList(RoomDirection.values());
			model.addAttribute("categorys", categorys);
			model.addAttribute("provinces", provinces);
			model.addAttribute("motelRoomDto", motelRoomDto);
			model.addAttribute("directions", directions);
			return "frontend/room/create";
		}

		post.save(motelRoomDto);
		return "redirect:/profile/me";

	}
}
