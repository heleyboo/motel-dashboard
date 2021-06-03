package com.binh.motel.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.binh.motel.entity.Category;
import com.binh.motel.entity.District;
import com.binh.motel.entity.MotelRoom;
import com.binh.motel.entity.Province;
import com.binh.motel.entity.User;
import com.binh.motel.entity.Ward;
import com.binh.motel.enums.RoomDirection;
import com.binh.motel.specification.MotelRoomSpecification;
import com.binh.motel.util.CustomStringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomFilter extends Filter<MotelRoom> {

	private RoomDirection doorDirection;
	private RoomDirection balconyDirection;
	private Province province;
	private District district;
	private Ward ward;
	private Category category;
	private String numOfBedrooms;
	private String numOfToilets;
	private String priceRange;
	private String areaRange;
	private User user;
	private Boolean approve = true;
	private Boolean deleted = false;
	private String order;

	public RoomFilter(String pageNum, String pageSize, String search) {
		super(pageNum, pageSize, search);
	}

	@Override
	public Specification<MotelRoom> buildSpec() {
		Specification<MotelRoom> spec = MotelRoomSpecification.isTrue();

		if (null != approve) {
			spec = spec.and(MotelRoomSpecification.isApproved(approve));
		}

		if (null != deleted) {
			spec = spec.and(MotelRoomSpecification.isDeleted(deleted));
		}

		if (null != category) {
			spec = spec.and(MotelRoomSpecification.categoryEqual(category));
		}

		if (null != province) {
			spec = spec.and(MotelRoomSpecification.propertyLike("provinceCode", province.getCode()));
		}

		if (null != district) {
			spec = spec.and(MotelRoomSpecification.propertyLike("districtCode", district.getCode()));
		}

		if (null != ward) {
			spec = spec.and(MotelRoomSpecification.propertyLike("wardCode", ward.getCode()));
		}

		// 1000000;5000000
		if (StringUtils.hasText(priceRange)) {
			List<Double> rangePrice = splitRange(priceRange);
			spec = spec.and(MotelRoomSpecification.priceGreaterOrEqual(rangePrice.get(0)));
			spec = spec.and(MotelRoomSpecification.priceLessThanOrEqual(rangePrice.get(1)));
		}

		if (StringUtils.hasText(areaRange)) {
			List<Double> rangeArea = splitRange(areaRange);
			spec = spec.and(MotelRoomSpecification.areaGreaterOrEqual(rangeArea.get(0)));
			spec = spec.and(MotelRoomSpecification.areaLessThanOrEqual(rangeArea.get(1)));
		}

		if (StringUtils.hasText(search)) {
			Specification<MotelRoom> searchSpec = MotelRoomSpecification.propertyLike("title", search);
			searchSpec = searchSpec.or(MotelRoomSpecification.propertyLike("description", search));
			spec = spec.and(searchSpec);
		}

		Integer bedRooms = CustomStringUtils.parseIntegerOrNull(numOfBedrooms);

		if (null != bedRooms) {
			Integer minBedRooms = bedRooms;
			Integer maxBedRooms = bedRooms + 1;
			Specification<MotelRoom> bedRoomSpec = MotelRoomSpecification.numOfBedroomsGreaterThanOrEqual(minBedRooms);
			bedRoomSpec = bedRoomSpec.and(MotelRoomSpecification.numOfBedroomsLessThanOrEqual(maxBedRooms));
			spec = spec.and(bedRoomSpec);
		}

		Integer toilets = CustomStringUtils.parseIntegerOrNull(numOfToilets);

		if (null != toilets) {
			Integer minToilets = toilets;
			Integer maxToilets = toilets + 1;
			Specification<MotelRoom> toiletSpec = MotelRoomSpecification.numOfToiletsGreaterThanOrEqual(minToilets);
			toiletSpec = toiletSpec.and(MotelRoomSpecification.numOfToiletsLessThanOrEqual(maxToilets));
			spec = spec.and(toiletSpec);
		}

		if (null != user) {
			spec = spec.and(MotelRoomSpecification.userEqual(user));
		}

		return spec;
	}

	@SuppressWarnings("unused")
	private List<Double> splitRange(String rangeValue) {
		List<Double> result = new ArrayList<Double>();
		String[] splitted = rangeValue.split(";");
		List<String> lstString = Arrays.asList(splitted);

		if (indexExists(lstString, 0)) {
			result.add(CustomStringUtils.parseDoubleOrValue(lstString.get(0), 0.0));
		} else {
			result.add(0.0);
		}

		if (indexExists(lstString, 1)) {
			result.add(CustomStringUtils.parseDoubleOrValue(lstString.get(1), 9999999990.0));
		} else {
			result.add(9999999990.0);
		}

		return result;
	}

	public boolean indexExists(final List list, final int index) {
		return index >= 0 && index < list.size();
	}

	@Override
	public List<Order> orders() {
		List<Order> orders = new ArrayList<Order>();
		int orderValue = CustomStringUtils.parseIntegerOrZero(order);
		switch (orderValue) {
		case 1:
			orders.add(Order.desc("createdDate"));
			break;
		case 2:
			orders.add(Order.asc("price"));
			break;
		case 3:
			orders.add(Order.desc("price"));
			break;

		default:
			orders.add(Order.desc("createdDate"));
			break;
		}
		return orders;
	}
}
