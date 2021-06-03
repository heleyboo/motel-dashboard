package com.binh.motel.specification;

import org.springframework.data.jpa.domain.Specification;

import com.binh.motel.entity.Category;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.User;
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
	
	public static Specification<MotelRoom> numOfBedroomsGreaterThanOrEqual(int numOfBedrooms) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.greaterThanOrEqualTo(root.get("numOfBedrooms"), numOfBedrooms);
	}
	
	public static Specification<MotelRoom> numOfBedroomsLessThanOrEqual(int numOfBedrooms) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.lessThanOrEqualTo(root.get("numOfBedrooms"), numOfBedrooms);
	}
	
	public static Specification<MotelRoom> numOfToiletsGreaterThanOrEqual(int numOfToilets) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.greaterThanOrEqualTo(root.get("numOfToilets"), numOfToilets);
	}
	
	public static Specification<MotelRoom> numOfToiletsLessThanOrEqual(int numOfToilets) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.lessThanOrEqualTo(root.get("numOfToilets"), numOfToilets);
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
	
	public static Specification<MotelRoom> categoryEqual(Category category) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.equal(root.join("category"), category);
	}
	
	public static Specification<MotelRoom> isTrue() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
	}
	
	public static Specification<MotelRoom> propertyLike(String property, String value) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(property), "%" + value + "%");
	}
	
	public static Specification<MotelRoom> isApproved(Boolean status) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("approve"), status);
	}
	
	public static Specification<MotelRoom> isDeleted(Boolean status) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("deleted"), status);
	}
	
	
	
	public static Specification<MotelRoom> userEqual(User user) {
		return (root, query, criteriaBuilder)
				-> criteriaBuilder.equal(root.get("createdBy"), user);
	}

}
