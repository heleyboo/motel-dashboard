package com.binh.motel.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.binh.motel.dto.CategoryDto;
import com.binh.motel.dto.RoomFilter;
import com.binh.motel.dto.UserDto;
import com.binh.motel.dto.UserFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.Category;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.User;
import com.binh.motel.service.UserService;
import com.binh.motel.entity.Role;

import javassist.NotFoundException;

@Controller
@RequestMapping("/administrator/user")
public class UserController {
	@Autowired
	public UserService userService;
	
	@GetMapping(value = "/create")
	public String showForm(Model model , @ModelAttribute("filter") UserFilter filter) {
		UserDto userDto = new UserDto();
		PageResponse<User> paged = userService.searchUsers(filter);
		List<Role> roles = userService.getRoles();
		model.addAttribute("paged", paged);
		model.addAttribute("userDto", userDto);
		model.addAttribute("roles", roles);
		return "admin/user/edit";
	}

	@PostMapping(value = "/create")
	public String createUser(@Valid UserDto userDto, BindingResult bindingResult, Model model , @ModelAttribute("filter") UserFilter filter)
			throws NotFoundException {
		if (bindingResult.hasErrors()) {
			PageResponse<User> paged = userService.searchUsers(filter);
			List<Role> roles = userService.getRoles();
			model.addAttribute("paged", paged);
			model.addAttribute("userDto", userDto);
			model.addAttribute("roles", roles);
			return "admin/user/edit";
		}
		userService.saveUser(userDto);
		return "redirect:/administrator/user";
	}
	
	
	@GetMapping("/list")
	public String listUsers(Model model , @ModelAttribute("filter") UserFilter filter) {
		PageResponse<User> paged = userService.searchUsers(filter);
		List<Role> roles = userService.getRoles();
		model.addAttribute("paged", paged);
		model.addAttribute("roles", roles);
		return "admin/user/list";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditUser(Model model,@PathVariable(name = "id") int id) throws NotFoundException, FileNotFoundException, IOException{
	    User user = userService.get(id);
	    model.addAttribute("user", user);
	     
	    return "admin/user/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String editUser(@PathVariable(value = "id") Integer id, @Valid UserDto dto,
			BindingResult bindingResult, Model model) throws NotFoundException, FileNotFoundException, IOException{
		if (bindingResult.hasErrors()) {
			model.addAttribute("user", dto);
			return "admin/user/edit";
		}
		userService.editUser(id, dto);
		return "redirect:/administrator/user/list";
		}
	
	@GetMapping("/approve/{id}")
	public String approveComment(@PathVariable("id") int id) throws NotFoundException {
		userService.toggleStatus(id, true);
		return "redirect:/administrator/user/list";
	}
	
	@GetMapping("/disapprove/{id}")
	public String disApproveComment(@PathVariable("id") int id) throws NotFoundException {
		userService.toggleStatus(id, false);
		return "redirect:/administrator/user/list";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) throws NotFoundException {
		userService.toggleDelete(id, false);
	    return "redirect:/administrator/user/list";       
	}
	
	@RequestMapping("/restore/{id}")
	public String restoreUser(@PathVariable("id") int id) throws NotFoundException {
		userService.toggleDelete(id, true);
	    return "redirect:/administrator/user/list";       
	}
	
}
