package com.binh.motel.service;

import java.util.List;
import java.util.Optional;

import com.binh.motel.entity.Ward;

import javassist.NotFoundException;

public interface WardService {
	public Optional<Ward> getWardByCode(String code);
	public List<Ward> getWardsByDistrictCode(String districtCode) throws NotFoundException;
}
