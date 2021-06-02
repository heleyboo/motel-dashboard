package com.binh.motel.service;

import com.binh.motel.dto.AdminUserDto;

public interface SetupService {
	public void setup(AdminUserDto adminUser, boolean isAdmin);
}
