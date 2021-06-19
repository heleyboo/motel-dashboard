package com.binh.motel.service;

import java.util.List;

import com.binh.motel.entity.Message;

import javassist.NotFoundException;

public interface MessageService {
	public List<Message> getMessagesByCurrentUser();
	public Message getMessageByUuid(String uuid) throws NotFoundException;
}
