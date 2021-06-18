package com.binh.motel.service;

import java.util.List;

import com.binh.motel.entity.Message;

public interface MessageService {
	public List<Message> getMessagesByCurrentUser();
}
