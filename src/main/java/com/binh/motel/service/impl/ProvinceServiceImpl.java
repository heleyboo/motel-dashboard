package com.binh.motel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.core.entity.District;
import com.binh.core.entity.Province;
import com.binh.core.repository.ProvinceRepository;
import com.binh.core.service.ProvinceService;

import javassist.NotFoundException;

@Service
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceRepository repo;

	@Override
	public List<Province> getAll() {
		return repo.findAll();
	}

	@Override
	public List<District> getDistricts(String provinceId) {
		List<District> districts = new ArrayList<District>();
		Optional<Province> province = repo.findById(provinceId);
		if (province.isPresent()) {
			districts = province.get().getDistricts();
		}
		return districts;
	}

	@Override
	public List<Province> getProvinces() {
		return repo.findAll();
	}

	@Override
	public Province getProvinceById(String provinceId) throws NotFoundException {
		return repo.findById(provinceId).orElseThrow(() -> new NotFoundException("Province not found"));
	}

}
