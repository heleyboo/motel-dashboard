package com.binh.motel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binh.motel.entity.MotelRoom;

public interface PostRoomRepository extends JpaRepository<MotelRoom, Integer>{

}
