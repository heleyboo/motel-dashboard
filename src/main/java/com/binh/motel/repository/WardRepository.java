package com.binh.motel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binh.motel.entity.District;
import com.binh.motel.entity.Ward;

public interface WardRepository extends JpaRepository<Ward, String> {
	
	public List<Ward> findByDistrict(District district);
}
