package com.binh.motel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.binh.motel.entity.Comment;
import com.binh.motel.entity.MotelRoom;

public interface CommentRepository extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment>  {
	public List<Comment> findByApprovedTrueAndRoom(MotelRoom room);
}
