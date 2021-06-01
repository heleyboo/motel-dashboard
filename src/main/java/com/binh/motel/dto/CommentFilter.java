package com.binh.motel.dto;

import org.springframework.data.jpa.domain.Specification;

import com.binh.motel.entity.Comment;
import com.binh.motel.specification.CommentSpecification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentFilter extends Filter<Comment> {

	public CommentFilter(String pageNum, String pageSize, String search) {
		super(pageNum, pageSize, search);
	}

	@Override
	public Specification<Comment> buildSpec() {
		Specification<Comment> spec = CommentSpecification.isTrue();
		return spec;
	}
}
