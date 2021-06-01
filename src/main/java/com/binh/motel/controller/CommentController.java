package com.binh.motel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.dto.CommentFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.Comment;
import com.binh.motel.service.CommentService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/administrator/comment")
public class CommentController {
	
	@Autowired CommentService commentService;
	
	@GetMapping("/list")
	public String listComments(Model model,  @ModelAttribute("filter") CommentFilter filter) {
		PageResponse<Comment> paged = commentService.searchComments(filter);
		model.addAttribute("paged", paged);
		return "admin/comment/list";
	}
	
	@GetMapping("/approve/{id}")
	public String approveComment(@PathVariable("id") int id) throws NotFoundException {
		commentService.toggleStatus(id, true);
		return "redirect:/administrator/comment/list";
	}
	
	@GetMapping("/disapprove/{id}")
	public String disApproveComment(@PathVariable("id") int id) throws NotFoundException {
		commentService.toggleStatus(id, false);
		return "redirect:/administrator/comment/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteComment(@PathVariable("id") int id) throws NotFoundException {
		commentService.deleteComment(id);
		return "redirect:/administrator/comment/list";
	}
}
