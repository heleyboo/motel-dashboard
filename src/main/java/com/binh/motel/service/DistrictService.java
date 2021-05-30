package com.binh.motel.service;

import java.util.List;

import com.binh.motel.entity.District;

import javassist.NotFoundException;

public interface DistrictService {
	public District getDistrictById(String districtId) throws NotFoundException;
	public List<District> findAll();
}
