package com.binh.motel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.binh.motel.entity.MotelRoom;

public interface PostRoomRepository extends JpaRepository<MotelRoom, Integer>{

	Page<MotelRoom> findAll(Specification<MotelRoom> spec, Pageable pageAble);

	List<MotelRoom> findByUserName(String userName);

}
