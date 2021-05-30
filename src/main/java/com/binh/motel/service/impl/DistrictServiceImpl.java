package com.binh.motel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.motel.entity.District;
import com.binh.motel.repository.DistrictRepository;
import com.binh.motel.service.DistrictService;

import javassist.NotFoundException;

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DistrictRepository repo;

	@Override
	public District getDistrictById(String districtId) throws NotFoundException {
		return repo.findById(districtId).orElseThrow(() -> new NotFoundException("District not found"));
	}

	@Override
	public List<District> findAll() {
		return repo.findAll();
	}

}
