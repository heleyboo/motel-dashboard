package com.binh.motel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.motel.entity.Message;
import com.binh.motel.entity.User;
import com.binh.motel.repository.MessageRepository;
import com.binh.motel.service.AuthenticationService;
import com.binh.motel.service.MessageService;

import javassist.NotFoundException;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageRepository msgRepo;
	
	@Autowired
	private AuthenticationService authService;

	@Override
	public List<Message> getMessagesByCurrentUser() {
		User user = authService.currentUser();
		return msgRepo.findByHostOrClient(user, user);
	}

	@Override
	public Message getMessageByUuid(String uuid) throws NotFoundException {
		return msgRepo.findByUuid(uuid).orElseThrow(() -> new NotFoundException("Message not found: " + uuid));
	}

}
