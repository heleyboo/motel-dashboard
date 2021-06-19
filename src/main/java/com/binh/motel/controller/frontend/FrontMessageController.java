package com.binh.motel.controller.frontend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.entity.Message;
import com.binh.motel.service.AuthenticationService;
import com.binh.motel.service.MessageService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/messages")
public class FrontMessageController {

	@Autowired
	private MessageService msgService;
	
	@Autowired
	private AuthenticationService authService;

	@GetMapping()
	public String showMessages(Model model) {
		List<Message> messages = msgService.getMessagesByCurrentUser();
		model.addAttribute("messages", messages);
		return "frontend/messages";
	}
	
	@GetMapping("/{uuid}")
	public String showMessageDetails(@PathVariable("uuid") String uuid, Model model) throws NotFoundException {
		List<Message> messages = msgService.getMessagesByCurrentUser();
		Message message = msgService.getMessageByUuid(uuid);
		model.addAttribute("messages", messages);
		model.addAttribute("message", message);
		model.addAttribute("currentUser", authService.currentUser());
		return "frontend/messages";
	}
}
