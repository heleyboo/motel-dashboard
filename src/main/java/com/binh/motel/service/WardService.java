package com.binh.motel.service;

import java.util.Optional;

import com.binh.motel.entity.Ward;

public interface WardService {
	public Optional<Ward> getWardByCode(String code);
}
