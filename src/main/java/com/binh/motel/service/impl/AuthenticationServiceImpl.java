package com.binh.motel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.binh.motel.entity.User;
import com.binh.motel.enums.RoleEnum;
import com.binh.motel.security.MotelUserDetails;
import com.binh.motel.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private com.binh.motel.service.UserService userService;

	@Override
	public MotelUserDetails getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		MotelUserDetails userDetails = (MotelUserDetails) authentication.getPrincipal();
		return userDetails;
	}

	@Override
	public boolean isLoggedInAsAdmin() {
		return isRole(RoleEnum.ROLE_ADMIN);
	}

	@Override
	public boolean isLoggedInAsUser() {
		return isRole(RoleEnum.ROLE_USER);
	}

	public boolean isRole(RoleEnum role) {
		MotelUserDetails userDetails = getUserDetails();
		return userDetails.getAuthorities().stream().anyMatch(it -> it.getAuthority().equals(role.toString()));
	}

	@Override
	public User currentUser() {
		MotelUserDetails userDetails = getUserDetails();
		return userService.findUserByUserName(userDetails.getUsername());
	}

}
