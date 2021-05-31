package com.binh.motel.service.impl;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.binh.motel.specification.Filter;
import com.binh.motel.specification.MotelRoomSpecification;
import com.binh.motel.dto.MotelRoomDto;
import com.binh.motel.entity.District;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.Province;
import com.binh.motel.entity.RoomImage;
import com.binh.motel.entity.Ward;
import com.binh.motel.service.CategoryService;
import com.binh.motel.service.DistrictService;
import com.binh.motel.service.ProvinceService;
import com.binh.motel.service.StorageService;
import com.binh.motel.service.WardService;
import com.binh.motel.repository.PostRoomRepository;
import com.binh.motel.repository.RoomImageRepository;
import com.binh.motel.service.PostRoomService;

import javassist.NotFoundException;

@Service
public class PostRoomServiceImpl  implements PostRoomService{
	@Autowired
	private PostRoomRepository postRoomRepository;
	
	@Autowired
	private RoomImageRepository roomImageRepository;
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private WardService wardService;

	@Override
	public MotelRoom getMotelRoomById(int id) throws NotFoundException {
		return postRoomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room notfound"));
	}

	@Override
	public List<MotelRoom> getAll() {
		return postRoomRepository.findAll();
	}
	
	@Override
	public void approveRoom(int roomId) throws NotFoundException {
		MotelRoom motelRoom = getMotelRoomById(roomId);
		motelRoom.setApprove(1);
		postRoomRepository.save(motelRoom);
	}
	
	@Override
	 public void deleteRoom(int id) throws NotFoundException{
		MotelRoom motelRoom = getMotelRoomById(id);
		
		List<RoomImage> roomImages = motelRoom.getImages();
		roomImageRepository.deleteAll(roomImages);

		
		 postRoomRepository.deleteById(id);
	    }

	@Override
	public RoomImage getRoomImageById(int id) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public long getCountMotelRoom() {
		long count = postRoomRepository.count();
		return count;
	}
	
	@Override
	public MotelRoom save(MotelRoomDto room) throws NotFoundException{
		MotelRoom motelRoom = new MotelRoom();
		motelRoom.setId(room.getId());
		motelRoom.setTitle(room.getTitle());
		motelRoom.setDescription(room.getDescription());
		motelRoom.setPrice(room.getPrice());
		motelRoom.setArea(room.getArea());
		motelRoom.setDepositAmount(room.getDepositAmount());
		motelRoom.setBalconyDirection(room.getBalconyDirection());
		motelRoom.setDoorDirection(room.getDoorDirection());
		motelRoom.setNumOfBedrooms(room.getNumOfBedrooms());
		motelRoom.setNumOfToilets(room.getNumOfToilets());

		String wardCode = room.getWard();
		Ward ward = wardService.getWardByCode(wardCode).orElseThrow(() -> new NotFoundException("Ward not found"));

		District district = ward.getDistrict();
		
		Province province = district.getProvince();
		
		String fullAddress = String.format(
				"%s, %s, %s, %s",
				room.getAddress(),
				ward.getNameWithType(),
				district.getNameWithType(),
				province.getNameWithType());
		
		motelRoom.setFullAddress(fullAddress);
		motelRoom.setProvince(province.getNameWithType());
		
		motelRoom.setWardCode(ward.getCode());
		motelRoom.setDistrictCode(district.getCode());
		motelRoom.setProvinceCode(province.getCode());
		motelRoom.setAddress(room.getAddress());
		motelRoom.setCategory(categoryService.getCategoryByCode(room.getCategory()));
		motelRoom.setAddress(room.getAddress());
		motelRoom.setPhoneNumber(room.getPhoneNumber());
		motelRoom.setSlug(room.getSlug());
		motelRoom.setUserName("BInhLOl");

		MotelRoom saved = postRoomRepository.save(motelRoom);
		return saved;


	}
	
	@Override
	public Page<MotelRoom> searchRooms(Filter filter) {
		Pageable pageAble = PageRequest.of(filter.getPageNum(), filter.getPageSize());

		double minPrice = 0;
		if (filter.getMinPrice() > minPrice) {
			minPrice = filter.getMinPrice();
		}
		Specification<MotelRoom> spec = MotelRoomSpecification.priceGreaterOrEqual(minPrice);


		double maxPrice = Double.MAX_VALUE;

		if (filter.getMaxPrice() < maxPrice && filter.getMaxPrice() > minPrice) {
			maxPrice = filter.getMaxPrice();
		}
		spec = Specification.where(spec).and(MotelRoomSpecification.priceLessThanOrEqual(maxPrice));
		
		
		double minArea = 0;
		
		if (filter.getMinArea() > minArea) {
			minArea = filter.getMinArea();
		}
		
		spec = spec.and(MotelRoomSpecification.areaGreaterOrEqual(minArea));
		
		double maxArea = Double.MAX_VALUE;
		
		if (filter.getMaxArea() < maxArea && filter.getMaxArea() > minArea) {
			maxArea = filter.getMaxArea();
			spec = spec.and(MotelRoomSpecification.areaLessThanOrEqual(maxArea));
		}
		
//		int numOfBedrooms = 0;
//		if (filter.getNumOfBedrooms() > numOfBedrooms) {
//			numOfBedrooms = filter.getNumOfBedrooms();
//			spec = spec.and(MotelRoomSpecification.numOfBedroomsEqual(numOfBedrooms));
//		}
		
		int numOfToilets = 0;
		if (filter.getNumOfToilets() > numOfToilets) {
			numOfToilets = filter.getNumOfToilets();
			spec = spec.and(MotelRoomSpecification.numOfToiletsEqual(numOfToilets));
		}
		
		if (null != filter.getBalconyDirection() && StringUtils.hasText(filter.getBalconyDirection())) {			
			spec = spec.and(MotelRoomSpecification.balconyDirectionEqual(filter.getBalconyDirection()));
		}
		
		if (null != filter.getDoorDirection() && StringUtils.hasText(filter.getDoorDirection())) {
			spec = spec.and(MotelRoomSpecification.doorDirectionEqual(filter.getDoorDirection()));
		}
		
		if (StringUtils.hasText(filter.getCategory())) {
			spec = spec.and(MotelRoomSpecification.categoryEqual(filter.getCategory()));
		}
		
		
		String wardCode;
		if (StringUtils.hasText(filter.getWardCode())) {
			wardCode = filter.getWardCode();
		
		
			spec = spec.and(MotelRoomSpecification.wardCodeEqual(wardCode));
		}
		
		if (StringUtils.hasText(filter.getDistrictCode())) {
			spec = spec.and(MotelRoomSpecification.districtCodeEqual(filter.getDistrictCode()));
		}
		if (StringUtils.hasText(filter.getProvinceCode())) {
			spec = spec.and(MotelRoomSpecification.provinceCodeEqual(filter.getProvinceCode()));
		}
		
		return postRoomRepository.findAll(spec, pageAble);
	}

	@Override
	public List<MotelRoom> getMotelRooms(String userName) {
		List<MotelRoom> rooms = postRoomRepository.findByUserName(userName);
		return rooms;
	}

	@Override
	public long countRooms() {
		return postRoomRepository.count();
	}

}
