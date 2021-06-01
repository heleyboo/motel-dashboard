package com.binh.motel.specification;

import org.springframework.data.jpa.domain.Specification;

import com.binh.motel.entity.Comment;

public final class CommentSpecification {

	public static Specification<Comment> propertyLike(String property, String value) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(property), "%" + value + "%");
	}

	public static Specification<Comment> isTrue() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
	}

}
