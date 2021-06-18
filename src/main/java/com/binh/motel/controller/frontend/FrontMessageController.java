package com.binh.motel.controller.frontend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.entity.Message;
import com.binh.motel.service.MessageService;

@Controller
@RequestMapping("/messages")
public class FrontMessageController {

	@Autowired
	private MessageService msgService;

	@GetMapping()
	public String showMessages(Model model) {
		List<Message> messages = msgService.getMessagesByCurrentUser();
		model.addAttribute("messages", messages);
		return "frontend/messages";
	}
}
