package com.binh.motel.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import com.binh.motel.dto.MotelRoomDTO;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.RoomImage;
import com.binh.motel.repository.PostRoomRepository;
import com.binh.motel.repository.RoomImageRepository;
import com.binh.motel.service.PostRoomService;
import com.binh.motel.util.SlugUtil;

import javassist.NotFoundException;

@Service
public class PostRoomServiceImpl  implements PostRoomService{
	@Autowired
	private PostRoomRepository postRoomRepository;
	
	@Autowired
	private RoomImageRepository roomImageRepository;

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
//		motelRoom.getImages()
//		postRoomRepository.delete(motelRoom);
		
//		RoomImage roomImage = getRoomImageById(id);
//		roomImage.getRoom().getId();
//		roomImageRepository.delete(roomImage);
		
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

}
