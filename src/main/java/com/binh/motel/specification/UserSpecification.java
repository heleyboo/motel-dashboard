package com.binh.motel.specification;

import org.springframework.data.jpa.domain.Specification;

import com.binh.motel.entity.User;

public final class UserSpecification {

	public static Specification<User> firstNameLike(String value) {
		return propertyLike("firstName", value);
	}
	
	public static Specification<User> userNameLike(String value) {
		return propertyLike("userName", value);
	}
	public static Specification<User> emailLike(String value) {
		return propertyLike("email", value);
	}
	public static Specification<User> phoneNumberLike(String value) {
		return propertyLike("phoneNumber", value);
	}
	
	public static Specification<User> byStatus(boolean status) {
		return (root, query, criteriaBuilder) 
				-> criteriaBuilder.equal(root.get("active"), status);
	}
	
	public static Specification<User> propertyLike(String property, String value) {
		return (root, query, criteriaBuilder) 
				-> criteriaBuilder.like(root.get(property), "%" + value + "%");
	}
	
	public static Specification<User> isTrue() {
		return (root, query, criteriaBuilder) 
				-> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
	}
	
}
