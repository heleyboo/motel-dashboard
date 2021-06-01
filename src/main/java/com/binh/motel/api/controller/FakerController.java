package com.binh.motel.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binh.motel.entity.Category;
import com.binh.motel.entity.District;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.Province;
import com.binh.motel.entity.RoomImage;
import com.binh.motel.entity.Ward;
import com.binh.motel.enums.RoomDirection;
import com.binh.motel.repository.CategoryRepository;
import com.binh.motel.repository.MotelRoomRepository;
import com.binh.motel.repository.RoomImageRepository;
import com.binh.motel.repository.WardRepository;
import com.binh.motel.service.AuthenticationService;
import com.binh.motel.service.DistrictService;
import com.binh.motel.service.ProvinceService;
import com.binh.motel.service.WardService;
import com.binh.motel.util.SlugUtil;
import com.github.javafaker.Faker;

@RestController
@RequestMapping("/api/v1/fake")
public class FakerController {
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private WardService wardService;
	
	@Autowired WardRepository repo;
	
	@Autowired CategoryRepository categoryRepo;
	
	@Autowired MotelRoomRepository roomRepo;
	
	@Autowired AuthenticationService authService;
	
	@Autowired RoomImageRepository roomImageRepo;
	
	@GetMapping
	public void createRooms() {
		
		
		List<Ward> wards = repo.findAll();
		List<Category> categories = categoryRepo.findAll();
		List<MotelRoom> motels = new ArrayList<MotelRoom>();
		Faker faker = new Faker(new Locale("vi-VN"));
		for (int i = 0; i < 10000; i++) {
			MotelRoom motelRoom = createRandomMotelRoom(faker, wards, categories);
			motelRoom.setCreatedBy(authService.currentUser());
			MotelRoom savedRoom = roomRepo.save(motelRoom);
			
			int imgIndex= faker.random().nextInt(1, 7);
			List<RoomImage> images = new ArrayList<RoomImage>();
			for (int j = imgIndex; j <= imgIndex + 2; j++) {
				RoomImage ri = new RoomImage();
				ri.setUrl(String.format("/images/%s.jpg", Integer.toString(j)));
				ri.setRoom(savedRoom);
				images.add(ri);
			}
			
			roomImageRepo.saveAll(images);
			
		}
		
	}
	
	private MotelRoom createRandomMotelRoom(Faker faker, List<Ward> wards, List<Category> categories) {
		
		MotelRoom motelRoom = new MotelRoom();
		motelRoom.setPrice(BigDecimal.valueOf(faker.number().numberBetween(2000000, 10000000)));
		motelRoom.setArea(faker.random().nextInt(20, 50));
		motelRoom.setDepositAmount(BigDecimal.valueOf(faker.number().numberBetween(2000000, 10000000)));
		
		List<RoomDirection> directions = Arrays.asList(RoomDirection.values());
		
		int indexDr = faker.random().nextInt(0, directions.size() - 1);
		int indexDr1 = faker.random().nextInt(0, directions.size() - 1);
		
		motelRoom.setBalconyDirection(directions.get(indexDr1));
		motelRoom.setDoorDirection(directions.get(indexDr));
		motelRoom.setNumOfBedrooms(faker.random().nextInt(1, 4));
		motelRoom.setNumOfToilets(faker.random().nextInt(1, 2));
		motelRoom.setDescription(faker.address().fullAddress());
		
		int wardIndex = faker.random().nextInt(0, wards.size() - 1);
		Ward ward = wards.get(wardIndex);
		
		District district = ward.getDistrict();
		
		Province province = district.getProvince();
		
		String address = faker.address().buildingNumber() + "," + faker.address().streetName();
		
		
		String fullAddress = String.format(
				"%s, %s, %s, %s",
				address,
				ward.getNameWithType(),
				district.getNameWithType(),
				province.getNameWithType());
		
		motelRoom.setFullAddress(fullAddress);
		motelRoom.setProvince(province.getNameWithType());
		
		motelRoom.setWardCode(ward.getCode());
		motelRoom.setDistrictCode(district.getCode());
		motelRoom.setProvinceCode(province.getCode());
		
		motelRoom.setTitle("Cho thuê phòng trọ " + address);
		
		int categoryIndex = faker.random().nextInt(0, categories.size() - 1);
		motelRoom.setCategory(categories.get(categoryIndex));
		motelRoom.setAddress(address);
		motelRoom.setPhoneNumber(faker.phoneNumber().phoneNumber());
		motelRoom.setSlug(SlugUtil.toSlug(fullAddress));
		
		
		
		return motelRoom;
	}

}
