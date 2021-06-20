package com.binh.motel.controller.frontend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.binh.motel.entity.ChatMessage;
import com.binh.motel.entity.Message;
import com.binh.motel.service.AuthenticationService;
import com.binh.motel.service.MessageService;

import javassist.NotFoundException;

@Controller
public class FrontMessageController {

	@Autowired
	private MessageService msgService;
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat.sendMessage/{room}")
    public void sendMessage(@DestinationVariable String room, @Payload ChatMessage chatMessage) {
    	String topic = "/messages/" + room;
        messagingTemplate.convertAndSend("/topic/public/" + room , chatMessage);
    }

	@GetMapping("/messages")
	public String showMessages(Model model) {
		List<Message> messages = msgService.getMessagesByCurrentUser();
		model.addAttribute("messages", messages);
		return "frontend/messages";
	}
	
	@GetMapping("messages/{uuid}")
	public String showMessageDetails(@PathVariable("uuid") String uuid, Model model) throws NotFoundException {
		List<Message> messages = msgService.getMessagesByCurrentUser();
		Message message = msgService.getMessageByUuid(uuid);
		model.addAttribute("messages", messages);
		model.addAttribute("message", message);
		model.addAttribute("currentUser", authService.currentUser());
		model.addAttribute("uuid", uuid);
		return "frontend/messages";
	}
}
