package com.binh.motel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binh.motel.entity.Message;
import com.binh.motel.entity.User;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	public Optional<Message> findByUuid(String uuid);
	public List<Message> findByHostOrClient(User hostUser, User clientUser);
}
