package com.binh.motel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.dto.CommentFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.Comment;
import com.binh.motel.service.CommentService;

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
}
