package com.binh.motel.service;

import java.util.List;

import com.binh.motel.dto.CommentFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.Comment;
import com.binh.motel.entity.MotelRoom;

import javassist.NotFoundException;

public interface CommentService {
	public PageResponse<Comment> searchComments(CommentFilter filter);
	public Comment findById(int id) throws NotFoundException;
	public void deleteComment(int id)  throws NotFoundException;
	public void toggleStatus(int id, boolean status) throws NotFoundException;
	public List<Comment> findApprovedTrueAndRoom(MotelRoom room);
	public Comment save(Comment comment);
	public long countComment();
	
}
