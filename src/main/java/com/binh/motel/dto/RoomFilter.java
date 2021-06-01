package com.binh.motel.dto;

import org.springframework.data.jpa.domain.Specification;

import com.binh.motel.entity.MotelRoom;
import com.binh.motel.specification.MotelRoomSpecification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomFilter extends Filter<MotelRoom> {

	public RoomFilter(String pageNum, String pageSize, String search) {
		super(pageNum, pageSize, search);
	}

	@Override
	public Specification<MotelRoom> buildSpec() {
		Specification<MotelRoom> spec = MotelRoomSpecification.isTrue();
		return spec;
	}
}
