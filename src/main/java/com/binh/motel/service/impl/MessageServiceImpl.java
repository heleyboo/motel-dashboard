package com.binh.motel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.motel.entity.Message;
import com.binh.motel.entity.User;
import com.binh.motel.repository.MessageRepository;
import com.binh.motel.service.AuthenticationService;
import com.binh.motel.service.MessageService;

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

}
