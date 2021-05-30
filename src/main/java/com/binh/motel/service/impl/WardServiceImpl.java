package com.binh.motel.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.motel.entity.Ward;
import com.binh.motel.repository.WardRepository;
import com.binh.motel.service.WardService;

@Service
public class WardServiceImpl implements WardService {

	@Autowired
	private WardRepository repo;

	@Override
	public Optional<Ward> getWardByCode(String code) {
		return repo.findById(code);
	}

}
