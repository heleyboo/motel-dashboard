package com.binh.motel.controller.frontend;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.entity.Comment;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.repository.CommentRepository;
import com.binh.motel.service.MotelRoomService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/room")
public class FrontDetailRoomController {
	
	@Autowired MotelRoomService roomService;
	
	@Autowired CommentRepository commentRepo;
	
	@GetMapping("{slug}.html")
	public String roomDetails(@PathVariable("slug") String slug, Model model) throws NotFoundException {
		MotelRoom room = roomService.findBySlugOrId(slug);
		Comment comment = new Comment();
		comment.setRoom(room);
		model.addAttribute("room", room);
		model.addAttribute("comment", comment);
		return "frontend/room/details";
	}
	
	@PostMapping("/{id}/comments")
	public String addComment(@PathVariable("id") int roomId, @Valid Comment comment, BindingResult bindingResult,
			Model model) throws NotFoundException {
		MotelRoom room = roomService.findById(roomId);
		comment.setRoom(room);
		if (bindingResult.hasErrors()) {
			model.addAttribute("room", room);
			model.addAttribute("comment", comment);
			return "frontend/room/details";
		}
		commentRepo.save(comment);
		return "redirect:/room/" + room.getSlug() + ".html";
	}
}
