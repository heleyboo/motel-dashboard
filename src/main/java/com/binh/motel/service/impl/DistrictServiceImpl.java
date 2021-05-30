package com.binh.motel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.core.entity.District;
import com.binh.core.repository.DistrictRepository;
import com.binh.core.service.DistrictService;

import javassist.NotFoundException;

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DistrictRepository repo;

	@Override
	public District getDistrictById(String districtId) throws NotFoundException {
		return repo.findById(districtId).orElseThrow(() -> new NotFoundException("District not found"));
	}

}
