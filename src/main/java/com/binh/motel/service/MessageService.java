package com.binh.motel.service;

import java.util.List;

import com.binh.motel.entity.Message;
import com.binh.motel.entity.MessageLine;
import com.binh.motel.entity.MotelRoom;

import javassist.NotFoundException;

public interface MessageService {
	public List<Message> getMessagesByCurrentUser();
	public Message getMessageByUuid(String uuid) throws NotFoundException;
	public Message getMessageByUuidOrCreate(String uuid, MotelRoom room);
	public MessageLine createMessageLine(MessageLine msgLine);
}
