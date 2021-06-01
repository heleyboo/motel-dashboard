package com.binh.motel.service;

import com.binh.motel.entity.User;
import com.binh.motel.security.MotelUserDetails;

public interface AuthenticationService {
	public MotelUserDetails getUserDetails();
	public boolean isLoggedInAsAdmin();
	public boolean isLoggedInAsUser();
	public User currentUser();
}
