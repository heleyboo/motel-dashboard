package com.binh.motel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.binh.motel.data.domain.FilterPageRequest;
import com.binh.motel.dto.CommentFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.Comment;
import com.binh.motel.repository.CommentRepository;
import com.binh.motel.service.CommentService;

import javassist.NotFoundException;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepo;

	@Override
	public PageResponse<Comment> searchComments(CommentFilter filter) {
		Pageable pageable = FilterPageRequest.of(filter);
		Specification<Comment> spec = filter.buildSpec();
		Page<Comment> paged = commentRepo.findAll(spec, pageable);
		return new PageResponse<Comment>(paged, filter);
	}

	@Override
	public Comment findById(int id) throws NotFoundException {
		return commentRepo.findById(id).orElseThrow(() -> new NotFoundException("Không tồn tại phản hồi"));
	}

	@Override
	public void deleteComment(int id) throws NotFoundException {
		Comment comment = findById(id);
		commentRepo.delete(comment);
		Comment comment1 = findById(id);
	}

	@Override
	public void toggleStatus(int id, boolean status) throws NotFoundException {
		Comment comment = findById(id);
		comment.setApproved(status);
		commentRepo.save(comment);
	}

}
