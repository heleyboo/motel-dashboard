package com.binh.motel.dto;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

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
		if (StringUtils.hasText(search)) {
			spec = spec.and(CommentSpecification.propertyLike("commentedBy", search));
			spec = spec.or(CommentSpecification.propertyLike("content", search));
			spec = spec.or(CommentSpecification.propertyLike("phoneNumber", search));
			spec = spec.or(CommentSpecification.propertyLike("email", search));
		}
		return spec;
	}
}
