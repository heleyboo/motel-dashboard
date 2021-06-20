package com.binh.motel.controller.frontend;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.binh.motel.entity.ChatMessage;
import com.binh.motel.entity.Message;
import com.binh.motel.entity.MessageLine;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.User;
import com.binh.motel.service.AuthenticationService;
import com.binh.motel.service.MessageService;
import com.binh.motel.service.MotelRoomService;
import com.binh.motel.service.UserService;

import javassist.NotFoundException;

@Controller
public class FrontMessageController {

	@Autowired
	private MessageService msgService;

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@Autowired
	private MotelRoomService roomService;
	
	@Autowired
	private UserService userService;

	@MessageMapping("/chat.sendMessage/{room}")
	public void sendMessage(@DestinationVariable String room, @Payload ChatMessage chatMessage) throws NotFoundException {
		MessageLine msgLine = new MessageLine();
		User sender = userService.findUserByUserName(chatMessage.getSender());
		Message message = msgService.getMessageByUuid(chatMessage.getRoomId());
		msgLine.setUser(sender);
		msgLine.setMessage(message);
		msgLine.setCreatedDate(new Date());
		msgLine.setContent(chatMessage.getContent());
		
		msgService.createMessageLine(msgLine);
		messagingTemplate.convertAndSend("/topic/public/" + room, chatMessage);
	}

	@GetMapping("/messages")
	public String showMessages(Model model) {
		List<Message> messages = msgService.getMessagesByCurrentUser();
		model.addAttribute("messages", messages);
		model.addAttribute("currentUser", authService.currentUser());
		return "frontend/messages";
	}

	@GetMapping("messages/{roomId}/{uuid}")
	public String showMessageDetails(@PathVariable("roomId") int roomId, @PathVariable("uuid") String uuid, Model model)
			throws NotFoundException {
		List<Message> messages = msgService.getMessagesByCurrentUser();
		MotelRoom room = roomService.findById(roomId);
		Message message = msgService.getMessageByUuidOrCreate(uuid, room);
		model.addAttribute("messages", messages);
		model.addAttribute("message", message);
		model.addAttribute("currentUser", authService.currentUser());
		model.addAttribute("uuid", uuid);
		return "frontend/messages";
	}
}
