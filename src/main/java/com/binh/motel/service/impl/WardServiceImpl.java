package com.binh.motel.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.motel.entity.District;
import com.binh.motel.entity.Ward;
import com.binh.motel.repository.WardRepository;
import com.binh.motel.service.DistrictService;
import com.binh.motel.service.WardService;

import javassist.NotFoundException;

@Service
public class WardServiceImpl implements WardService {

	@Autowired
	private WardRepository repo;
	
	@Autowired
	private DistrictService districtService;

	@Override
	public Optional<Ward> getWardByCode(String code) {
		return repo.findById(code);
	}

	@Override
	public List<Ward> getWardsByDistrictCode(String districtCode) throws NotFoundException {
		District district = districtService.getDistrictById(districtCode);
		return repo.findByDistrict(district);
	}

}
