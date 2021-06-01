package com.binh.motel.service;

import com.binh.motel.dto.CommentFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.Comment;

import javassist.NotFoundException;

public interface CommentService {
	public PageResponse<Comment> searchComments(CommentFilter filter);
	public Comment findById(int id) throws NotFoundException;
	public void deleteComment(int id)  throws NotFoundException;
	public void toggleStatus(int id, boolean status) throws NotFoundException;
	
}
