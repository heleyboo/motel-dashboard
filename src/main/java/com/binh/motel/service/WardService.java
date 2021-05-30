package com.binh.motel.service;

import java.util.Optional;

import com.binh.core.entity.Ward;

public interface WardService {
	public Optional<Ward> getWardByCode(String code);
}
