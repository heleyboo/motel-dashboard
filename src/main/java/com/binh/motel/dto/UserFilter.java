package com.binh.motel.dto;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.binh.motel.entity.User;
import com.binh.motel.specification.MotelRoomSpecification;
import com.binh.motel.specification.UserSpecification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter extends Filter<User> {
	
	private Boolean deleted = false;

	public UserFilter(String pageNum, String pageSize, String search) {
		super(pageNum, pageSize, search);
	}

	@Override
	public Specification<User> buildSpec() {
		Specification<User> spec = UserSpecification.isTrue();
		if (StringUtils.hasText(search)) {
			spec = UserSpecification.firstNameLike(search);
			spec = spec.or(UserSpecification.propertyLike("userName", search));
			spec = spec.or(UserSpecification.propertyLike("lastName", search));
			spec = spec.or(UserSpecification.propertyLike("email", search));
			spec = spec.or(UserSpecification.propertyLike("phoneNumber", search));
		}
		if (null != deleted) {
			spec = spec.and(UserSpecification.isDeleted(deleted));
		}

		return spec;
	}
}
