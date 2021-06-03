package com.binh.motel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.User;

public interface MotelRoomRepository extends JpaRepository<MotelRoom, Integer>, JpaSpecificationExecutor<MotelRoom>{

	List<MotelRoom> findByCreatedBy(User userName);
	Optional<MotelRoom> findBySlugAndDeletedFalse(String slug);
	Optional<MotelRoom> findBySlugOrIdAndDeletedFalse(String slug, int id);

}
