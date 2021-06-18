package com.binh.motel.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.binh.motel.dto.MotelRoomDto;
import com.binh.motel.dto.RoomFilter;
import com.binh.motel.dto.response.PageResponse;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.User;
import com.binh.motel.specification.Filter;

import javassist.NotFoundException;


public interface MotelRoomService {



	List<MotelRoom> getAll();


	MotelRoom getMotelRoomById(int id) throws NotFoundException;
	
	
	public void deleteRoom(int id) throws NotFoundException;
	
	MotelRoom findById(int id) throws NotFoundException;


	public long getCountMotelRoom();


	MotelRoom save(@Valid MotelRoomDto room) throws NotFoundException ;


	Page<MotelRoom> searchRooms(Filter filter);


	List<MotelRoom> getMotelRooms(String userName);
	
	PageResponse<MotelRoom> searchRooms(RoomFilter filter);
	
	PageResponse<MotelRoom> searchRoomsByUser(User user, RoomFilter filter);

	public long countRooms();
	
	public MotelRoom findBySlugOrId(String value) throws NotFoundException;


	void toggleStatus(int id, boolean status) throws NotFoundException;
	
	void toggleDelete(int id, boolean status) throws NotFoundException;
}
