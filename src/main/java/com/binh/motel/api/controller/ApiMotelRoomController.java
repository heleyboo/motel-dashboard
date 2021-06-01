package com.binh.motel.api.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.binh.motel.dto.MotelRoomDto;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.service.MotelRoomService;
import com.binh.motel.specification.Filter;

import javassist.NotFoundException;


@RestController
@RequestMapping("/api/v1/rooms")
public class ApiMotelRoomController {
	@Autowired
	private MotelRoomService motelroomservice;

	@PostMapping
	public MotelRoom createRoom(@Valid @RequestBody MotelRoomDto room) throws NotFoundException {
		return motelroomservice.save(room);

	}

	@GetMapping
	public Page<MotelRoom> search(
			@RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum,
			@RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "minPrice", required = false, defaultValue = "0") double minPrice,
			@RequestParam(value = "maxPrice", required = false, defaultValue = "0") double maxPrice,
			@RequestParam(value = "minArea", required = false, defaultValue = "0") double minArea,
			@RequestParam(value = "maxArea", required = false, defaultValue = "0") double maxArea,
			@RequestParam(value = "numOfBedrooms", required = false, defaultValue = "0") int numOfBedrooms,
			@RequestParam(value = "numOfToilets", required = false, defaultValue = "0") int numOfToilets,
			@RequestParam(value = "districtCode", required = false) String districtCode,
			@RequestParam(value = "provinceCode", required = false) String provinceCode,
			@RequestParam(value = "wardCode", required = false) String wardCode,
			@RequestParam(value = "doorDirection", required = false) String doorDirection,
			@RequestParam(value = "balconyDirection", required = false) String balconyDirection,
			@RequestParam(value = "category", required = false) String category
			
			) {
        Filter filter = Filter.builder()
        		.pageNum(pageNum)
        		.pageSize(pageSize)
        		.minPrice(minPrice)
        		.maxPrice(maxPrice)
        		.minArea(minArea)
        		.maxArea(maxArea)
        		.numOfBedrooms(numOfBedrooms)
        		.numOfToilets(numOfToilets)
        		.districtCode(districtCode)
        		.provinceCode(provinceCode)
        		.wardCode(wardCode)
        		.balconyDirection(balconyDirection)
        		.doorDirection(doorDirection)
        		.category(category)
        		.build();
        
		return motelroomservice.searchRooms(filter);
	}

	@GetMapping("/{id}")
	public MotelRoom getById(@PathVariable("id") Integer roomId) throws Exception {
		return motelroomservice.getMotelRoomById(roomId);
	}

	@GetMapping("/by-user")
	// @PathVariable: Tìm resource theo ID, VD: api/v1/rooms/12
	// @RequestParam: Truy vấn danh sách resource theo thuộc tính: vd:
	// /api/v1/rooms?username=binh
	public List<MotelRoom> getMotelRooms(@RequestParam(name = "userName", required = true) String userName) {
		return motelroomservice.getMotelRooms(userName);
	}

}
