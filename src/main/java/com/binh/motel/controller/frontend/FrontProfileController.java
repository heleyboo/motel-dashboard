package com.binh.motel.controller.frontend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.binh.motel.dto.CategoryDto;
import com.binh.motel.dto.RoomFilter;
import com.binh.motel.dto.UserDto;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.Category;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.User;
import com.binh.motel.service.AuthenticationService;
import com.binh.motel.service.MotelRoomService;
import com.binh.motel.service.UserService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/profile/me")
public class FrontProfileController {
	
	@Autowired AuthenticationService authService;
	
	@Autowired MotelRoomService roomService;
	
	@Autowired 
	private UserService userService;
	
	@GetMapping
	public String showProfile(Model model,@ModelAttribute("filter") RoomFilter filter) {
		model.addAttribute("user", authService.currentUser());
		PageResponse<MotelRoom> paged = roomService.searchRoomsByUser(authService.currentUser(), filter);
		model.addAttribute("paged", paged);
		model.addAttribute("user", authService.currentUser());
		return "frontend/profile/me";
	}
	

	@GetMapping("/edit/{id}")
	public String showEditUser(Model model) throws NotFoundException, FileNotFoundException, IOException{
		model.addAttribute("user", authService.currentUser());
		return "frontend/room/detailsProfile";
	}
	
	@PostMapping("/edit/{id}")
	public String editUser(@PathVariable(value = "id") int id, @Valid UserDto dto,
			BindingResult bindingResult, Model model, RedirectAttributes attibutes) throws NotFoundException, FileNotFoundException, IOException{
		if (bindingResult.hasErrors()) {
			model.addAttribute("user", authService.currentUser());
			return "frontend/room/detailsProfile";
		}
		userService.editUser(id, dto);
		String message = "Cập nhật thành công";
		attibutes.addFlashAttribute("message", message);
		return "redirect:/profile/me";
		}
	
	@RequestMapping("/delete/{id}")
	public String deleteRoom(@PathVariable("id") int id) throws NotFoundException {
		roomService.toggleDelete(id, false);
	    return "redirect:/profile/me";       
	}
	
	@RequestMapping("/restore/{id}")
	public String restoreRoom(@PathVariable("id") int id) throws NotFoundException {
		roomService.toggleDelete(id, true);
	    return "redirect:/profile/me";       
	}
	
}
