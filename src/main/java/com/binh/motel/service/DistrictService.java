package com.binh.motel.service;

import com.binh.core.entity.District;

import javassist.NotFoundException;

public interface DistrictService {
	public District getDistrictById(String districtId) throws NotFoundException;
}
