package com.binh.motel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.binh.motel.entity.Category;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.service.PostRoomService;

@Controller
@RequestMapping("/administrator/dashboard")
public class DashboardController {
	@Autowired
	private PostRoomService postrepo;
	
	
	@GetMapping
	public String dashboard() {
		return "admin/home";
	}
	

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseBody
	public long countMotelRoom() {
		long count = postrepo.getCountMotelRoom();
		return count;
	}
}
