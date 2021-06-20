package com.binh.motel.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.motel.entity.Message;
import com.binh.motel.entity.MessageLine;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.User;
import com.binh.motel.repository.MessageLineRepository;
import com.binh.motel.repository.MessageRepository;
import com.binh.motel.service.AuthenticationService;
import com.binh.motel.service.MessageService;

import javassist.NotFoundException;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageRepository msgRepo;
	
	@Autowired
	private MessageLineRepository msgLineRepo;
	
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

	@Override
	public Message getMessageByUuidOrCreate(String uuid, MotelRoom room) {
		Optional<Message> messageOp = msgRepo.findByUuid(uuid);
		if (messageOp.isPresent()) {
			return messageOp.get();
		} else {
			Message msg = new Message();
			msg.setUuid(uuid);
			msg.setClient(room.getCreatedBy());
			msg.setMotelRoom(room);
			msg.setHost(authService.currentUser());
			msgRepo.save(msg);
			return msg;
		}
	}

	@Override
	public MessageLine createMessageLine(MessageLine msgLine) {
		return msgLineRepo.save(msgLine);
	}

}
