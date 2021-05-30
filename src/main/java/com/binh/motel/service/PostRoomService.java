package com.binh.motel.service;

import java.util.List;

import javax.validation.Valid;

import com.binh.motel.dto.MotelRoomDto;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.RoomImage;

import javassist.NotFoundException;


public interface PostRoomService {



	List<MotelRoom> getAll();


	MotelRoom getMotelRoomById(int id) throws NotFoundException;
	
	public void approveRoom(int roomId) throws NotFoundException;
	
	public void deleteRoom(int id) throws NotFoundException;
	
	RoomImage getRoomImageById(int id) throws NotFoundException;


	public long getCountMotelRoom();



}
