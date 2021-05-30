package com.binh.motel.api.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.motel.entity.District;
import com.binh.motel.entity.Province;
import com.binh.motel.entity.Ward;
import com.binh.motel.repository.DistrictRepository;
import com.binh.motel.repository.ProvinceRepository;
import com.binh.motel.repository.WardRepository;
import com.binh.motel.service.ProvinceService;
import com.binh.motel.service.StorageService;

@RestController
@RequestMapping("/api/v1/provinces")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiProvinceController {

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private ProvinceRepository provinceRepo;

	@Autowired
	private DistrictRepository districtRepo;

	@Autowired
	private WardRepository wardRepo;
	
	@Autowired
	private StorageService storageService;

	@GetMapping("/{id}/districts")
	public List<District> getDistricts(@PathVariable("id") String provinceId) {
		return provinceService.getDistricts(provinceId);
	}

	@GetMapping
	public List<Province> getProvinces() {
		return provinceService.getProvinces();
	}

	@GetMapping
	public List<Ward> getWard(){
		return wardRepo.getWard();
	}

	
}
