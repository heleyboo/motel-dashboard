package com.binh.motel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binh.motel.entity.Ward;

public interface WardRepository extends JpaRepository<Ward, String> {

	List<Ward> getWard();

}
