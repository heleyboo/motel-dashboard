package com.binh.motel.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.motel.entity.District;
import com.binh.motel.entity.Province;
import com.binh.motel.entity.Ward;
import com.binh.motel.service.ProvinceService;
import com.binh.motel.service.WardService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/v1/provinces")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiProvinceController {

	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private WardService wardService;

	@GetMapping("/{id}/districts")
	public List<District> getDistricts(@PathVariable("id") String provinceId) {
		return provinceService.getDistricts(provinceId);
	}

	@GetMapping
	public List<Province> getProvinces() {
		return provinceService.getProvinces();
	}

	@GetMapping("/{id}/wards")
	public List<Ward> getWards(@PathVariable("id") String districtId) throws NotFoundException{
		return wardService.getWardsByDistrictCode(districtId);
	}

	
}
