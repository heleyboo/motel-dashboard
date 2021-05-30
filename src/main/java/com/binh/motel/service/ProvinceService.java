package com.binh.motel.service;

import java.util.List;

import com.binh.motel.entity.District;
import com.binh.motel.entity.Province;

import javassist.NotFoundException;

public interface ProvinceService {
	public List<Province> getAll();
	public List<District> getDistricts(String provinceId);
	public List<Province> getProvinces();
	public Province getProvinceById(String provinceId) throws NotFoundException;
}
