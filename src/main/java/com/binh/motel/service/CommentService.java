package com.binh.motel.service;

import com.binh.motel.dto.CommentFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.Comment;

public interface CommentService {
	public PageResponse<Comment> searchComments(CommentFilter filter);
}
