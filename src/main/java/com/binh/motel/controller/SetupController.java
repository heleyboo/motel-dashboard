package com.binh.motel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.dto.AdminUserDto;
import com.binh.motel.service.SetupService;

@Controller
@RequestMapping("/setup")
public class SetupController {

	@Autowired
	private SetupService setupService;

	@GetMapping
	public String showCreateAdminForm(Model model) {
		AdminUserDto userDto = new AdminUserDto();
		model.addAttribute("userDto", userDto);
		return "create_admin";
	}

	@PostMapping
	public String createAdminUser(@Valid AdminUserDto userDto, BindingResult bindingResult, Model model) throws Exception {
		if (bindingResult.hasErrors()) {
			model.addAttribute("userDto", userDto);
			return "create_admin";
		}
		setupService.setup(userDto);
		return "redirect:/login";
	}
}
