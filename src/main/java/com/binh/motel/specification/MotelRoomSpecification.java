package com.binh.motel.specification;

import org.springframework.data.jpa.domain.Specification;

import com.binh.motel.entity.MotelRoom;
import com.binh.motel.enums.RoomDirection;

public final class MotelRoomSpecification {
	
	public static Specification<MotelRoom> wardCodeEqual(String wardCode) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.equal(root.get("wardCode"), wardCode);
	}
	
	public static Specification<MotelRoom> priceGreaterOrEqual(double price) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
	}
	
	public static Specification<MotelRoom> priceLessThanOrEqual(double price) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
	}
	
	public static Specification<MotelRoom> areaGreaterOrEqual(double area) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.greaterThanOrEqualTo(root.get("area"), area);
	}
	
	public static Specification<MotelRoom> areaLessThanOrEqual(double area) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.lessThanOrEqualTo(root.get("area"), area);
	}
	
	public static Specification<MotelRoom> numOfBedroomsEqual(int numOfBedrooms) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.equal(root.get("numOfBedrooms"), numOfBedrooms);
	}
	
	public static Specification<MotelRoom> numOfToiletsEqual(int numOfToilets) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.equal(root.get("numOfToilets"), numOfToilets);
	}
	
	public static Specification<MotelRoom> districtCodeEqual(String districtCode) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.equal(root.get("districtCode"), districtCode);
	}
	
	public static Specification<MotelRoom> provinceCodeEqual(String provinceCode) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.equal(root.get("provinceCode"), provinceCode);
	}
	
	public static Specification<MotelRoom> doorDirectionEqual(String doorDirection) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.equal(root.get("doorDirection"), RoomDirection.valueOf(doorDirection));
	}
	
	public static Specification<MotelRoom> balconyDirectionEqual(String balconyDirection) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.equal(root.get("balconyDirection"),  RoomDirection.valueOf(balconyDirection));
	}
	
	public static Specification<MotelRoom> categoryEqual(String categoryCode) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.equal(root.join("category").get("code"), categoryCode);
	}
	
	public static Specification<MotelRoom> isTrue() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
	}
	
	
	
	
	

}
